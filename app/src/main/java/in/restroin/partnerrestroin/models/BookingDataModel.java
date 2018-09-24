package in.restroin.partnerrestroin.models;

public class BookingDataModel {
    private String booking_id, number_of_male, number_of_female, couponSelected, guest_name, guest_phone, guest_email, booking_status, visiting_date_time;

    public BookingDataModel(String booking_id, String number_of_male, String number_of_female, String couponSelected, String guest_name, String guest_phone, String guest_email, String booking_status, String visiting_date_time) {
        this.booking_id = booking_id;
        this.number_of_male = number_of_male;
        this.number_of_female = number_of_female;
        this.couponSelected = couponSelected;
        this.guest_name = guest_name;
        this.guest_phone = guest_phone;
        this.guest_email = guest_email;
        this.booking_status = booking_status;
        this.visiting_date_time = visiting_date_time;
    }

    public String getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(String booking_id) {
        this.booking_id = booking_id;
    }

    public String getNumber_of_male() {
        return number_of_male;
    }

    public void setNumber_of_male(String number_of_male) {
        this.number_of_male = number_of_male;
    }

    public String getNumber_of_female() {
        return number_of_female;
    }

    public void setNumber_of_female(String number_of_female) {
        this.number_of_female = number_of_female;
    }

    public String getCouponSelected() {
        return couponSelected;
    }

    public void setCouponSelected(String couponSelected) {
        this.couponSelected = couponSelected;
    }

    public String getGuest_name() {
        return guest_name;
    }

    public void setGuest_name(String guest_name) {
        this.guest_name = guest_name;
    }

    public String getGuest_phone() {
        return guest_phone;
    }

    public void setGuest_phone(String guest_phone) {
        this.guest_phone = guest_phone;
    }

    public String getGuest_email() {
        return guest_email;
    }

    public void setGuest_email(String guest_email) {
        this.guest_email = guest_email;
    }

    public String getBooking_status() {
        return booking_status;
    }

    public void setBooking_status(String booking_status) {
        this.booking_status = booking_status;
    }

    public String getVisiting_date_time() {
        return visiting_date_time;
    }

    public void setVisiting_date_time(String visiting_date_time) {
        this.visiting_date_time = visiting_date_time;
    }
}
