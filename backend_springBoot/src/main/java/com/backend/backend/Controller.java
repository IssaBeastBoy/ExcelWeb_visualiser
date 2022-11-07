package com.backend.backend;

import com.backend.backend.DB.userInfoTransactions;
import com.backend.backend.apps.calendar;
import com.backend.backend.apps.tickets;
import com.backend.backend.chart.*;
import com.backend.backend.processor.ingestSheet;
import com.backend.backend.processor.plotItems;
import com.backend.backend.uploads.FileResponse;
import com.backend.backend.uploads.FileStorageService;
import com.backend.backend.uploads.uploadFilesResponse;
import com.backend.backend.user.*;
import com.google.gson.Gson;
import org.apache.tomcat.util.json.ParseException;
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
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class Controller {

    private ingestSheet ingestSheet = new ingestSheet("", "");


    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping("/login/{id}")
    public userInformationEntity Login(@PathVariable(value = "id") int id){
        userInfoTransactions userInfo = new userInfoTransactions();
        List<userInformationEntity> user = userInfo.findUser(String.valueOf(id));
        if(user.size() == 1){
            userInformationEntity userDetails = user.get(0);
            userDetails.setStatus("Online");
            Boolean update = userInfo.updateUser(userDetails);
            return userInfo.findUser(String.valueOf(id)).get(0);
        }
        else{
            return null;
        }
    }

    @PostMapping("/getProfile")
    public userProfile getProfilePicture(@RequestParam("fileLoc") String directory){
        File dir = new File(directory);
        String[] dirContent = dir.list();
        String errMessage = "Error with loading profile picture. \n Default picture set.";
        String[] parseDir = directory.split("\\\\");
        String imgName = "";
        File curr = new File(directory);
        Boolean err = false;
        String imgLoc = "";
        Boolean addDir = false;
        if(curr.exists()){
            for (int parse=0; parse<parseDir.length && dirContent.length == 1; parse++)
            {
                imgName = dirContent[0].split("\\.")[0];
                err = true;
                if(addDir){
                    imgLoc += parseDir[parse]+"/";
                }
                else if(parseDir[parse].equals("data")){
                    //imgLoc += "../"+parseDir[parse]+"/";
                    addDir =  true;
                }
            }
            imgLoc+=imgName;
        }

        userProfile userProfile = new userProfile(err, errMessage, imgLoc);
        return userProfile;
    }

    @PostMapping("/updatePassword")
    public userInformationEntity updatePassword(@ModelAttribute updatedPassword user) {
        userInfoTransactions userInfo = new userInfoTransactions();
        userInformationEntity userDetails = userInfo.findUser(user.getUserName()).get(0);
        userDetails.setPassword(user.getNewPassword());
        Boolean update = userInfo.updateUser(userDetails);
        return userInfo.findUser(user.getUserName()).get(0);
    }

    @PostMapping("/updateProfile")
    public userInformationEntity updateProfile(@ModelAttribute updateProfile user) {
        userInfoTransactions userInfo = new userInfoTransactions();
        userInformationEntity userDetails = userInfo.findUser(user.getUserName()).get(0);
        if(!user.getStatus().equals(""))
            userDetails.setStatus(user.getStatus());
        if(!user.getContact().equals(""))
            userDetails.setContact(user.getContact());
        if(!user.getEmail().equals(""))
            userDetails.setStatus(user.getEmail());
        Boolean update = userInfo.updateUser(userDetails);
        return userInfo.findUser(user.getUserName()).get(0);
    }

    @PostMapping("/updatePicture")
    public pictureUpdate updatePicture(@ModelAttribute userPicture user) {
        Boolean err = false;
        String message = "";
        String imgLoc = "";
        try{
            String fileName = StringUtils.cleanPath(user.getImgFile().getOriginalFilename());
            fileName.toLowerCase();
            File dir = new File(user.getImgDir());
            String[] dirContent = dir.list();
            String[] parseDir = user.getImgDir().split("\\\\");
            for (int i=0; i<dirContent.length; i++)
            {
                File curr = new File(user.getImgDir()+dirContent[i]);
                if (curr.delete()) {
                    message += dirContent[i] + " successfully deleted. \n";
                } else {
                    message += "Error: \n Failed to delete " + dirContent[i];
                    err= true;
                }
            }
            if(!err){
                try{
                    message += "Profile picture updated: " + fileName;
                    Path userLocation = Path.of(user.getImgDir() + fileName);
                    Files.copy(user.getImgFile().getInputStream(), userLocation, StandardCopyOption.REPLACE_EXISTING);
                    Boolean addDir = false;
                    for (int parse=0; parse<parseDir.length && dirContent.length == 1; parse++)
                    {
                        if(addDir){
                            imgLoc += parseDir[parse]+"/";
                        }
                        else if(parseDir[parse].equals("data")){
                            //imgLoc += "../"+parseDir[parse]+"/";
                            addDir =  true;
                        }
                    }
                }
                catch (Exception ex){
                    message = ex.toString();
                }
            }
            imgLoc += fileName.split("\\.")[0];
        }
        catch (Exception ex){
            err= true;
            message = "Error: \n" + ex;
        }

        pictureUpdate update = new pictureUpdate(err, message, imgLoc);

        return update;
    }

    @PostMapping("/logout")
    public void userLogOut(@ModelAttribute userLogOut user) {
        userInfoTransactions userInfo = new userInfoTransactions();
        userInformationEntity userDetails = userInfo.findUser(user.getUserName()).get(0);
        userDetails.setStatus(user.getStatus());
        Boolean update = userInfo.updateUser(userDetails);
    }

//    @PostMapping("/updateNotes") still to be implemented
//    public userInformationEntity updateNotes(){
//
//    }


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
        String lasNumber = "VSuser";
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

    @PostMapping("/pivotTable")
    public pivotDetails pivotTable (@ModelAttribute pivotTableRequest details){
        if(!this.ingestSheet.getExcel_loc().equals(details.getExcelLoc()) ) {
            this.ingestSheet = new ingestSheet("", "");
            ingestSheet.setExcel_loc(details.getExcelLoc());
            ingestSheet.parseSheet();
        }

        int verticalIndex = ingestSheet.getCustomerDetails().indexOf(details.getVerticalSelect());
        int horizontalIndex = ingestSheet.getCustomerDetails().indexOf(details.getHorizontalSelect());

        pivotTableGen generatePivotTable = new pivotTableGen(verticalIndex, details.getVerticalSelect(),
                horizontalIndex, details.getHorizontalSelect(), ingestSheet.getCustomerInfo(), ingestSheet.getTotalCustomerCount(),
                details.getDisplayType());

        pivotDetails pivotDetails = new pivotDetails(generatePivotTable.getPivotTableValues(), ingestSheet.isThrowErr(), ingestSheet.getErr_Message(),
                generatePivotTable.getPivotTableBody(), ingestSheet.getTotalCustomerCount());

        return pivotDetails;
    }

    @PostMapping("/Table")
    public plotDetails table (@ModelAttribute plotRequestBody details){
        if(!this.ingestSheet.getExcel_loc().equals(details.getExcelLoc()) ) {
            this.ingestSheet = new ingestSheet(details.getFilterOne(), details.getFilterTwo());
            ingestSheet.setExcel_loc(details.getExcelLoc());
            ingestSheet.parseSheet();
        }

        plotDetails plotDetails = new plotDetails();
        if(!details.getFilterOne().equals("")){
            int index = ingestSheet.getIsColsValues().get(ingestSheet.getCustomerDetails().indexOf(details.getFilterOneSelect())+1).indexOf(details.getFilterOne());
            ingestSheet.getIsColsValues().get(ingestSheet.getCustomerDetails().indexOf(details.getFilterOneSelect())+1).remove(index);
            ingestSheet.getIsColsValues().get(ingestSheet.getCustomerDetails().indexOf(details.getFilterOneSelect())+1).add(0, details.getFilterOne());
            plotDetails.setFilterOneOptions(ingestSheet.getIsColsValues().get(ingestSheet.getCustomerDetails().indexOf(details.getFilterOneSelect())+1));
        }
        else{
            plotDetails.setFilterOneOptions(ingestSheet.getIsColsValues().get(ingestSheet.getCustomerDetails().indexOf(details.getFilterOneSelect())+1));

        }

        int filterOneIndex = ingestSheet.getCustomerDetails().indexOf(details.getFilterOneSelect());
        if(!details.getFilterTwo().equals("")){
            int index = ingestSheet.getIsColsValues().get(ingestSheet.getCustomerDetails().indexOf(details.getFilterTwoSelect())+1).indexOf(details.getFilterTwo());
            ingestSheet.getIsColsValues().get(ingestSheet.getCustomerDetails().indexOf(details.getFilterTwoSelect())+1).remove(index);
            ingestSheet.getIsColsValues().get(ingestSheet.getCustomerDetails().indexOf(details.getFilterTwoSelect())+1).add(0, details.getFilterTwo());
            plotDetails.setFilterTwoOptions(ingestSheet.getIsColsValues().get(ingestSheet.getCustomerDetails().indexOf(details.getFilterTwoSelect())+1));
        }
        else{
            plotDetails.setFilterTwoOptions(ingestSheet.getIsColsValues().get(ingestSheet.getCustomerDetails().indexOf(details.getFilterTwoSelect())+1));
        }
        int filterTwoIndex = ingestSheet.getCustomerDetails().indexOf(details.getFilterTwoSelect());

        List<String> tempMain = ingestSheet.getIsColsValues().get(ingestSheet.getCustomerDetails().indexOf(details.getMain())+1);
        int mainIndex = ingestSheet.getCustomerDetails().indexOf(details.getMain());
        Dictionary<String, Integer> mainItems = new Hashtable<>();

        List<Integer> tempOfferCount = new ArrayList<>();
        tempOfferCount.add(Integer.parseInt(details.getMainCount()));
        for(int parse=0; parse < ingestSheet.getIsColsValues().get(ingestSheet.getCustomerDetails().indexOf(details.getMain())+1).size(); parse++){
            if (!tempOfferCount.contains((ingestSheet.getIsColsValues().get(ingestSheet.getCustomerDetails().indexOf(details.getMain())+1).get(parse).length()/2))){
                tempOfferCount.add(ingestSheet.getIsColsValues().get(ingestSheet.getCustomerDetails().indexOf(details.getMain())+1).get(parse).length()/2);
            }
        }

        for(int parse=0; parse < tempMain.size(); parse++){
            if(tempMain.get(parse).length() == Integer.parseInt(details.getMainCount())*2){
                mainItems.put(tempMain.get(parse), 0);
            }
        }

        String selectedOne;
        String selectedTwo;

        if(details.getFilterOne().equals("") && details.getFilterTwo().equals("")){
            selectedOne = plotDetails.getFilterOneOptions().get(0);
            selectedTwo = plotDetails.getFilterTwoOptions().get(0);
        }
        else{
            selectedOne = details.getFilterOne();
            selectedTwo = details.getFilterTwo();
        }

        plotItems plotItems = new plotItems(ingestSheet.getCustomerInfo(), mainItems, mainIndex, filterOneIndex, filterTwoIndex,
                selectedOne,selectedTwo );

        plotDetails.setFilter(tempOfferCount);
        plotDetails.setItems(plotItems.getMainItems());
        plotDetails.setItemDisplay(plotItems.getItemDisplay());

        return plotDetails;
    }

    @PostMapping("/PieChartView")
    public List<List<String>> pieChartView (@RequestParam("colName") String colName, @RequestParam("min") String min,
                                            @RequestParam("max")String max){
        ViewData viewData = new ViewData(this.ingestSheet.getCustomerInfo(), colName, min, max);
        return viewData.getPieView();
    }

    @PostMapping("/BarGraphView")
    public List<List<String>> barGraphView (@RequestParam("colName") String colName, @RequestParam("min") String min,
                                            @RequestParam("max")String max){
        ViewData viewData = new ViewData(this.ingestSheet.getCustomerInfo(), colName, min, max);
        return viewData.getBarGraphView();
    }

    @PostMapping("/TableView")
    public List<List<String>> tableView (@RequestParam("colName") String colName){
        ViewData viewData = new ViewData(this.ingestSheet.getCustomerInfo(), colName);
        return viewData.getTableView();
    }

    @PostMapping("/Getmax")
    public int getMax (@RequestParam("colName") String colName){
        ViewData viewData = new ViewData(this.ingestSheet.getCustomerInfo(), colName);
        return viewData.getSize();
    }

    @PostMapping("/Excel_sheet")
    public List<String> inputSheet (@RequestParam("sheetLoc") String sheetLoc){
        if(!this.ingestSheet.getExcel_loc().equals(sheetLoc) ) {
            this.ingestSheet = new ingestSheet("", "");
            ingestSheet.setExcel_loc(sheetLoc);
            ingestSheet.parseSheet();
        }

        List<String> excludeTotal = new ArrayList<>();
        for(int index = 0; index < this.ingestSheet.getCustomerInfo().get(-1).size()-1; index++){
            excludeTotal.add(this.ingestSheet.getCustomerInfo().get(-1).get(index));
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

    @PostMapping("/getColumnName")
    public columnNames getColumns (@RequestParam(value="fileLoc") String fileDir){
        ingestSheet ingest = new ingestSheet("","");
        ingest.setExcel_loc(fileDir);
        ingest.parseColumns();
        columnNames columnNames = new columnNames();
        columnNames.setColumnNames(ingest.getCustomerDetails());
        columnNames.setErr(ingest.isThrowErr());
        columnNames.setErrMessage(ingest.getErr_Message());
        return columnNames;
    }


    @PostMapping("/getUploadFiles")
    public uploadFileName getNames (@RequestParam(value="fileLoc") String fileLoc){
        List<String> files = new ArrayList<>();
        File dir = new File(fileLoc);
        String[] dirContent = dir.list();
        for (int i=0; i<dirContent.length; i++)
        {
            File curr = new File(fileLoc+dirContent[i]);
            Boolean check = curr.exists();
            if(check)
                files.add(dirContent[i]);
        }
        uploadFileName fileName = new uploadFileName();
        fileName.setFilesName(files);
        return fileName;
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
