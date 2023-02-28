package org.example;

import org.example.account.manager.AccountConfiguration;
import org.example.account.manager.AccountDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        var applicationContext = new AnnotationConfigApplicationContext(AccountConfiguration.class);
        var accountDao = applicationContext.getBean(AccountDao.class);
        var account = accountDao.getAccount(1L);
        System.out.println(account);

        accountDao.setAmount(1L, 2000L);
        account = accountDao.getAccount(1L);
        System.out.println(account);

        var newAccount = accountDao.addAccount(10L, 10000L);
        System.out.println(newAccount);

        var accounts = accountDao.getAllAccount();
        System.out.println(accounts);
    }
}