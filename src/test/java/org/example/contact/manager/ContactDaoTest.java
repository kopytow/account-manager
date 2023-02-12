package org.example.contact.manager;

import org.example.contact.manager.config.ContactConfiguration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ContactConfiguration.class)
@Sql("classpath:contact.sql")
public record ContactDaoTest() {
    private static ContactDao contactDao;

    @BeforeAll
    public static void init(@Autowired ContactDao cd) {
        contactDao = cd;
    }

    private static final Contact FEDOSIY = new Contact(
            1000L, "Федосий", "Брежнев", "FedosiyBrezhnev@gmail.com", "+7 (977) 677-95-44"
    );

    private static final Contact EKATERINA = new Contact(
            2000L, "Екатерина", "Зимина", "EkaterinaZimina@gmail.com", "+7 (967) 874-83-33"
    );

    /**
     * Два контакта, хранящиеся в БД.
     */
    private static final List<Contact> PERSISTED_CONTACTS = List.of(FEDOSIY, EKATERINA);

    @Test
    void getAllContacts() {
        var contacts = contactDao.getAllContacts();
        assertThat(contacts).containsAll(PERSISTED_CONTACTS);
    }

    @Test
    void getContact() {
        var contact = contactDao.getContact(FEDOSIY.getId());
        assertThat(contact).isEqualTo(FEDOSIY);
    }

    @Test
    void addContact() {
        var contact = new Contact("Егор", "Загитов", "EgorZagitov631@gmail.com", "+7 (985) 112-34-98");
        var contactId = contactDao.addContact(contact);
        contact.setId(contactId);
        var contactInDb = contactDao.getContact(contactId);
        assertThat(contactInDb).isEqualTo(contact);
    }

    @Test
    void updatePhoneNumber() {
        var contact = new Contact("Артем", "Минаев", "ArtemMinaev211@gmail.com", "");
        var contactId = contactDao.addContact(contact);
        var newPhone = "+7 (945) 899-31-53";
        contactDao.updatePhoneNumber(contactId, newPhone);
        var updatedContact = contactDao.getContact(contactId);
        assertThat(updatedContact.getPhone()).isEqualTo(newPhone);
    }

    @Test
    void updateEmail() {
        var contact = new Contact("Кащей", "Бессмертный", "", "");
        var contactId = contactDao.addContact(contact);

        var newEmail = "kashchej@gmail.com";
        contactDao.updateEmail(contactId, newEmail);

        var updatedContact = contactDao.getContact(contactId);
        assertThat(updatedContact.getEmail()).isEqualTo(newEmail);
    }

    @Test
    void deleteContact() {
        var contact = new Contact("Баба", "Яга", "", "");
        var contactId = contactDao.addContact(contact);

        contactDao.deleteContact(contactId);

        assertThatThrownBy(() -> contactDao.getContact(contactId))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }
}