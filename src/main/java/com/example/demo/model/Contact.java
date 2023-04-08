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

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}