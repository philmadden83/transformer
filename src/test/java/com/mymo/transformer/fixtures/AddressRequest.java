package com.mymo.transformer.fixtures;

import com.mymo.transformer.annotation.TargetField;
import com.mymo.transformer.annotation.Translation;

@Translation(Address.class)
public class AddressRequest {
    @TargetField("addressId")
    private Long id;
    @TargetField("company")
    private String companyName;
    private String line1;
    private String line2;
    private String aptOrSuite;
    private String city;
    private String postalCode;
    private String phone1;
    private String phone2;
    private State state;
    private Country country;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getAptOrSuite() {
        return aptOrSuite;
    }

    public void setAptOrSuite(String aptOrSuite) {
        this.aptOrSuite = aptOrSuite;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
