package com.github.huifer.ctrpluginexample.entity;

import com.github.huifer.ctrpluginexample.ann.CtrPlugin;
import com.github.huifer.ctrpluginexample.api.InsertOrUpdateConvertImpl;
import com.github.huifer.ctrpluginexample.req.AppAddParam;
import java.sql.Timestamp;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@CtrPlugin(
        uri = "app",
        insertParamClazz = AppAddParam.class,
        updateParamClazz = AppAddParam.class,
        INSERT_OR_UPDATE_CONVERT = InsertOrUpdateConvertImpl.class
)
@Entity
@Table(name = "app", schema = "rbac", catalog = "")
public class AppEntity {

    private Long id;
    private String name;
    private Long version;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Integer createUser;
    private Integer updateUser;
    private Short deleted;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "version")
    @Version
    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "update_time")
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "create_user")
    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    @Basic
    @Column(name = "update_user")
    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

    @Basic
    @Column(name = "deleted")
    public Short getDeleted() {
        return deleted;
    }

    public void setDeleted(Short deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AppEntity appEntity = (AppEntity) o;

        if (id != null ? !id.equals(appEntity.id) : appEntity.id != null) {
            return false;
        }
        if (name != null ? !name.equals(appEntity.name) : appEntity.name != null) {
            return false;
        }
        if (version != null ? !version.equals(appEntity.version) : appEntity.version != null) {
            return false;
        }
        if (createTime != null ? !createTime.equals(appEntity.createTime)
                : appEntity.createTime != null) {
            return false;
        }
        if (updateTime != null ? !updateTime.equals(appEntity.updateTime)
                : appEntity.updateTime != null) {
            return false;
        }
        if (createUser != null ? !createUser.equals(appEntity.createUser)
                : appEntity.createUser != null) {
            return false;
        }
        if (updateUser != null ? !updateUser.equals(appEntity.updateUser)
                : appEntity.updateUser != null) {
            return false;
        }
        return deleted != null ? deleted.equals(appEntity.deleted) : appEntity.deleted == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (createUser != null ? createUser.hashCode() : 0);
        result = 31 * result + (updateUser != null ? updateUser.hashCode() : 0);
        result = 31 * result + (deleted != null ? deleted.hashCode() : 0);
        return result;
    }
}
