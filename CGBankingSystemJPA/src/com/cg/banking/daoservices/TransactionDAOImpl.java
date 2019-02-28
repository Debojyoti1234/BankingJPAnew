package com.cg.banking.daoservices;

import java.util.HashMap;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.cg.banking.beans.Account;
import com.cg.banking.beans.Transaction;

public class TransactionDAOImpl implements TransactionsDAO{
	private EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("JPA-PU");

	@Override
	public Transaction save(Account account, Transaction transaction) {
		transaction.setAccount(account);
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(transaction);
		entityManager.getTransaction().commit();
		entityManager.close();
		
		return transaction;
	}
	@Override
	public boolean update(Account account, Transaction transaction) {
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		transaction.setAccount(account);
		entityManager.getTransaction().begin();
		entityManager.merge(transaction);
		entityManager.getTransaction().commit();
		entityManager.close();
		return false;
	}

	@Override
	public Transaction findOne(Account account, int transactionId) {
		return entityManagerFactory.createEntityManager().find(Transaction.class,transactionId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<Integer, Transaction> findAll(long accountNo) {
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		Query query=entityManager.createQuery("from Transaction t",Transaction.class);
		return (HashMap<Integer, Transaction>) query.getResultList();
	}

}
