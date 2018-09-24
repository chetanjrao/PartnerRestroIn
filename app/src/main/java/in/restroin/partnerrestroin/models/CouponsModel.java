package in.restroin.partnerrestroin.models;

public class CouponsModel {
    private String coupon_id, coupon_code, description;

    public String getCoupon_id() {
        return coupon_id;
    }

    public CouponsModel(String coupon_id, String coupon_code, String description) {
        this.coupon_id = coupon_id;
        this.coupon_code = coupon_code;
        this.description = description;
    }

    public void setCoupon_id(String coupon_id) {
        this.coupon_id = coupon_id;
    }

    public String getCoupon_code() {
        return coupon_code;
    }

    public void setCoupon_code(String coupon_code) {
        this.coupon_code = coupon_code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
