package test;
import contactservice.Contact;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContactTest {

    @Test
    void testContactId() {
    	Contact contact = new Contact("1234567890", "John", "Doe", "1234567890", "123 Main St");
        assertEquals("1234567890", contact.getId());
    }

    @Test
    void testFirstName() {
        Contact contact = new Contact("1234567890", "John", "Doe", "1234567890", "123 Main St");
        assertEquals("John", contact.getFirstName());
    }

    @Test
    void testLastName() {
        Contact contact = new Contact("1234567890", "John", "Doe", "1234567890", "123 Main St");
        assertEquals("Doe", contact.getLastName());
    }
    
    @Test
    void testGetFullName() {
        Contact contact = new Contact("John", "Doe");
        assertEquals("John Doe", contact.getFullName());
    }

    @Test
    void testPhone() {
        Contact contact = new Contact("1234567890", "John", "Doe", "1234567890", "123 Main St");
        assertEquals("1234567890", contact.getPhone());
    }

    @Test
    void testAddress() {
        Contact contact = new Contact("1234567890", "John", "Doe", "1234567890", "123 Main St");
        assertEquals("123 Main St", contact.getAddress());
    }
    
    
    @Test
    void testContactIdLengthLimit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345678901", "John", "Doe", "1234567890", "123 Main St");
        });
    }
    
    @Test
    void testContactIdNotNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact(null, "John", "Doe", "1234567890", "123 Main St");
        });
    }
    
    @Test
    void testContactIdNotUpdatable() {
        Contact contact = new Contact("1234567890", "John", "Doe", "1234567890", "123 Main St");
        assertThrows(UnsupportedOperationException.class, () -> {
            contact.setId("0987654321");
        });
    }
    
    @Test
    void testFirstNameLengthLimit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1234567890", "JonathanJonathan", "Doe", "1234567890", "123 Main St");
        });
    }
    
    @Test
    public void testSetFirstName_validLessThanMaxLength() {
        Contact contact = new Contact();
        String validFirstName = "abc";
        contact.setFirstName(validFirstName);
        assertEquals(validFirstName, contact.getFirstName());
    }
    
    @Test
    public void testSetFirstName_validMaxLength() {
        Contact contact = new Contact();
        String validFirstName = "abcdefghij";
        contact.setFirstName(validFirstName);
        assertEquals(validFirstName, contact.getFirstName());
    }
    
    @Test
    void testSetFirstName() {
        // Valid last name
        Contact contact = new Contact("1234567890", "John", "Doe", "1234567890", "123 Main St");
        contact.setFirstName("Ben");
        assertEquals("Ben", contact.getFirstName());

        // Null last name
        assertThrows(IllegalArgumentException.class, () -> contact.setFirstName(null));

        // Last name with invalid length
        assertThrows(IllegalArgumentException.class, () -> contact.setFirstName("abcdefghijklmnopqrstuvwxyz"));
    }
    
    
    @Test
    void testFirstNameNotNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1234567890", null, "Doe", "1234567890", "123 Main St");
        });
    }
    
    @Test
    void testSetLastName() {
        // Valid last name
        Contact contact = new Contact("1234567890", "John", "Doe", "1234567890", "123 Main St");
        contact.setLastName("Smith");
        assertEquals("Smith", contact.getLastName());

        // Null last name
        assertThrows(IllegalArgumentException.class, () -> contact.setLastName(null));

        // Last name with invalid length
        assertThrows(IllegalArgumentException.class, () -> contact.setLastName("abcdefghijklmnopqrstuvwxyz"));
    }
    

    
    @Test
    void testLastNameLengthLimit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1234567890", "John", "DoeDoeDoeDoe", "1234567890", "123 Main St");
        });
    }
    
    @Test
    void testLastNameNotNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1234567890", "John", null, "1234567890", "123 Main St");
        });
    }
    
    @Test
    void testPhoneNumberValidation() {
        // Valid phone number
        Contact contact = new Contact("1234567890", "John", "Doe", "1234567890", "123 Main St");
        assertEquals("1234567890", contact.getPhone());

        // Null phone number
        assertThrows(IllegalArgumentException.class, () -> new Contact("1234567890", "John", "Doe", null, "123 Main St"));

        // Phone number with invalid length
        assertThrows(IllegalArgumentException.class, () -> new Contact("1234567890", "John", "Doe", "12345678", "123 Main St"));

        // Phone number with non-numeric characters
        assertThrows(IllegalArgumentException.class, () -> new Contact("1234567890", "John", "Doe", "1234567a89", "123 Main St"));
    }
    
    @Test
    void testPhoneTenDigits() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1234567890", "John", "Doe", "123456789", "123 Main St");
        });
    }
    
    @Test
    void testPhoneNotNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1234567890", "John", "Doe", null, "123 Main St");
        });
    }
    
    @Test
    void testSetPhone() {
        // Valid phone number
        Contact contact = new Contact("1234567890", "John", "Doe", "1234567890", "123 Main St");
        contact.setPhone("0987654321");
        assertEquals("0987654321", contact.getPhone());

        // Null phone number
        assertThrows(IllegalArgumentException.class, () -> contact.setPhone(null));

        // Phone number with invalid length
        assertThrows(IllegalArgumentException.class, () -> contact.setPhone("123"));

        // Phone number with non-digit characters
        assertThrows(IllegalArgumentException.class, () -> contact.setPhone("1234abcd"));

        // Phone number with valid length but non-digit characters
        assertThrows(IllegalArgumentException.class, () -> contact.setPhone("12345678ab"));
    }
    
    @Test
    void testAddressLengthLimit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1234567890", "John", "Doe", "1234567890", "123 Main St, Apt 3B, New York, NY, USA");
        });
    }

    @Test
    void testAddressNotNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1234567890", "John", "Doe", "1234567890", null);
        });
    }
    
    @Test
    void testSetAddress() {
        Contact contact = new Contact("12345", "John", "Doe", "1234567890", "123 Main St.");
        
        // Test valid address
        String validAddress = "456 Second Ave.";
        contact.setAddress(validAddress);
        assertEquals(validAddress, contact.getAddress());
        
        // Test null address
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setAddress(null);
        });
        
        // Test address with length greater than 30
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setAddress("1234567890123456789012345678901");
        });
    }

    @Test
    public void testEmptyValues() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("", "Doe", "1234567890", "johndoe@example.com", "123 Main St");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("John", "", "1234567890", "johndoe@example.com", "123 Main St");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("John", "Doe", "", "johndoe@example.com", "123 Main St");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("John", "Doe", "1234567890", "", "123 Main St");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("John", "Doe", "1234567890", "johndoe@example.com", "");
        });
    }

    @Test
    public void testSpecialCharacters() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("John*", "Doe", "1234567890", "johndoe@example.com", "123 Main St");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("John", "&Doe", "1234567890", "johndoe@example.com", "123 Main St");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("John", "Doe", "1234567890", "johndoe$example.com", "123 Main St");
        });
    }
    

    @Test
    public void testToString() {
        Contact contact = new Contact("1", "John", "Doe", "1234567890", "123 Main St");
        String expected = "Contact{id='1', firstName='John', lastName='Doe', phone='1234567890', address='123 Main St'}";
        assertEquals(expected, contact.toString());
    }
}