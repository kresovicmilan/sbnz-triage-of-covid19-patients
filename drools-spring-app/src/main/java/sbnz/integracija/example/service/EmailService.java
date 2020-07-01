package sbnz.integracija.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailService {

	@Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("isa.psw.tim17@gmail.com");
        mailSender.setPassword("Krokodil123$");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    /*
     * Koriscenje klase za ocitavanje vrednosti iz application.properties fajla
     */
    @Autowired
    private Environment env;
    
    public void sendEmail(String receiver, String subject, String content) throws MailException, MessagingException{
    	 MimeMessage mimi = getJavaMailSender().createMimeMessage();
         MimeMessageHelper helper = new MimeMessageHelper(mimi, "utf-8");
         String htmlMsg = "<h3>" + "Hello " + receiver + ",<br></br>" + content + "</h3>";

         helper.setText(htmlMsg, true); // Use this or above line.
         helper.setTo(receiver);
         helper.setSubject(subject);
         helper.setFrom("isa.psw.tim17@gmail.com");
         getJavaMailSender().send(mimi);


         System.out.println("Email poslat!");
    }
}
