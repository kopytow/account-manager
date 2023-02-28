package org.example.account.manager;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcAccountDao implements AccountDao {
    public static final String GET_ACCOUNT_SQL = "" +
            "SELECT" +
            "   ID" +
            "   AMOUNT" +
            "FROM ACCOUNT" +
            "WHERE ID = ?";
    public static final String SET_AMOUNT_SQL = "" +
            "UPDATE ACCOUNT" +
            "SET AMOUNT = ?" +
            "WHERE ID = ?";
    public static final String CREATE_ACCOUNT_SQL = "" +
            "INSERT INTO ACCOUNT(AMOUNT)" +
            "VALUES(?)";
    public static final String GET_ALL_ACCOUNT_SQL = "" +
            "SELECT" +
            "   ID" +
            "   AMOUNT" +
            "FROM ACCOUNT";

    public static final RowMapper<Account> ACCOUNT_ROW_MAPPER =
            (rs, i) -> new Account(rs.getLong("ID"), rs.getLong("AMOUNT"));

    public JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Account addAccount(long id, long amount) {
        jdbcTemplate.update(
                "INSERT INTO ACCOUNT(ID, AMOUNT) VALUES(?)",
                id, amount
        );
        return new Account(id,amount);
    }

    @Override
    public Account addAccount(long amount) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                con -> {
                    var ps = con.prepareStatement(CREATE_ACCOUNT_SQL, new String[] {"id"});
                    ps.setLong(1, amount);
                    return ps;
                },
                keyHolder
        );
        var accountID = keyHolder.getKey().longValue();
        return new Account(accountID, amount);
    }

    @Override
    public Account getAccount(long id) {
        return jdbcTemplate.queryForObject(
                GET_ACCOUNT_SQL,
                ACCOUNT_ROW_MAPPER,
                id
        );
    }

    @Override
    public void setAmount(long id, long amount) {
        jdbcTemplate.update(
                SET_AMOUNT_SQL,
                amount,
                id
        );
    }

    @Override
    public List<Account> getAllAccount() {
        return jdbcTemplate.query(
                GET_ALL_ACCOUNT_SQL,
                ACCOUNT_ROW_MAPPER
        );
    }
}
