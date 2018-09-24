package in.restroin.partnerrestroin.models;

public class RestaurantProfileModel {
    private String restaurant_id, restaurant_rating, total_earned, total_bookings, total_customers;

    public String getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(String restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public String getRestaurant_rating() {
        return restaurant_rating;
    }

    public void setRestaurant_rating(String restaurant_rating) {
        this.restaurant_rating = restaurant_rating;
    }

    public String getTotal_earned() {
        return total_earned;
    }

    public void setTotal_earned(String total_earned) {
        this.total_earned = total_earned;
    }

    public String getTotal_bookings() {
        return total_bookings;
    }

    public void setTotal_bookings(String total_bookings) {
        this.total_bookings = total_bookings;
    }

    public String getTotal_customers() {
        return total_customers;
    }

    public void setTotal_customers(String total_customers) {
        this.total_customers = total_customers;
    }

    public RestaurantProfileModel(String restaurant_id, String restaurant_rating, String total_earned, String total_bookings, String total_customers) {

        this.restaurant_id = restaurant_id;
        this.restaurant_rating = restaurant_rating;
        this.total_earned = total_earned;
        this.total_bookings = total_bookings;
        this.total_customers = total_customers;
    }
}
