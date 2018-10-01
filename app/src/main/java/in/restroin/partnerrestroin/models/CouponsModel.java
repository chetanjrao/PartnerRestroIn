package in.restroin.partnerrestroin.models;

public class CouponsModel {
    private String coupon_id, coupon_code, description, coupon_image;
    private boolean isChecked;

    public String getCoupon_id() {
        return coupon_id;
    }

    public void setCoupon_id(String coupon_id) {
        this.coupon_id = coupon_id;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
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

    public String getCoupon_image() {
        return coupon_image;
    }

    public void setCoupon_image(String coupon_image) {
        this.coupon_image = coupon_image;
    }

    public CouponsModel(String coupon_id, String coupon_code, String description, String coupon_image) {
        this.coupon_id = coupon_id;
        this.coupon_code = coupon_code;
        this.description = description;
        this.coupon_image = coupon_image;
    }
}
