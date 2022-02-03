package common;

import java.io.Serializable;

/**
 *
 * @author 
 */
public class Account implements Serializable {
     private static final long serialVersionUID = 1L;
     private int accountNumber; //This accounts unique identification number
     private int owner; //The customerId of the owner of this account
     private Money accountBalance;
     

    public Account(int accountNumber, int owner, Money accountBalance){
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.accountBalance = accountBalance;
    }

    //adds money if earned and subtracts from account balance if withdrawing
    public void withdrawl(double amount) {
        accountBalance.spend(new Money(amount));
    }
    public void deposit(double amount) {
        accountBalance.earn(new Money(amount));
    }


    public int getAccountNumber() {return accountNumber;}
    public int getOwner() {return owner;}

    public Money getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Money accountBalance) {
        this.accountBalance = accountBalance;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + this.accountNumber;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Account other = (Account) obj;
        if (this.accountNumber != other.accountNumber) {
            return false;
        }
        return true;
    }
     
     
}
