package org.example.contact.manager;

import org.example.contact.manager.config.ContactConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link ContactService}.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ContactConfiguration.class)
@Sql("classpath:contact.sql")
public class ContactServiceTests {

    private static final Contact IVAN = new Contact(
            1L, "Ivan", "Ivanov", "iivanov@gmail.com", "1234567"
    );

    private static final Contact MARIA = new Contact(
            2L, "Maria", "Ivanova", "mivanova@gmail.com", "7654321"
    );

    private static final Contact FEDOSIY = new Contact(
            1000L, "Федосий", "Брежнев", "FedosiyBrezhnev@gmail.com", "+7 (977) 677-95-44"
    );

    private static final Contact EKATERINA = new Contact(
            2000L, "Екатерина", "Зимина", "EkaterinaZimina@gmail.com", "+7 (967) 874-83-33"
    );

    /**
     * Ожидаемое содержимое БД.
     */
    private static final List<Contact> PERSISTED_CONTACTS = List.of(FEDOSIY, EKATERINA, IVAN, MARIA);

    private final ContactService contactService;

    @Autowired
    public ContactServiceTests(ContactService contactService) {
        this.contactService = contactService;
    }

    @Test
    void saveContacts() throws IOException {
        var filePath = new ClassPathResource("contacts.csv").getFile().toPath();
        contactService.saveContacts(filePath);

        var contacts = contactService.getContacts();

        assertThat(contacts).contains(IVAN, MARIA);
    }
}
