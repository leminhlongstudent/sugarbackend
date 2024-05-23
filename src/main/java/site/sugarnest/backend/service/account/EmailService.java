package site.sugarnest.backend.service.account;

import jakarta.mail.internet.InternetAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import site.sugarnest.backend.dto.dto.SendEmailDto;
import site.sugarnest.backend.entities.AccountEntity;
import site.sugarnest.backend.exception.AppException;
import site.sugarnest.backend.exception.ErrorCode;
import site.sugarnest.backend.reponsitoties.IAccountRepository;

import java.io.UnsupportedEncodingException;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private IAccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

    public void sendMail(String accountEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        InternetAddress fromAddress;
        try {
            fromAddress = new InternetAddress("20130253@st.hcmuaf.edu.vn", "SugarNest");
            message.setFrom(String.valueOf(fromAddress));
            message.setTo(accountEmail);
            message.setSubject(subject);
            message.setText("Mã xác thực: " + body);
            javaMailSender.send(message);
            System.out.println("Email sent");
        } catch (UnsupportedEncodingException e) {
            throw new AppException(ErrorCode.SEND_MAIL_FAILED);
        }
    }

    public void verifyMail(SendEmailDto sendEmailDto) {
        AccountEntity entity = accountRepository.findByEmail(sendEmailDto.getEmail())
                .orElseThrow(() -> new AppException(ErrorCode.EMAIL_NOT_EXITED));
        System.out.println(entity);
        System.out.println(entity.getEmail());
        try {
            if(entity.getVerificationCode().equals(passwordEncoder.encode(sendEmailDto.getVerificationCode())))
                entity.setEnabled("true");
            accountRepository.save(entity);
        } catch (Exception e) {
            throw new AppException(ErrorCode.VERIFICATION_ACCOUNT_INCORRECT_CODE);
        }
    }
}









