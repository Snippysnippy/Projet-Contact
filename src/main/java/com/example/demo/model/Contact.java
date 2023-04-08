package com.example.demo.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

//    @ManyToMany(cascade = {CascadeType.ALL})
//    @JoinTable(
//            name = "contact_address",
//            joinColumns = { @JoinColumn(name = "contact_id") },
//            inverseJoinColumns = { @JoinColumn(name = "address_id") }
//    )
//    private Set<Address> addresses = new HashSet<>();
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contact")
//    private Set<PhoneNumber> phoneNumbers = new HashSet<>();

    public Contact() {
    }

    public Contact(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // Getters and Setters
}