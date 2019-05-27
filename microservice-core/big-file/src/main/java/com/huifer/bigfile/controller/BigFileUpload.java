package com.huifer.bigfile.controller;

import com.huifer.bigfile.config.UpLoadFileConfig;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>Title : BigFileUpload </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-27
 */
@RestController
@RequestMapping("/bg")
@CrossOrigin
public class BigFileUpload {


    /**
     * 切片上传
     *
     * @param file 文件
     * @param ic 文件编号
     * @param guid 存储文件夹
     * @param prefix 后缀
     */
    @PostMapping("/")
    public void upload(
            MultipartFile file,
            String ic, String guid,
            String prefix
    ) {
        try {

            String filepath = UpLoadFileConfig.uploadPath;
            String tempFileDir = filepath + guid;

            File parentFileDir = new File(tempFileDir);
            if (!parentFileDir.exists()) {
                parentFileDir.mkdirs();
            }
            File tempPartFile = new File(parentFileDir, guid + "_" + ic + ".part");
            FileUtils.copyInputStreamToFile(file.getInputStream(), tempPartFile);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 文件合并返回
     *
     * @param fileName 文件名称
     * @param guid 融合那个文件夹下的
     * @param prefix 后缀
     */
    @GetMapping("/merge")
    public void mergeFile(String fileName, String guid, String prefix) {
        String filePath = UpLoadFileConfig.uploadPath;

        try {
            File parentFileDir = new File(filePath + guid);
            if (parentFileDir.isDirectory()) {
                File destTempFile = new File(filePath + "/merge", fileName + "." + prefix);
                if (!destTempFile.exists()) {

                    destTempFile.getParentFile().mkdir();
                    try {
                        destTempFile.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                for (int i = 0; i < parentFileDir.listFiles().length; i++) {
                    File partFile = new File(parentFileDir, guid + "_" + i + ".part");

                    FileOutputStream destTempfos = new FileOutputStream(destTempFile, true);
                    FileUtils.copyFile(partFile, destTempfos);
                    destTempfos.close();
                }
                FileUtils.deleteDirectory(parentFileDir);
                System.out.println();

//                fileService.save(
//                        new FileInfo(destTempFile, destTempFile.getName(), uploadPath, new Date(), prefix)
//
//                )

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
