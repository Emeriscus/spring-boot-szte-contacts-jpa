package contacts.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "CONTACT")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotEmpty(message = "Name must not be empty")
    @Size(min = 3, message = "Name must be at least 3 characters long")
    @Column(name = "name_col")
    @NotNull
    private String name;

    @Email
    private String email;

    private String phone;

    @PastOrPresent
    private Date birthDate;

    private String address;
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private ContactGroup contactGroup;
}
