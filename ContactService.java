package contactservice;

import java.util.HashMap;
import java.util.Map;

public class ContactService {
    private final Map<String, Contact> contacts = new HashMap<>();

    public void addContact(Contact contact) {
        if (contacts.containsKey(contact.getId())) {
            throw new IllegalArgumentException("Contact with ID " + contact.getId() + " already exists");
        }
        contacts.put(contact.getId(), contact);
    }

    public void deleteContact(String id) {
        contacts.remove(id);
    }
    
    public void updateContact(String id, String firstName, String lastName, String phone, String address) {
        Contact contact = contacts.get(id);
        if (contact == null) {
            throw new IllegalArgumentException("Contact with ID " + id + " not found");
        }
        if (firstName != null) {
            contact.setFirstName(firstName);
        }
        if (lastName != null) {
            contact.setLastName(lastName);
        }
        if (phone != null) {
            contact.setPhone(phone);
        }
        if (address != null) {
            contact.setAddress(address);
        }
    }
    public Map<String, Contact> getContacts() {
    	return contacts;
    }

}
