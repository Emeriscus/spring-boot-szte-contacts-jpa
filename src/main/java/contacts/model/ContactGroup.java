package contacts.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class ContactGroup {

    @Id
    private Long id;

    private String name;

//    @OneToMany
//    private List<Contact> contacts;
}
