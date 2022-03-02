package com.ducterry.base.service.email;

import com.ducterry.base.dto.base.ResponseObject;
import com.ducterry.base.dto.email.request.AttachFileRq;
import com.ducterry.base.dto.email.request.SendEmailRq;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailService {
    private final Logger LOGGER = LogManager.getLogger(this.getClass());
    private final String PREFIX = "[EmailService]_";

    @Value("${spring.mail.username}")
    private String sendFrom;

    @Autowired
    private JavaMailSender mailSender;


    public ResponseObject<Object> sendEmailTemplate(SendEmailRq request) {
        try {
            LOGGER.debug(PREFIX + "sendEmailTemplate => {}", request);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(sendFrom);
            message.setTo(request.getToEmail());
            message.setSubject(request.getSubject());
            message.setText(request.getBody());

            mailSender.send(message);

            request.setMessage("Send Email Successfully!");
            return new ResponseObject<>(true, request);
        } catch (Exception ex) {
            throw ex;
        }
    }


    @SneakyThrows
    public ResponseObject<Object> sendEmailAttachFile(AttachFileRq request) {
        try {
            LOGGER.debug(PREFIX + "sendEmailAttachFile => {}", request);
            FileSystemResource attachFile = new FileSystemResource(new File(request.getAttachFile()));


            MimeMessage mimeMessage = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setFrom(sendFrom);
            helper.setTo(request.getToEmail());
            helper.setSubject(request.getSubject());
            helper.setText(request.getBody());
            helper.addAttachment(attachFile.getFilename(), attachFile);


            mailSender.send(mimeMessage);

            request.setMessage("Send Email Successfully!");
            return new ResponseObject<>(true, request);
        } catch (Exception ex) {
            throw ex;
        }
    }
}
