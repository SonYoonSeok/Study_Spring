package com.sonny;

public class BankServiceFacade {

    CustomerService customerService;
    LoanService loanService;
    AccountService accountService;

    public boolean getLoan(int sessionId, double amout) {
        boolean result = false;
        long id = customerService.getCustomer(sessionId);

        if (customerService.checkId(id)) {
            if (loanService.checkCreditRating(id, amout)) {
                if (accountService.getLoad(amout)) {
                    result = accountService.setCustomerBalance(id, amout);
                }
            }
        }
        return result;
    }
}
