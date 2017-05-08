package com.mymo.transformer.fixtures;

import com.mymo.transformer.annotation.TargetField;
import com.mymo.transformer.annotation.Translation;

@Translation(Customer.class)
public class CustomerRequest {
    @TargetField("customerId")
    private Long id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private AddressRequest address;

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

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public AddressRequest getAddress() {
        return address;
    }

    public void setAddress(AddressRequest address) {
        this.address = address;
    }
}
