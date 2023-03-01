package com.spring_boot.backend.restapi.model;

public class UpdateProfileRequest {

    private String address;
    private int experience;
    private String phone_no;

    

    public UpdateProfileRequest(String address, int experience, String phone_no) {
        this.address = address;
        this.experience = experience;
        this.phone_no = phone_no;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public int getExperience() {
        return experience;
    }
    public void setExperience(int experience) {
        this.experience = experience;
    }
    public String getPhone_no() {
        return phone_no;
    }
    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

}
