package org.example.contact.manager;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.List;

public class NamedParametersJdbcContactDao implements ContactDao{

    public static final String CREATE_CONTACT_SQL = "" +
        "INSERT INTO CONTACT(FIRSTNAME, SURNAME, EMAIL, PHONE_NUMBER) " +
        "VALUES(:name, :surname, :email, :phone)";

    public static final String GET_ALL_CONTACT_SQL = "" +
        "SELECT ID, FIRSTNAME, SURNAME, EMAIL, PHONE_NUMBER FROM CONTACT";

    public static final String GET_CONTACT_SQL = "" +
        "SELECT ID, FIRSTNAME, SURNAME, EMAIL, PHONE_NUMBER FROM CONTACT WHERE ID = :id";

    public static final String UPDATE_PHONE_NUMBER_SQL = "" +
        "UPDATE CONTACT SET PHONE_NUMBER = :phone WHERE ID = :id";

    public static final String UPDATE_EMAIL_SQL = "" +
        "UPDATE CONTACT SET EMAIL = :email WHERE ID = :id";

    public static final String DELETE_CONTACT_SQL = "" +
        "DELETE FROM CONTACT WHERE ID = :id";

    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    public NamedParametersJdbcContactDao(NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.namedJdbcTemplate = namedJdbcTemplate;
    }

    public List<Contact> getAllContacts() {
        return namedJdbcTemplate.query(
                GET_ALL_CONTACT_SQL,
                (rs, id) -> new Contact(
                        rs.getLong("ID"),
                        rs.getString("FIRSTNAME"),
                        rs.getString("SURNAME"),
                        rs.getString("EMAIL"),
                        rs.getString("PHONE_NUMBER")
                )
        );
    }

    public Contact getContact(long contactId) {
        return (Contact) namedJdbcTemplate.queryForObject(
                GET_CONTACT_SQL,
                new MapSqlParameterSource("id", contactId),
                (rs, id) -> new Contact(
                        rs.getLong("ID"),
                        rs.getString("FIRSTNAME"),
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
                new String[] {"id"}
        );
        contact.setId(keyHolder.getKey().longValue());
        return contact.getId();
    }

    @Override
    public void updateContact(Contact contact) {

    }

    public void updatePhoneNumber(long contactId, String phoneNumber) {
        namedJdbcTemplate.update(
                UPDATE_PHONE_NUMBER_SQL,
                new MapSqlParameterSource()
                        .addValue("id", contactId)
                        .addValue("phone", phoneNumber)
        );
    }

    public void updateEmail(long contactId, String email) {
        namedJdbcTemplate.update(
                UPDATE_EMAIL_SQL,
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
