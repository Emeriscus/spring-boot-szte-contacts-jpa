package contacts.controller;

import contacts.model.Contact;
import contacts.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/contact")
public class ContactController {

    private ContactService contactService;

    @Autowired
    public void setContactService(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/create")
    public String createContactForm(Contact contact) {
        return "contact-create";
    }

    @GetMapping
    public String listContacts(Model model) {
        model.addAttribute("contacts", contactService.getContacts());
        return "contact-list";
    }

    @PostMapping("/create")
    public String createContact(@Valid Contact contact, BindingResult bindingResult) {

        if (bindingResult.hasErrors()){
            return "/contact-create";
        }

        contactService.saveContact(contact);
        return "redirect:/contact";
    }

    @GetMapping("/delete/{id}")
    public String deleteContact(@PathVariable UUID id) {
        contactService.deleteContact(id);
        return "redirect:/contact";
    }

    @GetMapping("/edit/{id}")
    public String editContactForm(@PathVariable UUID id, Model model) {
        Contact c= contactService.findById(id);
        model.addAttribute("contact", c);
        return "contact-create";
    }

    @PostMapping("/edit/{id}")
    public String editContact(Contact contact) {
        contactService.saveContact(contact);
        return "redirect:/contact";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }

}
