package org.example.contact.manager;

import java.nio.file.Path;
import java.util.List;

public class ContactService {

    private final ContactDao contactDao;
    private final ContactCSVReader contactReader;

    public ContactService(ContactDao contactDao, ContactCSVReader contactReader) {
        this.contactDao = contactDao;
        this.contactReader = contactReader;
    }

    public void saveContacts(Path filePath) {
        var contacts = contactReader.readFromFile(filePath);
        contactDao.saveAll(contacts);
    }

    public List<Contact> getContacts() {
        return contactDao.getAllContacts();
    }
}
