package org.example.contact.manager;

import java.util.List;

public interface ContactDao {

    /**
     * Возвращает список контактов. Тип элементов {@link Contact}.
     * @return список контактов {@link List<Contact>}
     */
    public List<Contact> getAllContacts();

    /**
     * Возвращает контакт, соответствующий указанному идентификатору.
     *
     * @return контакт {@link Contact}
     */
    public Contact getContact(long contactId);

    /**
     * Добавляет контакт. Идентификатор присваивается после записи в БД.
     * @param contact добавляемый контакт
     */
    public long addContact(Contact contact);

    /**
     * Обновить контакт.
     * @param contact обновляемый контакт
     */
    public void updateContact(Contact contact);
    /**
     * Изменяет номер телефона указанного контакта
     * @param contactId идентификатор контакта
     * @param phoneNumber новый номер телефона
     */
    public void updatePhoneNumber(long contactId, String phoneNumber);

    /**
     * Изменяет адрес почты указанного контакта
     * @param contactId идентификатор контакта
     * @param email адрес почты
     */
    public void updateEmail(long contactId, String email);

    /**
     * Удаляет указанный контакт.
     * @param contactId идентификатор удаляемого контакта
     */
    public void deleteContact(long contactId);
}
