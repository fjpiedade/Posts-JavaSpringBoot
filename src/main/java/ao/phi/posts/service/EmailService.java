package ao.phi.posts.service;

import ao.phi.posts.repository.EmailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService implements EmailSender {
    private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender mailSender;

    @Override
    @Async
    public void send(String to, String email) {
        try {
            //System.out.println(to);
            //System.out.println(email);
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject("Confirm your Email");
            helper.setFrom("phi@raiztech.com");
            mailSender.send(mimeMessage);

        } catch(MessagingException ex){
            LOGGER.error("Failed to send email", ex);
            throw new IllegalStateException("Failed to send email");
        }
    }
}
