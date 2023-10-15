package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Optional;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * SYSC 4806 Lab 4, Fall 2023
 * The AddressBook Controller used to handle the different mappings in the application.
 * @author Max Curkovic 101139937
 */
@Controller
public class AppController {
    @Autowired
    private AddressBookRepository addressBookRepository;
    @Autowired
    private BuddyInfoRepository buddyInfoRepository;


    @GetMapping("/")
    public String home(Model model){
        List<AddressBook> addressbooks = addressBookRepository.findAll();
        model.addAttribute("AddressBooks", addressbooks);
        return "index";
    }

    @GetMapping("/addaddressbook")
    public String addAddressBook(Model model){
        AddressBook ab = new AddressBook();
        addressBookRepository.save(ab);
        model.addAttribute("AddressId", ab.getId());
        return "addAddressBook";
    }

    @GetMapping("/displayaddressbook")
    public String getAddressBook(@RequestParam Long id, Model model){
        Optional<AddressBook> ab = addressBookRepository.findById(id);
        if (!ab.isPresent()){
            return "index";
        }
        model.addAttribute("AddressBook", ab.get());
        return "displayAddressBook";
    }

    @GetMapping("/addBuddy")
    public String addBuddy(@RequestParam Long id, Model model){
        BuddyInfoWithId buddyDTO = new BuddyInfoWithId(id, new BuddyInfo());
        model.addAttribute("BuddyDTO", buddyDTO);
        return "addBuddy";
    }

    @GetMapping("/removeBuddy")
    public String removeBuddy(@RequestParam Long id, Model model){
        BuddyInfoWithId buddyDTO = new BuddyInfoWithId(id);
        model.addAttribute("BuddyDTO", buddyDTO);
        return "removeBuddy";
    }

    @PostMapping("/createdBuddy")
    public String createBuddy(@ModelAttribute("BuddyDTO") BuddyInfoWithId buddyDTO, Model model) {
        Optional<AddressBook> ab = addressBookRepository.findById(buddyDTO.getAddressBookId());
        if (!ab.isPresent()){
            return "index";
        }
        AddressBook addressBook = ab.get();
        addressBook.addBuddy(buddyDTO.getBuddy());
        addressBookRepository.save(addressBook);
        model.addAttribute("AddressBook", addressBook);
        return "displayAddressBook";
    }

    @PostMapping("/createdBuddyManual")
    public String createdBuddy(@RequestParam Long id, @RequestParam String name, @RequestParam String number, RedirectAttributes redirectAttributes) {
        Optional<AddressBook> ab = addressBookRepository.findById(id);
        if (!ab.isPresent()){
            return "redirect:/";
        }
        AddressBook addressBook = ab.get();
        BuddyInfo buddy = new BuddyInfo("Test", "123 Test", "613-Test");
        addressBook.addBuddy(buddy);
        addressBookRepository.save(addressBook);
        redirectAttributes.addAttribute("id", addressBook.getId());
        return "redirect:displayaddressbook";
    }

    @PostMapping ("/removingBuddy")
    public String removeBuddy(@ModelAttribute("BuddyDTO") BuddyInfoWithId buddyDTO, RedirectAttributes redirectAttributes) {
        Optional<AddressBook> ab = addressBookRepository.findById(buddyDTO.getAddressBookId());
        Optional<BuddyInfo> b = buddyInfoRepository.findById(buddyDTO.getBuddyId());

        if (!ab.isPresent() || !b.isPresent()){
            return "redirect:/";
        }
        AddressBook addressBook = ab.get();
        BuddyInfo buddy = b.get();

        addressBook.removeBuddy(buddy);
        addressBookRepository.save(addressBook);
        redirectAttributes.addAttribute("id", addressBook.getId());
        return "redirect:displayaddressbook";
    }

    @PostMapping("/removingBuddyManual")
    public String deletedbuddy(@RequestParam Long id, @RequestParam String name, RedirectAttributes redirectAttributes) {
        Optional<AddressBook> ab = addressBookRepository.findById(id);
        Optional<BuddyInfo> b = buddyInfoRepository.findByName(name);

        if (!ab.isPresent() || !b.isPresent()){
            return "redirect:/";
        }
        AddressBook addressBook = ab.get();
        BuddyInfo buddy = b.get();

        addressBook.removeBuddy(buddy);
        addressBookRepository.save(addressBook);
        redirectAttributes.addAttribute("id", addressBook.getId());
        return "redirect:displayaddressbook";
    }

}