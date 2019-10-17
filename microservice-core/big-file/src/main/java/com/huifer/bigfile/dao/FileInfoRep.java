package com.huifer.bigfile.dao;

import com.huifer.bigfile.pojo.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>Title : FileInfoRep </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-27
 */

@Repository
public interface FileInfoRep extends JpaRepository<FileInfo, Long> {

    FileInfo deleteByPath(String path);
}
