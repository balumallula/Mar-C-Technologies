package com.marsc.marsc_web.Controllers;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.marsc.marsc_web.Entities.Contact;
import com.marsc.marsc_web.Repositories.ContactRepository;
import com.marsc.marsc_web.Services.MailService;
import jakarta.validation.Valid;


@Controller
@RequestMapping("/")
public class DashboardController {

    @GetMapping
    public String redirectToDashboard() {
        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "index"; 
    }

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private MailService mailService;

    @PostMapping("/contact")
    public String submitContactForm(@ModelAttribute Contact contact,BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        // Save to DB (optional)
    	
    	 if (result.hasErrors()) {
    	        System.out.println("Validation errors: " + result.getAllErrors());
    	        return "redirect:/dashboard";
    	    }
        contactRepository.save(contact);

        // Send Email
        mailService.sendContactEmail(
            contact.getEmail(),
            contact.getName(),
            contact.getSubject(),
            contact.getMessage()
        );

        mailService.sendResponseToUser(
            contact.getEmail(),
            contact.getName()
        );

        redirectAttributes.addFlashAttribute("message", "Thanks! Your message has been sent.");
        return "redirect:/dashboard";
    }
}
