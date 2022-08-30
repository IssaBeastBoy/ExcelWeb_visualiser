package com.backend.backend;

import com.backend.backend.processor.ViewData;
import com.backend.backend.processor.ingestSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class Controller {

    private Dictionary<Integer, List<String>> customerInfo;

    public Dictionary<Integer, List<String>> getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(Dictionary<Integer, List<String>> customerInfo) {
        this.customerInfo = customerInfo;
    }

    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping("/login/{id}")
    public userInformationEntity Login(@PathVariable(value = "id") int id){
        userInfoTransactions userInfo = new userInfoTransactions();
        List<userInformationEntity> user = userInfo.findUser(String.valueOf(id));
        if(user.size() == 1){
            return user.get(0);
        }
        else{
            return null;
        }
    }

    @GetMapping("/Register/all")
    public List<userInformationEntity> allUsers(){
        userInfoTransactions userInfo = new userInfoTransactions();
        return userInfo.findall();
    }

    @PostMapping("/Register/user")
    public ResponseEntity<newUserResponse> registerUser(@ModelAttribute newUserBody user){
        userInfoTransactions userInfo = new userInfoTransactions();
        String[] createLas = user.getLasNumbr().split("");
        String lasNumber = "LAS";
        for(int digit = 5- createLas.length; digit > 0; digit--){
            lasNumber += "0";
        }
        lasNumber += user.getLasNumbr();
        Boolean userCreated = userInfo.addUser(user.getPassword(),user.getName(),user.getSurname()
                ,user.getEmail(),user.getContact(),lasNumber);
        if(userCreated){
            userInformationEntity userAdd = userInfo.findUser(user.getLasNumbr()).get(0);
            newUserResponse newUser = new newUserResponse(lasNumber, true, userAdd);
            try{
                Files.createDirectories(Path.of(userAdd.getFileStorageDir()));
                return new ResponseEntity<>(newUser, HttpStatus.OK);
            }catch(Exception ex){
                return new ResponseEntity<>(null, HttpStatus.OK);
            }
        }
        else{
            newUserResponse newUser = new newUserResponse("##########", false, null);
            return new ResponseEntity<>(newUser, HttpStatus.FORBIDDEN);
        }
    }
    @PostMapping("/PieChartView")
    public List<List<String>> pieChartView (@RequestParam("colName") String colName){
        ViewData viewData = new ViewData(customerInfo, colName);
        return viewData.getPieView();
    }

    @PostMapping("/BarGraphView")
    public List<List<String>> barGraphView (@RequestParam("colName") String colName){
        ViewData viewData = new ViewData(customerInfo, colName);
        return viewData.getBarGraphView();
    }

    @PostMapping("/TableView")
    public List<List<String>> tableView (@RequestParam("colName") String colName){
        ViewData viewData = new ViewData(customerInfo, colName);
        return viewData.getTableView();
    }

    @PostMapping("/Excel_sheet")
    public List<String> inputSheet (@RequestParam("sheetLoc") String sheetLoc){
        ingestSheet ingest = new ingestSheet();
        ingest.setExcel_loc(sheetLoc);
        ingest.parseSheet();
        setCustomerInfo(ingest.getCustomerInfo());
        List<String> excludeTotal = new ArrayList<>();
        for(int index = 0; index < customerInfo.get(-1).size()-1; index++){
            excludeTotal.add(customerInfo.get(-1).get(index));
        }
        return excludeTotal;
    }

    @PostMapping("/delUpload")
    public Boolean delFile(@RequestParam(value="fileLoc") String fileLoc){
        List<String> files = new ArrayList<>();
        File dir = new File(fileLoc);
        if (dir.delete()) {
            return true;
        } else {
            return false;
        }
    }

    @PostMapping("/Getuploads")
    public uploadFilesResponse getFiles(@RequestParam(value="fileLoc") String fileLoc){
        List<String> files = new ArrayList<>();
        File dir = new File(fileLoc);
        String[] dirContent = dir.list();
        for (int i=0; i<dirContent.length; i++)
        {
            File curr = new File(fileLoc+dirContent[i]);
            Boolean check = curr.exists();
            DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            long lastMod = curr.lastModified();
            Date date = new Date(lastMod);
            files.add(dirContent[i]+" - "+date);
        }
        uploadFilesResponse uploaded = new uploadFilesResponse(files);
        return uploaded;
    }

    @PutMapping("/file")
    public ResponseEntity<FileResponse> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("userDetails") String userDir){
        try{
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            String fileDownUri = ServletUriComponentsBuilder.fromCurrentRequestUri().
                    path("/userFile/")
                    .path(fileName)
                    .toUriString();
            Path userLocation = Path.of(userDir + fileName);
            FileResponse fileResponse = new FileResponse(file.getName(), fileDownUri, file.getContentType(), file.getSize(), (userDir + fileName));
            List<String> fileNameContents = List.of(fileName.split("\\."));

            if(fileNameContents.contains("xlsx") || fileNameContents.contains("xlsm")
                    || fileNameContents.contains("xlsb") || fileNameContents.contains("xls")) {
                Files.copy(file.getInputStream(), userLocation, StandardCopyOption.REPLACE_EXISTING);
                return new ResponseEntity<>(fileResponse, HttpStatus.OK);
            }
            else{
                return  new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
            }
            }catch (Exception ex){
                return  new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
            }
        }

    @GetMapping("/{fileName:.+}")
    public  ResponseEntity<UrlResource> downloadFile(@PathVariable String fileName, HttpServletRequest request){
        UrlResource resource = fileStorageService.loadFileResource(fileName);
        String contentType = null;
        try{
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        }
        catch (IOException ex){
            System.out.println("Could not find file type");
        }
        if(contentType==null){
            int r = 0;
        }
        //Resource resource1 = (Resource) resource;
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }


}
