package org.example.contact.manager;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.List;

public class ContactDao {
    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    private static final String CREATE_CONTACT_SQL = "" +
            "INSERT INTO CONTACT(_NAME, SURNAME, EMAIL, PHONE_NUMBER)" +
            "VALUES(:name, :surname, :email, :phone)";

    private static final String GET_ALL_CONTACT_SQL = "" +
            "SELECT ID, _NAME, SURNAME, EMAIL, PHONE_NUMBER FROM CONTACT";

    private static final String GET_CONTACT_SQL = "" +
            "SELECT ID, _NAME, SURNAME, EMAIL, PHONE_NUMBER FROM CONTACT WHERE ID = :id";

    private static final String DELETE_CONTACT_SQL = "" +
            "DELETE FROM CONTACT WHERE ID = :id";

    public ContactDao(NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.namedJdbcTemplate = namedJdbcTemplate;
    }

    public List<Contact> getAllContacts() {
        return namedJdbcTemplate.query(
                GET_ALL_CONTACT_SQL,
                (rs, i) -> new Contact(
                        rs.getLong("ID"),
                        rs.getString("_NAME"),
                        rs.getString("SURNAME"),
                        rs.getString("EMAIL"),
                        rs.getString("PHONE_NUMBER")
                )
        );
    }

    public Contact getContact(long contactId) {
        return namedJdbcTemplate.queryForObject(
                GET_CONTACT_SQL,
                new MapSqlParameterSource("id", contactId),
                (rs, i) -> new Contact(
                        rs.getLong("ID"),
                        rs.getString("_NAME"),
                        rs.getString("SURNAME"),
                        rs.getString("EMAIL"),
                        rs.getString("PHONE_NUMBER")
                )
        );
    }

    public long addContact(Contact contact) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedJdbcTemplate.update(
                CREATE_CONTACT_SQL,
                new MapSqlParameterSource()
                        .addValue("name", contact.getName())
                        .addValue("surname", contact.getSurname())
                        .addValue("email", contact.getEmail())
                        .addValue("phone", contact.getPhone()),
                keyHolder,
                new String[] { "id" }
        );
        contact.setId(keyHolder.getKey().longValue());
        return contact.getId();
    }

    public void updatePhoneNumber(long contactId, String phoneNumber) {
        namedJdbcTemplate.update(
                "UPDATE CONTACT SET PHONE = :phone WHERE ID = :id",
                new MapSqlParameterSource()
                        .addValue("id", contactId)
                        .addValue("phone", phoneNumber)
        );
    }

    public void updateEmail(long contactId, String email) {
        namedJdbcTemplate.update(
                "UPDATE CONTACT SET email = :email WHERE ID = :id",
                new MapSqlParameterSource()
                        .addValue("id", contactId)
                        .addValue("email", email)
        );
    }

    public void deleteContact(long contactId) {
        namedJdbcTemplate.update(
                DELETE_CONTACT_SQL,
                new MapSqlParameterSource("id", contactId)
        );
    }
}
