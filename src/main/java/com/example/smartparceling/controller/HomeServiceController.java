/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 25-Jul-21
 *   Time: 11:08 AM
 *   File: HomeSerciveController.java
 */

package com.example.smartparceling.controller;

import com.example.smartparceling.database.PersonRepository;
import com.example.smartparceling.entity.Address;
import com.example.smartparceling.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class HomeServiceController {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/loginsuccess")
    public String loginSuccess(@RequestParam("file") MultipartFile file,
                               Model model, @RequestParam(name = "confirm_password")
                                       String password, @ModelAttribute @Valid Person person, BindingResult result) throws Exception {
        person.getAddress().setPerson(person);
        Address address = person.getAddress();
        model.addAttribute("passwords", password);
        model.addAttribute("pass", false);
        model.addAttribute("userAvailable", false);
        model.addAttribute("img_true", false);
        Person personList = personRepository.findPersonByUserName(person.getUserName());
        if (!(personList == null)) {
            model.addAttribute("userAvailable", true);
            model.addAttribute("person", person);
            model.addAttribute("address", address);
            model.addAttribute("title", "Sign Up");
            return "Login";
        } else if (!person.getPassword().equals(password)) {
            model.addAttribute("pass", true);
            model.addAttribute("person", person);
            model.addAttribute("address", address);
            model.addAttribute("title", "Sign Up");
            return "Login";
        } else if (address.getHouseNumber().equals("") || address.getStreet().equals("") || address.getCity().equals("") ||
                address.getZip().length() != 6 || address.getState().equals("Choose...")) {
            model.addAttribute("person", person);
            model.addAttribute("address", address);
            model.addAttribute("title", "Sign Up");
            model.addAttribute("zip_true", true);
            return "Login";
        } else if (result.hasErrors()) {
            model.addAttribute("person", person);
            model.addAttribute("address", address);
            model.addAttribute("title", "Sign Up");
            return "Login";
        } else if (file.isEmpty()) {
            model.addAttribute("img_true", true);
            model.addAttribute("person", person);
            model.addAttribute("address", address);
            model.addAttribute("title", "Sign Up");
            return "Login";
        } else {
            Path path = Paths.get(new ClassPathResource("").getFile().getAbsolutePath() +
                    "\\static\\img\\pro.png");
            byte[] data = Files.readAllBytes(path);
            person.setImage(data);
            byte[] fileArray = file.getBytes();
            System.out.println(file.getName());
            person.setAdhaar(fileArray);
            person.setAdhaarVerified(false);
            person.setEmailVerified(false);
            person.setPhoneVerified(true);
            person.setAccountNonExpired(true);
            person.setEnabled(true);
            person.setCredentialsNonExpired(true);
            person.setAccountNonLocked(true);
            person.setRole("ROLE_USER");
            person.setAccountBalance(10);
            person.setPassword(bCryptPasswordEncoder.encode(person.getPassword()));
            personRepository.save(person);
            return "redirect:/user/dashboard/1";
        }

    }
}