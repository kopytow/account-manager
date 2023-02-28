package org.example.contact.manager;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
public class HibernateContactDao implements ContactDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public HibernateContactDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Contact> getAllContacts() {
        String hql = "FROM Contact";
        try (var session = sessionFactory.openSession()) {
            Query query = (Query) session.createQuery(hql);
            return query.list();
        }
    }

    @Override
    public Contact getContact(long contactId) {
        try (var session = sessionFactory.openSession()){
            return session.get(Contact.class, contactId);
        }
    }

    @Override
    public long addContact(Contact contact) {
        try (var session = sessionFactory.openSession()){
            var transaction = session.beginTransaction();
            var contactId = (long) session.save(contact);
            transaction.commit();
            contact.setId(contactId);
            return contactId;
        }
    }

    @Override
    public void updateContact(Contact contact) {
        try (var session = sessionFactory.openSession()){
            var transaction = session.beginTransaction();
            session.update(contact);
            transaction.commit();
        }
    }

    @Override
    public void updatePhoneNumber(long contactId, String phoneNumber) {
        var contact = getContact(contactId);
        contact.setPhone(phoneNumber);
        updateContact(contact);
    }

    @Override
    public void updateEmail(long contactId, String email) {
        var contact = getContact(contactId);
        contact.setEmail(email);
        updateContact(contact);
    }

    @Override
    public void deleteContact(long contactId) {
        try (var session = sessionFactory.openSession()){
            var transaction = session.beginTransaction();
            var contact = getContact(contactId);
            if (contact != null) {
                session.delete(contact);
            }
            transaction.commit();
        }
    }
}
