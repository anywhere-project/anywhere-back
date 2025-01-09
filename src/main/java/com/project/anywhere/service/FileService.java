package com.project.anywhere.service;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    List<String> upload(List<MultipartFile> files);
    Resource getFile(String fileName);
    
}