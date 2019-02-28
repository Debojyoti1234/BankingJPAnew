package com.cg.banking.test;

import com.cg.banking.services.BankingServices;

public class BankingSystemTest {

		private static BankingServices services;

		/*@BeforeClass
		public static void setUpTestEnv() {
			services=new BankingservicesImpl();
		}
		
		@Test(expected=AccountNotFoundException.class)
		public void testGetAccountDetailsForInvalidAccountId() throws AccountNotFoundException, BankingServicesDownException {
			services.getAccountDetails(1000);
		}
		
		@Test(expected=AccountNotFoundException.class)
		public void testGetAccountDetailsForValidAccountId() throws AccountNotFoundException, BankingServicesDownException {
			Account expectedAccount = new Account(101, 6554, "savings", "Active", 200, new HashMap<Integer, Transaction>(1) );
			Account actualAccount=services.getAccountDetails(101);
			Assert.assertEquals(expectedAccount, actualAccount);
		}
		
		@Test(expected=AccountNotFoundException.class)
		public void testGetAccountDetailsforInvalidAmount() throws AccountNotFoundException, BankingServicesDownException {
			services.getAccountDetails(12345);
		}
		@Test(expected=AccountNotFoundException.class)
		public void testAccountStatusForValidAccountNumber() throws AccountNotFoundException, BankingServicesDownException, AccountBlockedException{
			String expectedStatus="Active";
			String actualStatus=services.accountStatus(5001);
			Assert.assertEquals(expectedStatus, actualStatus);
		}
		@Test
		public void testGetAllAccountDetailsforInvalidAmount() throws AccountNotFoundException, BankingServicesDownException {
			Account account1=new Account( "Savings", "Active",2000);
			Account account2=new Account("Savings", "Blocked",3000);
				
			ArrayList<Account> expectedAccountList=new ArrayList<Account>();
			expectedAccountList.add(account1);
			expectedAccountList.add(account2);
			ArrayList<Account> actualAccountList=(ArrayList<Account>)services.getAllAccountDetails();
			Assert.assertEquals(expectedAccountList, actualAccountList);
			}
		@AfterClass
		public static void tearDownTestEnv() {
		services=null;
		}*/
	}