package com.backend.backend.uploads;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileStorageService {
    private final Path fileStorageLocation;

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath();
    }

    // File storage function
    public  String storeFile(MultipartFile file, String LasNumber){
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            Path targetlocation = this.fileStorageLocation.resolve(fileName);
            return fileName;
        } catch (Exception e) {
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
