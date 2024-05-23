package site.sugarnest.backend.service.account;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import site.sugarnest.backend.dto.request.AccountRequest;
import site.sugarnest.backend.dto.response.AccountResponse;
import site.sugarnest.backend.entities.AccountEntity;
import site.sugarnest.backend.exception.AppException;
import site.sugarnest.backend.exception.ErrorCode;
import site.sugarnest.backend.mapper.IAccountMapper;
import site.sugarnest.backend.reponsitoties.IAccountRepository;
import site.sugarnest.backend.reponsitoties.IRoleRepository;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;


@Slf4j
@Service
@AllArgsConstructor
public class AccountService implements IAccountService {

    private IAccountRepository iAccountRepository;
    private IAccountMapper iAccountMapper;
    private EmailService emailService;
    IRoleRepository roleRepository;

    public void createAccount(AccountRequest accountDto) {
        if (iAccountRepository.findByEmail(accountDto.getEmail()).isPresent()) {
            throw new AppException(ErrorCode.ACCOUNT_EXITED);
        }
        if (iAccountRepository.findByPhone(accountDto.getPhone()).isPresent()) {
            throw new AppException(ErrorCode.PHONE_EXISTED);
        }

        AccountEntity accountEntity = iAccountMapper.mapToAccountEntity(accountDto);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        accountEntity.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        accountEntity.setCurrentPassword(passwordEncoder.encode(accountDto.getPassword()));

        accountEntity.setIsDelete("false");
        accountEntity.setIsActive("true");
        accountEntity.setCreateAt();
        accountEntity.setUpdateAt();
        accountEntity.setNumber_login_fail(0);
        accountEntity.setEnabled("false");
        // Set role
        accountEntity.setRoles(new HashSet<>(Collections.singletonList(roleRepository.findById("USER").orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_EXITED)))));

        String verificationCode = UUID.randomUUID().toString();
        accountEntity.setVerificationCode(passwordEncoder.encode(verificationCode));
        emailService.sendMail(accountDto.getEmail(), "Xác thực email", verificationCode);

        iAccountRepository.save(accountEntity);

        iAccountMapper.mapToAccountDto(accountEntity);
    }

//    @PreAuthorize("hasAuthority('APPROVE_POST')")
    @Override
    public List<AccountResponse> findAll() {
        List<AccountEntity> accountEntities = iAccountRepository.findAll();
        List<AccountResponse> accountDtos = accountEntities.stream().map(iAccountMapper::mapToAccountDto).toList();
        return accountDtos;
    }

//    @PostAuthorize("returnObject.email == authentication.name")
    @Override
    public AccountResponse findById(Long id) {
        AccountEntity accountEntity = iAccountRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOT_EXITED));
        return iAccountMapper.mapToAccountDto(accountEntity);
    }

    @Override
    public AccountResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String accountName = context.getAuthentication().getName();
        AccountEntity accountEntity = iAccountRepository.findByAccountName(accountName)
                .orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOT_EXITED));
        return iAccountMapper.mapToAccountDto(accountEntity);
    }


    public void updateAccount(AccountResponse accountDto) {
        AccountEntity accountEntity = iAccountRepository.findByEmail(accountDto.getEmail())
                .orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_EXITED));
    }
}
