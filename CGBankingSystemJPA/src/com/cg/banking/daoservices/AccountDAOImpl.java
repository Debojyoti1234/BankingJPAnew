package com.cg.banking.daoservices;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.cg.banking.beans.Account;


public  class AccountDAOImpl implements AccountDAO{
	private EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("JPA-PU");

	@Override
	public Account save(Account account) {
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		account.setPinNumber((int) (Math.random()*1000));
		account.setAccountStatus("active");
		entityManager.getTransaction().begin();
		entityManager.persist(account);
		entityManager.getTransaction().commit();
		entityManager.close();
		return account;	
	}

	@Override
	public boolean update(Account account) {
		account.setPinNumber((int)Math.random()*1000);
		
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(account);
		entityManager.getTransaction().commit();
		entityManager.close();

		return false;
	}

	@Override
	public Account findOne(long accountNo) {
		return entityManagerFactory.createEntityManager().find(Account.class,accountNo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Account> findAll() {
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		Query query=entityManager.createQuery("from Account a",Account.class);
		return query.getResultList();
		
	}
}