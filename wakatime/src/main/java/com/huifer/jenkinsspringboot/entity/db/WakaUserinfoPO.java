package com.huifer.jenkinsspringboot.entity.db;

import lombok.Data;

import java.util.Date;

@Data
public class WakaUserinfoPO {
    private String apiKey;

    private Date createdAt;

    private String displayName;

    private String email;

    private String emailPublic;

    private String fullName;

    private String hasPremiumFeatures;

    private String humanReadableWebsite;

    private String id;

    private String isEmailConfirmed;

    private String isHireable;

    private String languagesUsedPublic;

    private String lastHeartbeatAt;

    private String lastPlugin;

    private String lastPluginName;

    private String lastProject;

    private String location;

    private String loggedTimePublic;

    private Date modifiedAt;

    private String needsPaymentMethod;

    private String photo;

    private String photoPublic;

    private String plan;

    private String timezone;

    private String username;

    private String website;

    private Date updateTime;
}