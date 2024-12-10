package com.project.anywhere.controller;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
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
public class FileController {
    
    private final FileService fileService;

    @PostMapping("/upload")
    public List<String> upload(
        @RequestParam("files") List<MultipartFile> files
    ) {
        List<String> fileUrls = fileService.upload(files);
        return fileUrls;
    }

    @GetMapping(value="/{fileName}", produces={MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
    public List<Resource> getImageFiles(
        @RequestParam("fileNames") List<String> fileNames
    ) {
        return fileService.getFiles(fileNames);
    }

}