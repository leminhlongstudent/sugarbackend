package site.sugarnest.backend.service.account;

import site.sugarnest.backend.dto.request.AccountRequest;
import site.sugarnest.backend.dto.response.AccountResponse;

import java.util.List;

public interface IAccountService{
    void createAccount(AccountRequest accountDto);
    List<AccountResponse> findAll();
    AccountResponse findById(Long id);
    AccountResponse getMyInfo();
}
