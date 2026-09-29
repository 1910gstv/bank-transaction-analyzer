package main.java;

import main.java.domain.BankTransaction;

import java.time.Month;
import java.util.Calendar;
import java.util.List;

public class BankStatementProcessor {
    private final List<BankTransaction> bankTransactions;

    public BankStatementProcessor(final List<BankTransaction> bankTransactions){
        this.bankTransactions = bankTransactions;
    }

    public double calculateTotalAmount(){
        double total = 0;
        for(final BankTransaction bankTransaction: bankTransactions) {
            total += bankTransaction.getAmount();
        }

        return total;
    }

    public double calculateTotalInMonth(final Month month){
        double total = 0;
        for(final BankTransaction bankTransaction: bankTransactions){
            if(bankTransaction.getDate().getMonth() == month){
                total += bankTransaction.getAmount();
            }
        }
        return total;
    }

    public double calculateTotalForCategory(final String category){
        double total = 0;
        for(final BankTransaction bankTransaction: bankTransactions){
            if(bankTransaction.getDescription().equals(category)){
                total += bankTransaction.getAmount();
            }
        }

        return total;
    }

    public double returnMinimalBetweenMonths(final Month initialMonth, final Month finalMonth){
        double total = 0;
        for(final BankTransaction bankTransaction: bankTransactions){
            Month date = bankTransaction.getDate().getMonth();
            if(date.getValue() >= initialMonth.getValue() && date.getValue() <= finalMonth.getValue()){
                if(total > bankTransaction.getAmount() || total == 0){
                    total = bankTransaction.getAmount();
                }
            }
        }

        return total;
    }
}
