package site.sugarnest.backend.service.account;

import site.sugarnest.backend.dto.dto.PasswordChangeRequest;
import site.sugarnest.backend.dto.request.AccountRequest;
import site.sugarnest.backend.dto.response.AccountResponse;
import site.sugarnest.backend.entities.AccountEntity;

import java.util.List;

public interface IAccountService {
    void createAccount(AccountRequest accountDto);

    void editAccount(Long id, AccountRequest accountDto);

    void editMyAccount(AccountRequest accountDto);

    void editMyPassword(PasswordChangeRequest passwordChangeRequest);

    List<AccountResponse> findAll();

    AccountResponse findById(Long id);

    AccountResponse getMyInfo();

    boolean checkExistedEmail(String email);

    void deleteAccount(Long id);

    AccountEntity getAccount();
}
