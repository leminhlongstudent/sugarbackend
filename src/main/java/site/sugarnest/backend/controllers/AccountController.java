package site.sugarnest.backend.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import site.sugarnest.backend.dto.dto.ApiResponse;
import site.sugarnest.backend.dto.request.AccountRequest;
import site.sugarnest.backend.dto.response.AccountResponse;
import site.sugarnest.backend.service.account.IAccountService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {

    private IAccountService iAccountService;

    @PostMapping("register")
    public ApiResponse<String> createAccount(@RequestBody AccountRequest accountDto) {
        iAccountService.createAccount(accountDto);
        return ApiResponse.<String>builder()
                .code(200)
                .message("Please enter your verification code")
                .build();
    }

    @GetMapping("/all")
    public ApiResponse<List<AccountResponse>> getAllAccount() {
        return ApiResponse.<List<AccountResponse>>builder()
                .code(200)
                .message("Success")
                .result(iAccountService.findAll())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<AccountResponse> getAccountById(@PathVariable Long id) {
        return ApiResponse.<AccountResponse>builder()
                .code(200)
                .message("Success")
                .result(iAccountService.findById(id))
                .build();
    }

    @GetMapping("/myInfo")
    public ApiResponse<AccountResponse> getMyInfo() {
        return ApiResponse.<AccountResponse>builder()
                .code(200)
                .message("Success")
                .result(iAccountService.getMyInfo())
                .build();
    }

}
