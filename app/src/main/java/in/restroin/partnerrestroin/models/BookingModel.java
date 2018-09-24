package in.restroin.partnerrestroin.models;

public class BookingModel {
    String booking_id, guest_name;

    public String getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(String booking_id) {
        this.booking_id = booking_id;
    }

    public String getGuest_name() {
        return guest_name;
    }

    public void setGuest_name(String guest_name) {
        this.guest_name = guest_name;
    }

    public BookingModel(String booking_id, String guest_name) {

        this.booking_id = booking_id;
        this.guest_name = guest_name;
    }
}
