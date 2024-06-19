package com.huifer.bigfile.services;

import com.huifer.bigfile.config.UpLoadFileConfig;
import com.huifer.bigfile.dao.FileInfoRep;
import com.huifer.bigfile.pojo.FileInfo;
import com.huifer.bigfile.utils.DownloadFileUtils;
import com.huifer.bigfile.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * <p>Title : FileService </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-27
 */
@Service
public class FileService {

    @Autowired
    private FileInfoRep fileInfoRep;

    public FileInfo save(FileInfo fileInfo) {
        FileInfo save = fileInfoRep.save(fileInfo);
        return save;
    }


    public FileInfo upload(String name,
                           MultipartFile file) throws Exception {
        String filename = file.getOriginalFilename();

        String prefix = filename.substring(filename.lastIndexOf(".") + 1);

        String uploadPath =
                UpLoadFileConfig.uploadPath + DownloadFileUtils.generateFileName() + "." + prefix;
        DownloadFileUtils.write(
                uploadPath,
                file.getInputStream()
        );
        String md5 = MD5Utils.getMD5(filename);
        return save(
                new FileInfo(name, md5, uploadPath, new Date(), prefix)
        );
    }


}
