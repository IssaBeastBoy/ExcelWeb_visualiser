package com.backend.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {
    private final Path fileStorageLocation;

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath();
        try{
            Files.createDirectories(this.fileStorageLocation);
            String classpath = System.getProperty("user.dir");
            System.out.println(classpath);
            System.out.println(this.fileStorageLocation.toString());
        }catch(Exception ex){
            throw  new FileStorageException("Error uploading file");
        }
    }

    // File storage function
    public  String storeFile(MultipartFile file, String LasNumber){
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            Path targetlocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetlocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (IOException e) {
            throw  new FileStorageException("File(s) failed to store "+fileName+" on DB. Please try again!", e);
        }
    }

    // File retrieval function
    public  UrlResource loadFileResource(String fileName){
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            UrlResource resource =  new UrlResource(filePath.toUri());
            if(resource.exists()){
                return resource;
            }
            else{
                throw new lookUPFileNotFoundException("File not found " + fileName);
            }
        }
        catch (lookUPFileNotFoundException | MalformedURLException exTwo){
            throw new lookUPFileNotFoundException("File not found " + fileName);
        }
    }
}
