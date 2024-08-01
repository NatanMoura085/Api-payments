package com.api_payments.domain.service;

import com.api_payments.domain.model.Transaction;
import com.api_payments.utils.DateUtils;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private SpringTemplateEngine templateEngine;
    @Value("${spring.mail.username}")
    private String remetente;

    public void sendEmail(String to, String subject, Map<String, Object> templateModel) throws MessagingException {
        Context context = new Context();
        context.setVariables(templateModel);
        String htmlBody = templateEngine.process("emailTemplate", context);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        helper.setFrom(remetente);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlBody, true);

        ClassPathResource imageResource = new ClassPathResource("static/picpayIMG.png");
        helper.addInline("image",imageResource);
        javaMailSender.send(mimeMessage);
    }


    public Map<String, Object> populations(Transaction transaction) {
        Map<String, Object> populando = new HashMap<>();
        populando.put("nome",transaction.getReceivedID().getNomeCompleto());
        populando.put("valor", transaction.getValor());
        populando.put("data", DateUtils.formatador(String.valueOf(transaction.getDataTransaction())));
        populando.put("beneficiario", transaction.getReceivedID().getNomeCompleto());
        populando.put("destinatario", transaction.getSenderID().getNomeCompleto());
        return populando;

    }

}
