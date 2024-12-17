package com.project.anywhere.service.implement;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.anywhere.service.FileService;

@Service
public class FileServiceImplement implements FileService {

    @Value("${file.path}")
    private String filePath;

    @Value("${file.url}")
    private String fileUrl;

    @Override
    public List<String> upload(List<MultipartFile> files) {
        List<String> fileUrls = new ArrayList<>();

        // description: 각 파일에 대해 처리 //
        for (MultipartFile file : files) {
            // 빈 파일인지 확인 //
            if (file.isEmpty()) {
                fileUrls.add(null);
                continue;
            }

            // description: 원본 파일명 구하기 //
            String originalFileName = file.getOriginalFilename();
            // description: 확장자 구하기 //
            String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
            // description: UUID 형식의 임의의 파일명 생성 //
            String uuid = UUID.randomUUID().toString();
            String saveFileName = uuid + extension;
            // description: 파일이 저장될 경로 //
            String savePath = filePath + saveFileName;
            
            try {
                // description: 파일 저장 //
                file.transferTo(new File(savePath));
                // description: 파일을 불러올 수 있는 경로 반환 //
                String url = fileUrl + saveFileName;
                fileUrls.add(url);
            } catch (Exception e) {
                e.printStackTrace();
                fileUrls.add(null); // 오류 발생 시 null 추가
            }
        }

        return fileUrls;
    }

    @Override
    public List<Resource> getFiles(List<String> fileNames) {
        List<Resource> resources = new ArrayList<>();

        for (String fileName : fileNames) {
            try {
                // description: 파일 저장 경로에 있는 파일명에 해당하는 파일 불러오기 //
                Resource resource = new UrlResource("file:" + filePath + fileName);
                resources.add(resource);
            } catch (Exception exception) {
                exception.printStackTrace();
                resources.add(null); // 오류 발생 시 null 추가
            }
        }

        return resources;
    }

    @Override
    public Resource getFile(String fileName) {
        
        Resource resource = null;

        try {
            resource = new UrlResource("file:" + filePath + fileName);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }

        return resource;
    }

}
