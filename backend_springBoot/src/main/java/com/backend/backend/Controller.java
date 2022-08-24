package com.backend.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Dictionary;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class Controller {

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
            return new ResponseEntity<>(newUser, HttpStatus.OK);
        }
        else{
            newUserResponse newUser = new newUserResponse("##########", false, null);
            return new ResponseEntity<>(newUser, HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping("/file")
    public ResponseEntity<FileResponse> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("userDetails") userInformationEntity userDetails){
        String fileName = fileStorageService.storeFile(file, "");
        String fileDownUri = ServletUriComponentsBuilder.fromCurrentRequestUri().
                path("/uploads/")
                .path(fileName)
                .toUriString();
        FileResponse fileResponse = new FileResponse(fileName, fileDownUri, file.getContentType(), file.getSize(), userDetails);
        return  new ResponseEntity<>(fileResponse, HttpStatus.OK);
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
