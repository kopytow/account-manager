package org.example.account.manager;

import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
public class NamedJdbcAccountDao implements AccountDao {
    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    public NamedJdbcAccountDao(NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.namedJdbcTemplate = namedJdbcTemplate;
    }

    @Override
    public Account addAccount(long id, long amount) {
        namedJdbcTemplate.update(
                "INSERT INTO ACCOUNT(ID,AMOUNT) VALUES(:id, :amount)",
                new MapSqlParameterSource()
                        .addValue("id", id)
                        .addValue("amount", amount)
        );
        return new Account(id, amount);
    }

    @Override
    public Account addAccount(long amount) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedJdbcTemplate.update(
                "INSERT INTO ACCOUNT(AMOUNT) VALUES(:amount)",
                new MapSqlParameterSource("amount", amount),
                keyHolder,
                new String[] {"id"}
        );
        var accountID = keyHolder.getKey().longValue();
        return new Account(accountID, amount);
    }

    @Override
    public Account getAccount(long id) {
        return namedJdbcTemplate.queryForObject(
                "SELECT ID, AMOUNT FROM ACCOUNT WHERE ID = :id",
                new MapSqlParameterSource("id", id),
                (rs, i) -> new Account(rs.getLong("ID"), rs.getLong("AMOUNT"))
        );
    }

    @Override
    public void setAmount(long id, long amount) {
        namedJdbcTemplate.update(
                "UPDATE ACCOUNT SET AMOUNT = :amount WHERE ID = :id",
                new MapSqlParameterSource()
                        .addValue("id", id)
                        .addValue("amount", amount)
        );
    }

    @Override
    public List<Account> getAllAccount() {
        return namedJdbcTemplate.query(
                "SELECT ID, AMOUNT FROM ACCOUNT",
                (rs, i) -> new Account(rs.getLong("ID"), rs.getLong("AMOUNT"))
        );
    }
}
