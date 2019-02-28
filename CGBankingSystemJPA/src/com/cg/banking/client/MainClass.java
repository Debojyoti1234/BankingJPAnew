package com.cg.banking.client;

import java.util.Scanner;

import com.cg.banking.beans.Account;
import com.cg.banking.exceptions.AccountBlockedException;
import com.cg.banking.exceptions.AccountNotFoundException;
import com.cg.banking.exceptions.BankingServicesDownException;
import com.cg.banking.exceptions.InsufficientAmountException;
import com.cg.banking.exceptions.InvalidAccountTypeException;
import com.cg.banking.exceptions.InvalidAmountException;
import com.cg.banking.exceptions.InvalidPinNumberException;
import com.cg.banking.services.BankingServices;
import com.cg.banking.services.BankingservicesImpl;

public class MainClass {

	public static void main(String[] args)throws InsufficientAmountException,InvalidPinNumberException {
		long accNo,pinNo;
		int choice;
		float amt;
		Scanner sc=new Scanner(System.in);
		BankingServices services=new BankingservicesImpl();
		Account customer1=null;
		Account customer2=null;
		try {
			customer1=services.openAccount("Savings",10000);
			customer1.setAccountStatus("Active");
		}catch(InvalidAmountException|InvalidAccountTypeException|BankingServicesDownException e) {
			e.printStackTrace();
		}
		try {
			customer2=services.openAccount("Salary",5000);
			customer1.setAccountStatus("Active");
		}catch(InvalidAmountException|InvalidAccountTypeException|BankingServicesDownException e) {
			e.printStackTrace();
		}
		System.out.println("Customer Details : "+customer1);
		System.out.println("Customer Details : "+customer2);
		while(true)
		{
			System.out.println("\n Operation choices");
			System.out.println("1.Withdraw Ammount \n 2.Deposit Ammount \n 3.Fund Transfer \n 4.Exit");
			System.out.println("Enter your choice  :");
			choice= sc.nextInt();
			switch(choice) {
			case 1: System.out.println("-----------------Withdraw Operation-----------------");
			System.out.println("Enter Account number");
			accNo=sc.nextLong();
			System.out.println("Enter Pin number");
			pinNo=sc.nextInt();
			System.out.println("Enter Amount to withdraw");
			amt=sc.nextFloat();
			try {
			System.out.println(services.withdrawAmount(accNo, amt, pinNo));
			System.out.println("\tAccount Details After Withdrawl :"+services.getAccountDetails(accNo));
			}catch(AccountNotFoundException|BankingServicesDownException|AccountBlockedException e) {
				e.printStackTrace();
			}catch(InsufficientAmountException e) {
				e.printStackTrace();
			}catch(InvalidPinNumberException e) {
				e.printStackTrace();
			}
			System.out.println("--------------------------------------------------------");
			break;
		case 2:
			System.out.println("\n\t\t\bDeposit Operation\b\n");
			System.out.println("\tEnter Account Number: ");
			accNo=sc.nextLong();
			System.out.println("\tEnter Amount to Deposit: ");
			amt=sc.nextFloat();
			try {
				System.out.println(services.depositAmount(accNo, amt));
				System.out.println("\tAccount Details After Deposit :"+services.getAccountDetails(accNo));
			}catch(AccountNotFoundException | BankingServicesDownException | AccountBlockedException e) {
				e.printStackTrace();
			}
			System.out.println("----------------------------------------------------------");
			break;
		case 3:
			System.out.println("\n\t\t\bFund Transfer Operation\b\n");
			System.out.println("\nEnter Account Number to Transfer From: ");
			Long accNoTo=sc.nextLong();
			System.out.println("\tEnter Account Number to transfer (to): ");
			Long accNoFrom=sc.nextLong();
			System.out.println("\tEnter amount to transfer: ");
			Float transferAmount=sc.nextFloat();
			System.out.println("\tEnter Pin No: ");
			long pinNumber=sc.nextLong();
			try {
				System.out.println(services.fundTransfer(accNoTo,accNoFrom, transferAmount, pinNumber));
				System.out.println("Account Details After Fund Transfer :"+services.getAccountDetails(accNoFrom));
			}catch(InsufficientAmountException | InvalidPinNumberException | AccountNotFoundException | BankingServicesDownException | AccountBlockedException e) {
				e.printStackTrace();
			}
			System.out.println("---------------------------------------------------------");
			break;
		case 4:
			System.exit(-1);
		default:
			System.out.println("\tInvalid Choice! Try Again.....");
			System.out.println("-----------------------------------------------------------------");
			break;
		}
	}

}
}