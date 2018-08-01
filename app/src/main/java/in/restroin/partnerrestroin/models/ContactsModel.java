/*
 * Copyright (c) 2018. An change in this code must be done under my supervision and any misuse my lead to legal actions
 */

package in.restroin.partnerrestroin.models;

import java.util.HashMap;

public class ContactsModel {
    private String customer_name, customer_number, customer_email;
    private int customer_image;

    public ContactsModel(String customer_name, String customer_number, String customer_email, int customer_image) {
        this.customer_name = customer_name;
        this.customer_number = customer_number;
        this.customer_email = customer_email;
        this.customer_image = customer_image;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_number() {
        return customer_number;
    }

    public void setCustomer_number(String customer_number) {
        this.customer_number = customer_number;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }

    public int getCustomer_image() {
        return customer_image;
    }

    public void setCustomer_image(int customer_image) {
        this.customer_image = customer_image;
    }
}
