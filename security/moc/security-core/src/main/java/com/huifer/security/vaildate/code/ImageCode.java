package com.huifer.security.vaildate.code;

import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 描述:
 * 图形验证码
 *
 * @author: huifer
 * @date: 2019-11-17
 */
public class ImageCode {
    private BufferedImage image;
    private String code;
    private LocalDateTime expireTime;

    public ImageCode() {
    }

    public ImageCode(BufferedImage image, String code, int expireTime) {
        this.image = image;
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireTime);
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
