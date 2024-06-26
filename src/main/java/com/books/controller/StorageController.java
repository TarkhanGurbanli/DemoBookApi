package com.books.controller;

import com.books.constants.Constants;
import com.books.dto.ResponseDto;
import com.books.entity.PhotoFile;
import com.books.service.StorageService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file/")
@AllArgsConstructor
public class StorageController {

    private static final Logger log = LoggerFactory.getLogger(StorageController.class);
    private final StorageService storageService;

    @PostMapping("upload")
    public ResponseEntity<ResponseDto> uploadFile(@RequestParam(value = "file") MultipartFile file) {
        storageService.uploadFile(file);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(Constants.STATUS_200, Constants.FILE_UPLOAD_MSG));
    }

    @GetMapping("download/{fileName}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable(value = "fileName") String fileName) {
        byte[] data = storageService.downloadFile(fileName);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity.ok()
                .contentLength(data.length)
                .header("Content-Type", "application/octet-stream")
                .header("Content-Disposition", "attachment; filename=\"" + fileName +"\"")
                .body(resource);
    }

    @GetMapping("getAll")
    public ResponseEntity<Page<PhotoFile>> getAll(@RequestParam int size, @RequestParam int pageSize){
        Page<PhotoFile> result = storageService.getAllPhotos(size, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("delete/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable(value = "fileName") String fileName) {
        return new ResponseEntity<>(storageService.deleteFile(fileName), HttpStatus.OK);
    }
}
