package com.huifer.emailtask.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * <p>Title : EmailA </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-27
 */
public class EmailA {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String username;

    public void setMailSender(String to, String subject, String context) throws Exception {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(username);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(context);

        mailSender.send(simpleMailMessage);
    }


}
