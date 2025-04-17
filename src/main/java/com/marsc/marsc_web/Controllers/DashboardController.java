package com.marsc.marsc_web.Controllers;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.marsc.marsc_web.Entities.Contact;
import com.marsc.marsc_web.Repositories.ContactRepository;
import com.marsc.marsc_web.Services.MailService;

@Controller
@RequestMapping("/Marsc")
public class DashboardController {

 
    @GetMapping("/dashboard")
    public String dashboard() {
         
        return "index"; 
    }
    
    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private MailService mailService;

    @PostMapping("/contact")
    public String submitContactForm(@ModelAttribute Contact contact, Model model, RedirectAttributes redirectAttributes) {
        // Save to DB (optional)
        contactRepository.save(contact);

        // Send Email
        mailService.sendContactEmail(
            contact.getEmail(),
            contact.getName(),
            contact.getSubject(),
            contact.getMessage()
        );
        
        // Send response to user
        mailService.sendResponseToUser(
            contact.getEmail(),
            contact.getName()
        );

        redirectAttributes.addFlashAttribute("message", "Thanks! Your message has been sent.");
        return "redirect:/Marsc/dashboard";
    }
}