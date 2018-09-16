/*
 * Copyright (c) 2018. An change in this code must be done under my supervision and any misuse my lead to legal actions
 */

package in.restroin.partnerrestroin.models;

import com.google.gson.annotations.SerializedName;

public class LoginModel {
    @SerializedName("login_username") private String login_username;
    @SerializedName("login_password") private String login_password;

    public LoginModel(String login_username, String login_password) {
        this.login_username = login_username;
        this.login_password = login_password;
    }

    public String getLogin_username() {
        return login_username;
    }

    public void setLogin_username(String login_username) {
        this.login_username = login_username;
    }

    public String getLogin_password() {
        return login_password;
    }

    public void setLogin_password(String login_password) {
        this.login_password = login_password;
    }
}
