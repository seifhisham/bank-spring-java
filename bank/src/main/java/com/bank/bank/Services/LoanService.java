package com.bank.bank.Services;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bank.bank.Models.Loan;
import com.bank.bank.Models.User;
import com.bank.bank.Models.Loan.LoanStatus;

@Service
public class LoanService {
    
    private RestTemplate restTemplate;  
    private String baseUrl="http://localhost:8081";

    public LoanService()
    {
        this.restTemplate=new RestTemplate();
    }

    public List<Loan> findAllByRelatedAccountUser(User user) {
        String url = baseUrl + "/loan/loans?userId=" + user.getId();
        return restTemplate.getForObject(url, List.class);
    }
    
    public List<Loan> findAll() {
        String url = baseUrl + "/loan/loans";
        ResponseEntity<List<Loan>> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<Loan>>() {}
        );
        return response.getBody();
    }
    

    public Loan SaveLoan(double amount, Long accountId, LoanStatus status) {
        String url = baseUrl + "/loan";
    
        Loan loan = new Loan();
        loan.setAmount(amount);
        loan.setRelatedAccount(accountId);
        loan.setStatus(status);
    
        return restTemplate.postForObject(url, loan, Loan.class);
    }
    
    
    
    
}
