package br.com.nobre.sender_mail.dto;

import java.util.List;

public record MailListDto(List<String> recipients, String subject, String text, AttachmentDto attachment) {

    public MailListDto {
        if (recipients == null || recipients.isEmpty()) {
            throw new IllegalArgumentException("A lista de destinatários não pode ser vazia.");
        }
        if (subject == null || subject.isBlank()) {
            throw new IllegalArgumentException("O assunto não pode ser vazio.");
        }
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException("O corpo do e-mail não pode ser vazio.");
        }
    }
}
