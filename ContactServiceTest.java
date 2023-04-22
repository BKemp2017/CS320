package test;
import contactservice.ContactService;
import contactservice.Contact;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContactServiceTest {
    
    private ContactService contactService;
    
    @BeforeEach
    public void setUp() {
        contactService = new ContactService();
    }
   
    @Test
    public void addContactTest() {
        Contact contact = new Contact("12345", "John", "Doe", "1234567890", "123 Main St.");
        contactService.addContact(contact);
        
        Assertions.assertTrue(contactService.getContacts().containsKey("12345"));
        Assertions.assertEquals(contact, contactService.getContacts().get("12345"));
    }
    
    @Test
    public void addDuplicateContactTest() {
        Contact contact = new Contact("12345", "John", "Doe", "1234567890", "123 Main St.");
        contactService.addContact(contact);
        
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            contactService.addContact(contact);
        });
    }
    
    @Test
    public void deleteContactTest() {
        Contact contact = new Contact("12345", "John", "Doe", "1234567890", "123 Main St.");
        contactService.addContact(contact);
        
        Assertions.assertTrue(contactService.getContacts().containsKey("12345"));
        
        contactService.deleteContact("12345");
        
        Assertions.assertFalse(contactService.getContacts().containsKey("12345"));
    }
    
    @Test
    public void updateContactTest() {
        Contact contact = new Contact("12345", "John", "Doe", "1234567890", "123 Main St.");
        contactService.addContact(contact);
        
        Assertions.assertEquals("John", contactService.getContacts().get("12345").getFirstName());
        Assertions.assertEquals("Doe", contactService.getContacts().get("12345").getLastName());
        Assertions.assertEquals("1234567890", contactService.getContacts().get("12345").getPhone());
        Assertions.assertEquals("123 Main St.", contactService.getContacts().get("12345").getAddress());
        
        contactService.updateContact("12345", "Jane", "Doe", null, "456 Main St.");
        
        Assertions.assertEquals("Jane", contactService.getContacts().get("12345").getFirstName());
        Assertions.assertEquals("Doe", contactService.getContacts().get("12345").getLastName());
        Assertions.assertEquals("1234567890", contactService.getContacts().get("12345").getPhone());
        Assertions.assertEquals("456 Main St.", contactService.getContacts().get("12345").getAddress());
    }
    
    @Test
    public void updateNonexistentContactTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            contactService.updateContact("12345", "Jane", "Doe", "1234567890", "456 Main St.");
        });
    }
    
    @Test
    public void addContactWithNonUniqueIDTest() {
        Contact contact1 = new Contact("12345", "John", "Doe", "1234567890", "123 Main St.");
        Contact contact2 = new Contact("12345", "Jane", "Doe", "0987654321", "456 Main St.");
        contactService.addContact(contact1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            contactService.addContact(contact2);
        });
    }

    @Test
    public void updateContactFieldsTest() {
        Contact contact = new Contact("12345", "John", "Doe", "1234567890", "123 Main St.");
        contactService.addContact(contact);
        
        contactService.updateContact("12345", "Jane", "Doe", "0987654321", "456 Main St.");
        
        Assertions.assertEquals("Jane", contactService.getContacts().get("12345").getFirstName());
        Assertions.assertEquals("Doe", contactService.getContacts().get("12345").getLastName());
        Assertions.assertEquals("0987654321", contactService.getContacts().get("12345").getPhone());
        Assertions.assertEquals("456 Main St.", contactService.getContacts().get("12345").getAddress());
    }

}