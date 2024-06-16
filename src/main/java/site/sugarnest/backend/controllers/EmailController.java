package site.sugarnest.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import site.sugarnest.backend.dto.response.ApiResponse;
import site.sugarnest.backend.dto.dto.EmailRequestDto;
import site.sugarnest.backend.dto.dto.SendEmailDto;
import site.sugarnest.backend.service.account.EmailService;

@Controller
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send_email")
    public ApiResponse<String> sendEmail(@RequestBody EmailRequestDto emailRequest) {
        emailService.sendMail(emailRequest.getAccountEmail(), emailRequest.getSubject(), emailRequest.getBody());
        return ApiResponse.<String>builder()
                .message("Email sent successfully")
                .build();
    }

    @PostMapping("/verify_code")
    public ApiResponse<String> verifyCode(@RequestBody SendEmailDto sendEmailDto) {
        emailService.verifyMail(sendEmailDto);
        return ApiResponse.<String>builder()
                .message("Correct the verification code")
                .build();
    }
}
