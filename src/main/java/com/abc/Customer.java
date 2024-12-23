package com.abc;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class Customer {
    private String name;
    private List<Account> accounts;

    public Customer(String name) {
        this.name = name;
        this.accounts = new ArrayList<Account>();
    }

    public String getName() {
        return name;
    }

    public Customer openAccount(Account account) {
        accounts.add(account);
        return this;
    }

    public int getNumberOfAccounts() {
        return accounts.size();
    }

    public double totalInterestEarned() {
        double total = 0;
        for (Account account : accounts)
            total += account.interestEarned();
        return total;
    }

    public String getStatement() {
        StringBuilder statement = null;
        statement = new StringBuilder("Statement for " + name + "\n");
        double total = 0.0;
        for (Account account : accounts) {
            statement.append("\n")
                    .append(statementForAccount(account))
                    .append("\n");
            total += account.sumTransactions();
        }
        statement.append("\nTotal In All Accounts ")
                .append(toDollars(total));
        return statement.toString();
    }

    private String statementForAccount(Account account) {
        StringBuilder statement = new StringBuilder();

       //Translate to pretty account type
        switch(account.getAccountType()){
            case Account.CHECKING:
                statement.append("Checking Account\n");
                break;
            case Account.SAVINGS:
                statement.append("Savings Account\n");
                break;
            case Account.MAXI_SAVINGS:
                statement.append("Maxi Savings Account\n");
                break;
            case Account.SUPER_SAVINGS:
                statement.append("Super Savings Account\n");
                break;
        }

        //Now total up all the transactions
        double total = 0.0;
        for (Transaction transaction : account.transactions) {
            statement.append("  ")
                    .append(transaction.amount < 0 ? "withdrawal" : "deposit")
                    .append(" ")
                    .append(toDollars(transaction.amount))
                    .append("\n");
            total += transaction.amount;
        }
        statement.append("Total ").append(toDollars(total));
        return statement.toString();
    }

    private String toDollars(double d){
        return String.format("$%,.2f", abs(d));
    }
}
