package in.restroin.partnerrestroin.models;

public class NotificationsModel {
    private int booking_id;
    private String notification_text, notification_type;

    public int getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(int booking_id) {
        this.booking_id = booking_id;
    }

    public String getNotification_text() {
        return notification_text;
    }

    public void setNotification_text(String notification_text) {
        this.notification_text = notification_text;
    }

    public String getNotification_type() {
        return notification_type;
    }

    public void setNotification_type(String notification_type) {
        this.notification_type = notification_type;
    }

    public NotificationsModel(int booking_id, String notification_text, String notification_type) {
        this.booking_id = booking_id;
        this.notification_text = notification_text;
        this.notification_type = notification_type;
    }
}
