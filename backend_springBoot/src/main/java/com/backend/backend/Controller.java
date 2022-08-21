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
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class Controller {

    @Autowired
    private FileStorageService fileStorageService;

//    @GetMapping
//    @RequestMapping("CheckUser")
//    public List<String> existingDetails(@RequestParam("CheckUser") )

    @PutMapping
    @RequestMapping("file")
    public ResponseEntity<FileResponse> uploadFile(@RequestParam("file") MultipartFile file){
        String fileName = fileStorageService.storeFile(file);
        String fileDownUri = ServletUriComponentsBuilder.fromCurrentRequestUri().
                path("/uploads/")
                .path(fileName)
                .toUriString();
        FileResponse fileResponse = new FileResponse(fileName, fileDownUri, file.getContentType(), file.getSize());
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
