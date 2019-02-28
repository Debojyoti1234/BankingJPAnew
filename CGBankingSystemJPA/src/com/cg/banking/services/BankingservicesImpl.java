package com.cg.banking.services;

import java.util.List;

import com.cg.banking.beans.Account;
import com.cg.banking.beans.Transaction;
import com.cg.banking.daoservices.AccountDAO;
import com.cg.banking.daoservices.AccountDAOImpl;
import com.cg.banking.daoservices.TransactionDAOImpl;
import com.cg.banking.daoservices.TransactionsDAO;
import com.cg.banking.exceptions.AccountBlockedException;
import com.cg.banking.exceptions.AccountNotFoundException;
import com.cg.banking.exceptions.BankingServicesDownException;
import com.cg.banking.exceptions.InsufficientAmountException;
import com.cg.banking.exceptions.InvalidAccountTypeException;
import com.cg.banking.exceptions.InvalidAmountException;
import com.cg.banking.exceptions.InvalidPinNumberException;

public  class BankingservicesImpl implements BankingServices{

	AccountDAO customerData1=new AccountDAOImpl() ;
	TransactionsDAO transactionDAO=new TransactionDAOImpl();
	private final int maxInvalidPinAttempts=3;
	private final static float minBalance=1000;
	public Account openAccount(String accountType, float initBalance)
			throws InvalidAmountException, InvalidAccountTypeException, BankingServicesDownException {
		if(initBalance<=minBalance)
			throw new InvalidAmountException("Invalid amount !");
		Account cust = customerData1.save(new Account(accountType,initBalance));
		transactionDAO.save(cust, new Transaction(initBalance, "Deposit"));
		return cust;
	}

	
	public Account getAccountDetails(long accountNo) throws AccountNotFoundException, BankingServicesDownException {

		Account customers=null;
		customers=customerData1.findOne(accountNo);
		if(customers==null)
			throw new AccountNotFoundException("Account doesn't exist! ");
		return customers;
	}

	public List<Account> getAllAccountDetails() throws BankingServicesDownException {

		List<Account>customers=customerData1.findAll();
		return customers;
	}

	public List<Transaction> getAccountAllTransactions(long accountNo)
			throws BankingServicesDownException, AccountNotFoundException {
		return null;
	}

	public String accountStatus(long accountNo)
			throws BankingServicesDownException, AccountNotFoundException,AccountBlockedException {
		Account customers=customerData1.findOne(accountNo);
		if(customers==null)
			throw new AccountNotFoundException("Account doesn't exist! ");
		else if(customers.getAccountStatus().equalsIgnoreCase("BLOCKED!"))
			throw new AccountBlockedException("Account is Blocked!");
		return customers.getAccountStatus();
	}

	public float depositAmount(long accountNo, float amount)
			throws AccountNotFoundException, BankingServicesDownException, AccountBlockedException {
		Account customers=null;
		customers=getAccountDetails(accountNo);
		if(customers.getAccountStatus().equalsIgnoreCase("BLOCKED!"))
			throw new AccountBlockedException("Account is Blocked!");
		else
			customers.setAccountBalance(customers.getAccountBalance()+amount);
		transactionDAO.save(customers, new Transaction(amount, "Deposit"));
		return customers.getAccountBalance() ;
	}

	public boolean fundTransfer(long accountNoTo, long accountNoFrom, float transferAmount, long pinNumber)
			throws InsufficientAmountException, AccountNotFoundException, InvalidPinNumberException,
			BankingServicesDownException, AccountBlockedException {
		Account customerTo=null;
		Account customerFrom=null;
		customerTo=getAccountDetails(accountNoTo);
		customerFrom=getAccountDetails(accountNoFrom);
		if(customerFrom.getAccountBalance()-transferAmount<=minBalance)
			throw new InsufficientAmountException("Amount not present in Account");
		if(customerFrom.getPinNumber()!=pinNumber)
			throw new InvalidPinNumberException("Wrong Pin Entered!");
		else {
			customerFrom.setAccountBalance(withdrawAmount(customerFrom.getAccountNo(),transferAmount,customerFrom.getPinNumber()));
			customerTo.setAccountBalance(depositAmount(customerTo.getAccountNo(),transferAmount));
			return true;
		}
	}
	public List<Transaction> getAccountAllTransaction(long accountNo)
			throws BankingServicesDownException, javax.security.auth.login.AccountNotFoundException {
		return null;
	}

	@Override
	public float withdrawAmount(long accountNo, float amount, long pinNo) throws InsufficientAmountException,
			AccountNotFoundException, InvalidPinNumberException, BankingServicesDownException, AccountBlockedException {
		Account customers=null;
		customers=getAccountDetails(accountNo);
		if(customers.getAccountStatus().equalsIgnoreCase("BLOCKED"))
			throw new AccountBlockedException("Account is Blocked!");
		else if(customers.getPinNumber()!=pinNo)
			throw new InvalidPinNumberException("Wrong Pin Entered!");
		if(customers.getPinAttempts()==maxInvalidPinAttempts) {
			customers.setAccountStatus("Blocked!");
			throw new InvalidPinNumberException();
		} 
		else if(customers.getPinAttempts()>0 && !(customers.getAccountStatus().equalsIgnoreCase("Blocked!")))
			customers.resetPin();
		else if(customers.getAccountBalance()-amount<=minBalance)
			throw new InsufficientAmountException("Amount not present in Account");
		else
			customers.setAccountBalance(customers.getAccountBalance()-amount);
		transactionDAO.save(customers, new Transaction(amount, "Withdraw"));
		return customers.getAccountBalance();
	}



}
