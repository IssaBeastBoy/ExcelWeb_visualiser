package com.backend.backend;

import com.backend.backend.apps.calendar;
import com.backend.backend.apps.tickets;
import com.backend.backend.processor.ViewData;
import com.backend.backend.processor.ingestSheet;
import org.apache.poi.util.ArrayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.yaml.snakeyaml.util.ArrayUtils;

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
import java.util.*;

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

    @PostMapping("/updateNotes")
    public userInformationEntity updateNotes(){

    }

    @PostMapping("/updateTickets")
    public userInformationEntity updateTickets(@ModelAttribute tickets user){
        userInfoTransactions userInfo = new userInfoTransactions();
        userInformationEntity userDetails = userInfo.findUser(user.getUserId()).get(0);
        Dictionary<String, String> priorityColor = new Hashtable<>();
        priorityColor.put("Minor","#FFE000"); priorityColor.put("Low","#F5A623");
        priorityColor.put("High","#FF4700"); priorityColor.put("Critical","#FF0000");
        String ticketDetails = "";
        if(userDetails.getTickets().equals("")){
            ticketDetails = "Id-1"+"^Priority-"+ user.getPriority()+"^Summary-"+ user.getSummary()+"^Color-"+priorityColor.get(user.getPriority())
                    +"^Status-" +user.getStatus()+"^Estimate-"+user.getEstimate();
        }
        else if(user.getEventType().equals("new")){
            String[] currTickets = userDetails.getTickets().split(">");
            ticketDetails = "Id-"+(currTickets.length + 1)+"^Priority-"+ user.getPriority()+"^Summary-"+ user.getSummary()+"^Color-"+priorityColor.get(user.getPriority())
                    +"^Status-" +user.getStatus()+"^Estimate-"+user.getEstimate();
            ticketDetails += ">" + userDetails.getTickets();
        }
        else if(user.getEventType().equals("cardChange")){
            String[] currTickets = userDetails.getTickets().split(">");
            for(int parse = 0; parse< currTickets.length; parse++){
                String[] ticketItems = currTickets[parse].split("\\^");
                for(int index =0; index<ticketItems.length; index++){
                    String[] ticketItem = ticketItems[index].split("-");
                    if(ticketItem[1].equals(user.getId())){
                        if(ticketDetails.equals("")){
                            ticketDetails += "Id-"+user.getId()+"^Priority-"+ user.getPriority()+"^Summary- "+ user.getSummary()+
                                    "^Color-"+priorityColor.get(user.getPriority())+"^Status-" +user.getStatus()+"^Estimate-"+user.getEstimate() ;

                        }
                        else{
                            ticketDetails += ">"+"Id-"+user.getId()+"^Priority-"+ user.getPriority()+"^Summary- "+ user.getSummary()+
                                    "^Color-"+priorityColor.get(user.getPriority())+"^Status-" +user.getStatus()+"^Estimate-"+user.getEstimate() ;

                        }
                        break;
                    }
                    else{
                        if(ticketDetails.equals("")){
                            ticketDetails += currTickets[parse];
                        }
                        else{
                            ticketDetails += ">" + currTickets[parse];
                        }
                        break;
                    }
                }
            }
        }
        else if(user.getEventType().equals("moveCard")){
            String[] currTickets = userDetails.getTickets().split(">");
            Dictionary<String, String> boardPostions = new Hashtable<>();
            boardPostions.put("0", "Backlog");
            boardPostions.put("1", "Open");
            boardPostions.put("2", "InProgress");
            boardPostions.put("3", "Close");
            for(int parse = 0; parse< currTickets.length; parse++){
                String[] ticketItems = currTickets[parse].split("\\^");
                for(int index =0; index<ticketItems.length; index++){
                    String[] ticketItem = ticketItems[index].split("-");
                    if(ticketItem[1].equals(user.getId())){
                        if(ticketDetails.equals("")){
                            ticketDetails += "Id-"+user.getId()+"^Priority-"+ user.getPriority()+"^Summary- "+ user.getSummary()+
                                    "^Color-"+priorityColor.get(user.getPriority())+"^Status-" +boardPostions.get(user.getStatus())+"^Estimate-"+user.getEstimate() ;

                        }
                        else{
                            ticketDetails += ">"+"Id-"+user.getId()+"^Priority-"+ user.getPriority()+"^Summary- "+ user.getSummary()+
                                    "^Color-"+priorityColor.get(user.getPriority())+"^Status-" +boardPostions.get(user.getStatus())+"^Estimate-"+user.getEstimate() ;

                        }
                        break;
                    }
                    else{
                        if(ticketDetails.equals("")){
                            ticketDetails += currTickets[parse];
                        }
                        else{
                            ticketDetails += ">" + currTickets[parse];
                        }
                        break;
                    }
                }
            }
        }
        else if(user.getEventType().equals("cardRemove")){
            String[] currTickets = userDetails.getTickets().split(">");
            String updated = "";
            boolean changeID = false;
            for(int parse = 0; parse< currTickets.length; parse++){
                String[] ticketItems = currTickets[parse].split("\\^");
                for(int index =0; index<ticketItems.length && ticketItems.length !=0; index++){
                    String[] ticketItem = ticketItems[index].split("-");
                    if(ticketItem[0].equals("Id") && ticketItem[1].equals(user.getId())){
                        changeID = true;
                        break;
                    }
                    else if(changeID && ticketItem[0].equals("Id")){
                        updated += "Id-" +(Integer.valueOf(ticketItem[1]) - 1);
                    }
                    else if(changeID){
                        updated+= "^" + ticketItems[index];
                    }
                    else{
                        ticketDetails +=  ">" + currTickets[parse];
                        break;
                    }
                }
                if (changeID && !updated.equals("")){
                    if(ticketDetails.equals(""))
                        ticketDetails += updated;
                    else
                        ticketDetails += ">" + updated;
                    updated = "";
                }

            }
        }
        userDetails.setTickets(ticketDetails);
        Boolean update = userInfo.updateUser(userDetails);
        return userInfo.findUser(user.getUserId()).get(0);
    }

    @PostMapping("/updateCalendar")
    public userInformationEntity updateCalendar(@ModelAttribute calendar user){
        userInfoTransactions userInfo = new userInfoTransactions();
        userInformationEntity userDetails = userInfo.findUser(user.getUserId()).get(0);
        String eventDetails = "Id-" +user.getId()+"^Subject-"
                +user.getSubject()+ "^StartTime-"+user.getStartTime().getTime()+
                "^IsAllDay-"+user.getAllDay()+"^EndTime-"+user.getEndTime().getTime();
        if(userDetails.getCalendar().equals("")){
            if(user.getCancel()!=null)
                eventDetails += "^cancel-"+user.getCancel();
            if(user.getLocation() != null)
                eventDetails += "^Location-"+user.getLocation();
            if(user.getDescription() != null)
                eventDetails += "^Description-"+user.getDescription();
            if(user.getRecurrenceRule() != null)
                eventDetails += "^RecurrenceRule-"+user.getRecurrenceRule();
            if(user.getEndTimezone() != null)
                eventDetails += "^EndTimezone-"+user.getEndTimezone();
            if(user.getStartTimezone() != null)
                eventDetails += "^StartTimezone-"+user.getStartTimezone();
            if(user.getRecurrenceID() != 0)
                eventDetails += "^RecurrenceID-"+user.getRecurrenceID();
            if(user.getRecurrenceException() != null)
                eventDetails += "^RecurrenceException-"+user.getRecurrenceException();
            userDetails.setCalendar(eventDetails);
        }
        else if (user.getEventType().equals("new")){
            if(user.getCancel()!=null)
                eventDetails += "^cancel-"+user.getCancel();
            if(user.getLocation() != null)
                eventDetails += "^Location-"+user.getLocation();
            if(user.getDescription() != null)
                eventDetails += "^Description-"+user.getDescription();
            if(user.getRecurrenceRule() != null)
                eventDetails += "^RecurrenceRule-"+user.getRecurrenceRule();
            if(user.getEndTimezone() != null)
                eventDetails += "^EndTimezone-"+user.getEndTimezone();
            if(user.getStartTimezone() != null)
                eventDetails += "^StartTimezone-"+user.getStartTimezone();
            if(user.getRecurrenceID() != 0)
                eventDetails += "^RecurrenceID-"+user.getRecurrenceID();
            if(user.getRecurrenceException() != null)
                eventDetails += "^RecurrenceException-"+user.getRecurrenceException();
            eventDetails = userDetails.getCalendar()+">"+eventDetails;
            userDetails.setCalendar(eventDetails);
        }
        else if (user.getEventType().equals("delete")){
            String[] currCalendar = userDetails.getCalendar().split(">");
            String reConStr = "";
            int addUpdate = 0;
            int updated = 0;
            for(int parse = 0; parse<currCalendar.length; parse++){
                String[] items = currCalendar[parse].split("\\^");
                if(!currCalendar[parse].equals("")){
                    String idReset = "";
                    for(int index=0; index<items.length; index++){
                        String[] item = items[index].split("-");
                        if(addUpdate == 1){
                            int tempID = Integer.parseInt(item[1]) - 1;
                            String currID = "";
                            item[1] = String.valueOf(tempID);
                            currID = item[0] + "-" + item[1];
                            idReset += currID;
                            for(int i = 1; i <items.length; i++){
                                idReset +="^"+items[i];
                            }
                            break;
                        }
                        else if(item[1].equals(String.valueOf(user.getId())) && addUpdate == 0){
                            addUpdate = 1;
                            break;
                        }
                        else{
                            idReset = currCalendar[parse];
                            break;
                        }
                    }
                    if(!idReset.equals(""))
                        reConStr += idReset + ">";
                }

            }
            userDetails.setCalendar(reConStr);
        }
        else if (user.getEventType().equals("update")){
            if(user.getCancel()!=null)
                eventDetails += "^cancel-"+user.getCancel();
            if(user.getLocation() != null)
                eventDetails += "^Location-"+user.getLocation();
            if(user.getDescription() != null)
                eventDetails += "^Description-"+user.getDescription();
            if(user.getRecurrenceRule() != null)
                eventDetails += "^RecurrenceRule-"+user.getRecurrenceRule();
            if(user.getEndTimezone() != null)
                eventDetails += "^EndTimezone-"+user.getEndTimezone();
            if(user.getStartTimezone() != null)
                eventDetails += "^StartTimezone-"+user.getStartTimezone();
            if(user.getRecurrenceID() != 0)
                eventDetails += "^RecurrenceID-"+user.getRecurrenceID();
            if(user.getRecurrenceException() != null)
                eventDetails += "^RecurrenceException-"+user.getRecurrenceException();
            String[] currCalendar = userDetails.getCalendar().split(">");
            boolean breakLoop = false;
            String reConStr = "";
            int addUpdate = 0;
            for(int parse = 0; parse<currCalendar.length; parse++){
                String[] items = currCalendar[parse].split("\\^");
                if(!currCalendar[parse].equals("")){
                    for(int index=0; index<items.length && !breakLoop; index++){
                    String[] item = items[index].split("-");
                    if(item[1].equals(String.valueOf(user.getId()))){
                        breakLoop = true;
                        addUpdate = 1;
                    }
                    break;
                }
                    if(addUpdate == 0)
                        reConStr += currCalendar[parse] +">";
                    else{
                        reConStr += eventDetails +">";
                        addUpdate = 0;
                    }}

            }
            userDetails.setCalendar(reConStr);
        }
        Boolean update = userInfo.updateUser(userDetails);
        return userInfo.findUser(user.getUserId()).get(0);
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
                Files.createDirectories(Path.of(userAdd.getImg()));
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
    public List<List<String>> pieChartView (@RequestParam("colName") String colName, @RequestParam("min") String min,
                                            @RequestParam("max")String max){
        ViewData viewData = new ViewData(customerInfo, colName, min, max);
        return viewData.getPieView();
    }

    @PostMapping("/BarGraphView")
    public List<List<String>> barGraphView (@RequestParam("colName") String colName, @RequestParam("min") String min,
                                            @RequestParam("max")String max){
        ViewData viewData = new ViewData(customerInfo, colName, min, max);
        return viewData.getBarGraphView();
    }

    @PostMapping("/TableView")
    public List<List<String>> tableView (@RequestParam("colName") String colName){
        ViewData viewData = new ViewData(customerInfo, colName);
        return viewData.getTableView();
    }

    @PostMapping("/Getmax")
    public int getMax (@RequestParam("colName") String colName){
        ViewData viewData = new ViewData(customerInfo, colName);
        return viewData.getSize();
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
