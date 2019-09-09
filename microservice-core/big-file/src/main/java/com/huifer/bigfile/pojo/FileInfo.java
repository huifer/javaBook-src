package com.huifer.bigfile.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title : FileInfo </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FileInfo implements Serializable {

    private static final long serialVersionUID = -1428891800897624020L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "md5")
    private String md5;
    @Column(name = "path")
    private String path;
    @Column(name = "uploadTime")
    private Date uploadTime;
    @Column(name = "type")
    private String type;


    public FileInfo(String name, String md5, String path, Date uploadTime, String type) {
        this.name = name;
        this.md5 = md5;
        this.path = path;
        this.uploadTime = uploadTime;
        this.type = type;
    }
}
