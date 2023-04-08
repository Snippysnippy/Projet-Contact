package com.example.demo.controller;

import com.example.demo.model.Contact;
import com.example.demo.service.ContactService;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;

    /*************************************** WEB *****************************************************/
    @GetMapping("/contacts")
    public String showContacts(Model model) {
        List<Contact> contacts = contactService.getAllContacts();
        model.addAttribute("contacts", contacts);
        return "list-contact";
    }

    @GetMapping("/contacts/{id}")
    public String editContactForm(@PathVariable("id") long id, Model model) {
        Contact contact = contactService.getContactById(id);
        model.addAttribute("contact", contact);
        return "edit-contact";
    }

    @PostMapping("/contacts")
    public String addContact(@ModelAttribute("contact") Contact contact) {
        contactService.addContact(contact);
        return "redirect:/contacts";
    }


    @PostMapping("/contacts/edit/{id}")
    public String updateContactForm(@PathVariable Long id, @ModelAttribute Contact contact) {
        contact.setId(id);
        contactService.updateContact(contact);
        return "redirect:/contacts";
    }


    @GetMapping("/contacts/delete/{id}")
    public String deleteContactRedirect(@PathVariable("id") Long id) {
        contactService.deleteContact(id);
        return "redirect:/contacts";
    }

    /************************************** API JSON ************************************************/
    @GetMapping("/api/contacts")
    public ResponseEntity<List<Contact>> getAllContacts() {
        List<Contact> contacts = contactService.getAllContacts();
        return ResponseEntity.ok(contacts);
    }

    @GetMapping("/api/contacts/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable Long id) {
        Contact contact = contactService.getContactById(id);
        return ResponseEntity.ok(contact);
    }

    @PostMapping("/api/contacts")
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact) {
        contactService.addContact(contact);
        return new ResponseEntity<>(contact, HttpStatus.CREATED);
    }

    @PutMapping("/api/contacts/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable Long id, @RequestBody Contact contact) {
        contactService.updateContact(contact);
        return ResponseEntity.ok(contact);
    }

    @DeleteMapping("/api/contacts/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
        return ResponseEntity.noContent().build();
    }

    /************************************** API XML ************************************************/

    @GetMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getXmlResponse(@RequestParam String action, @RequestParam(required = false) Long id) {
        try {
            if (action.equals("listContacts")) {
                List<Contact> contacts = contactService.getAllContacts();
                XmlMapper xmlMapper = new XmlMapper();
                String xml = xmlMapper.writeValueAsString(contacts);
                return ResponseEntity.ok(xml);
            } else if (action.equals("getContact") && id != null) {
                Contact contact = contactService.getContactById(id);
                XmlMapper xmlMapper = new XmlMapper();
                String xml = xmlMapper.writeValueAsString(contact);
                return ResponseEntity.ok(xml);
            } else if (action.equals("delContact") && id != null) {
                contactService.deleteContact(id);
                return ResponseEntity.ok("Contact with id " + id + " has been deleted.");
            } else {
                return ResponseEntity.badRequest().body("Invalid action");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

}
