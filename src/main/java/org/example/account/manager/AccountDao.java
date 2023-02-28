package org.example.account.manager;

import java.util.List;

public interface AccountDao {
    Account addAccount(long id, long amount);
    Account addAccount(long amount);
    Account getAccount(long id);
    void setAmount(long id, long amount);
    List<Account> getAllAccount();
}
