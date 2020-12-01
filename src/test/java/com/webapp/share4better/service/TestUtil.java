package com.webapp.share4better.service;

import com.webapp.share4better.model.Address;
import com.webapp.share4better.model.Contact;
import com.webapp.share4better.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestUtil {

    @Autowired
    private IAddressService addressService;
    @Autowired
    private  IContactService contactService;

    @Autowired
    private IProfileService profileService;

    private Contact contact_1 = createContact(999999991,"123-456-7891","987-543-1234");
    private Contact contact_2 = createContact(999999992,"987654321","987654321");

    private Address address_1 = createAddress(999999991,"300001 Mill shop Drive charlotte NC 28222","123 Mill Drive charlotte NC 28111");
    private Address address_2 = createAddress(999999992,"300001 Mill shop Drive charlotte NC 28222","123 Mill Drive charlotte NC 28111");

    private Profile profile_1 = createProfile(99999999,"FirstName LastName","test@test.com","123456");


    public void addAddressTestData(){
        addressService.updateAddressInfo(address_1);
        addressService.updateAddressInfo(address_2);
    }

    public void removeAddressTestData(){
        addressService.removeAddress(address_1);
        addressService.removeAddress(address_2);

    }

    public void addContactTestData(){
        contactService.updateContactInfo(contact_1);
        contactService.updateContactInfo(contact_2);
    }

    public void removeContactTestData(){
        contactService.removeContactInfo(contact_1);
        contactService.removeContactInfo(contact_2);
    }


    public void addProfileTestData(){
        profileService.addUser(profile_1);
    }

    public void removeProfileTestData(){
        profileService.removeUser(99999999);
    }

    public Address createAddress(int id, String home, String work){
        Address address = new Address();
        address.setId(id);
        address.setHome_address(home);
        address.setWork_address(work);
        return address;
    }

    public Contact createContact(int id, String phone, String additional){
        Contact contact = new Contact();
        contact.setId(id);
        contact.setPhone_number(phone);
        contact.setAdditional_number(additional);
        return contact;
    }

    public Profile createProfile(int id, String userName, String email, String password){
        Profile profile = new Profile();
        profile.setUser_id(id);
        profile.setUser_name(userName);
        profile.setUser_email(email);
        profile.setDonor_status(true);
        profile.setUser_password(password);
        return profile;
    }
}