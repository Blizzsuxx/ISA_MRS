package mrsisa.projekat.util;

import mrsisa.projekat.korisnik.Korisnik;
import mrsisa.projekat.poseta.PosetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.net.PasswordAuthentication;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

@CrossOrigin
@RestController
@RequestMapping(path="api/v1/mail")
public class MailSender {
    @Autowired
    public MailSender(){

    }

    @PostMapping(value = "/sendemail")
    public Boolean sendEmail(@RequestBody Map<String, String> info) {
        try {
            sendmail(info.get("text"), info.get("address"));
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    @PostMapping(value = "/sendemailRegistration")
    public Boolean sendEmailRegistration() {
        try {
            sendmail("Test", "dunjica.isa@gmail.com");
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    @PostMapping(value = "/slanjeMejla")
    public static void sendmail(String text, String sendT0) throws AddressException, MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {

                return new javax.mail.PasswordAuthentication("doktorski.sistem@gmail.com", "IsaMrs123");
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("doktorski.sistem@gmail.com", false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(sendT0));
        msg.setSubject("Poslednji Trzaj");
        msg.setContent("Poslednji Trzaj", "text/html");
        msg.setSentDate(new Date());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(text, "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        MimeBodyPart attachPart = new MimeBodyPart();

        multipart.addBodyPart(attachPart);
        msg.setContent(multipart);
        msg.setText(text);

        Transport.send(msg);
    }
}