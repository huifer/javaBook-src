package com.huifer.bigfile.controller;

import com.huifer.bigfile.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>Title : FileUplad </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-27
 */
@RestController
@RequestMapping("/fileupload")
@CrossOrigin
public class FileUplad {

    @Autowired
    private FileService fileService;


    @PostMapping("/")
    public void upload(String name, MultipartFile file) throws Exception {
        fileService.upload(name, file);
    }


}
