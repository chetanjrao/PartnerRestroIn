/*
 * Copyright (c) 2018. An change in this code must be done under my supervision and any misuse my lead to legal actions
 */

package in.restroin.partnerrestroin.models;

import android.net.Uri;

public class ProfileModel {
    private String name, email, mobile, status, token;
    private Uri profile_image;

    public ProfileModel(String name, String email, String mobile, String status, String token, Uri profile_image) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.status = status;
        this.token = token;
        this.profile_image = profile_image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Uri getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(Uri profile_image) {
        this.profile_image = profile_image;
    }
}
