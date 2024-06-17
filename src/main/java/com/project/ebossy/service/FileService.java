package com.project.ebossy.service;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService {

    public String saveFile(MultipartFile file, String dirPath, String filename) throws IOException {
        if(file == null) return null;

        byte[] bytes = file.getBytes();
        Path path = Paths.get("uploads/" + dirPath + filename);
        Files.write(path, bytes);
        return dirPath + filename;
    }
}
