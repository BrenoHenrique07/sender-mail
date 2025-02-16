package br.com.nobre.sender_mail.service;

import br.com.nobre.sender_mail.dto.AttachmentDto;
import br.com.nobre.sender_mail.dto.MailListDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class SenderMailService {

    private final JavaMailSender javaMailSender;
    private final String from;

    public SenderMailService(JavaMailSender javaMailSender, @Value("${spring.mail.username}") String from) {
        this.javaMailSender = javaMailSender;
        this.from = from;
    }

    //TODO deixar assíncrono
    public void sendEmail(MailListDto mailListDto) throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        String[] to = mailListDto.recipients().toArray(new String[0]);

        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(mailListDto.subject());
        helper.setText(mailListDto.text(), true);

        AttachmentDto attachmentDto = mailListDto.attachment();

        //TODO verificações - extensão do nome e do path iguais
        if(attachmentDto.path() != null && !attachmentDto.path().isEmpty()) {
            File file = getFile(attachmentDto.path());
            helper.addAttachment(attachmentDto.name(), file);
        }

        javaMailSender.send(mimeMessage);
    }

    private File getFile(String attachmentPath) {

        File file = new File(attachmentPath);

        if(!file.exists()) {
            //TODO exception
        }

        return file;

    }

}
