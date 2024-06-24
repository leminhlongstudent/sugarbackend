package site.sugarnest.backend.service.payment;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.sugarnest.backend.dto.request.PaymentRequest;
import site.sugarnest.backend.entities.AccountEntity;
import site.sugarnest.backend.reponsitoties.IAccountRepository;
import site.sugarnest.backend.service.account.AccountService;


import java.util.*;

@Service
public class PayPalService {
    @Autowired
    private IAccountRepository iAccountRepository;
    @Autowired
    private AccountService accountService;

    @Autowired
    private APIContext apiContext;

    public Map<String, String> createPayment(PaymentRequest paymentRequest) {
        Amount amount = new Amount();
        amount.setCurrency(paymentRequest.getCurrency());
        amount.setTotal(String.format("%.2f", paymentRequest.getTotal()));

        Transaction transaction = new Transaction();
        transaction.setDescription(paymentRequest.getDescription());
        transaction.setAmount(amount);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod(paymentRequest.getMethod());

        Payment payment = new Payment();
        payment.setIntent(paymentRequest.getIntent());
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("https://sugarnest.netlify.app/cart");
        redirectUrls.setReturnUrl("https://sugarnest.netlify.app/checkout");
        payment.setRedirectUrls(redirectUrls);

        try {
            Payment createdPayment = payment.create(apiContext);
            String approvalUrl = null;
            for (Links link : createdPayment.getLinks()) {
                if (link.getRel().equals("approval_url")) {
                    approvalUrl = link.getHref();
                    break;
                }
            }
            Map<String, String> response = new HashMap<>();
            response.put("approvalUrl", approvalUrl);
            return response;
        } catch (PayPalRESTException e) {
            e.printStackTrace();
            return Collections.singletonMap("error", e.getMessage());
        }
    }

    public String executePayment(String paymentId, String payerId) {
        Payment payment = new Payment();
        payment.setId(paymentId);


        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);
        try {
            Payment executedPayment = payment.execute(apiContext, paymentExecution);
            if (executedPayment.getState().equals("approved")) {


                System.out.println("Payment successful");
            }
            return "Payment successful: " + executedPayment;
        } catch (PayPalRESTException e) {
            e.printStackTrace();
            return "Payment failed: " + e.getMessage();
        }
    }
}
