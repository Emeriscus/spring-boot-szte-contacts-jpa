package contacts.service;

import contacts.model.Contact;
import contacts.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ContactService {

    private ContactRepository contactRepository;

    @Autowired
    public void setContactRepository(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public void saveContact(Contact contact) {
        contactRepository.save(contact);
    }

    public List<Contact> getContacts() {
        return contactRepository.findAll();
    }

    public void deleteContact(UUID id) {
        contactRepository.deleteById(id);
    }

    public Contact findById(UUID id) {
        return contactRepository.findById(id).orElse(null);
    }
}
