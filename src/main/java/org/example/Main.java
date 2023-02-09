package org.example;

import org.example.account.manager.AccountDao;
import org.example.account.manager.config.ApplicationConfiguration;
import org.example.contact.manager.ContactDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        var applicationContext = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

        var accountDao = applicationContext.getBean(AccountDao.class);

        /*var account = accountDao.getAccount(1000L);

        System.out.println(account);

        accountDao.setAmount(1000L, 2000L);
        account = accountDao.getAccount(1000L);
        System.out.println(account);

        var newAccount = accountDao.addAccount(10L,10000L);
        System.out.println(newAccount);*/

        var accounts = accountDao.getAllAccounts();
        System.out.println(accounts);

        var contactDao = applicationContext.getBean(ContactDao.class);
        var contacts = contactDao.getAllContacts();
        System.out.println(contacts);
    }
}
