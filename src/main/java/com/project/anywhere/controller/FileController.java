package com.project.anywhere.controller;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.anywhere.service.FileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class FileController {
    
    private final FileService fileService;

    @PostMapping("/upload")
    public List<String> upload(@RequestParam("file") List<MultipartFile> files) {
        List<String> urls = fileService.upload(files);
        return urls;
    }

    // @GetMapping(value = "/{fileName}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
    // public List<Resource> getImageFiles(@RequestParam("fileName") List<String> fileNames) {
    //     return fileService.getFiles(fileNames);
    // }

    @GetMapping(value="/{fileName}", produces={MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
    public Resource getImageFile(
        @PathVariable("fileName") String fileName
    ) {
        Resource resource = fileService.getFile(fileName);
        return resource;
    }

}