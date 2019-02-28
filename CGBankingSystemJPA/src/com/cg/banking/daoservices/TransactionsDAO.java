package com.cg.banking.daoservices;
import java.util.HashMap;
import java.util.List;

import com.cg.banking.beans.Account;
import com.cg.banking.beans.Transaction;

public interface TransactionsDAO {
	Transaction save(Account account,Transaction transaction);
	boolean update(Account account,Transaction  transaction);
	Transaction findOne(Account account,int transactionId);
	HashMap<Integer, Transaction>findAll(long accountNo);
}
