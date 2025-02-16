package br.com.nobre.sender_mail.controller;

import br.com.nobre.sender_mail.dto.MailListDto;
import br.com.nobre.sender_mail.service.SenderMailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/mail")
public class MailController {

    private final SenderMailService senderMailService;

    public MailController(SenderMailService senderMailService) {
        this.senderMailService = senderMailService;
    }

    @PostMapping("/send/list")
    public void sendMailToList(@RequestBody MailListDto mailListDto) throws MessagingException {

        senderMailService.sendEmail(mailListDto);

    }

}
