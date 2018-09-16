package in.restroin.partnerrestroin.models;

public class AuthenModel {
    private String error_code, message, access_token, partner_id;

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getPartner_id() {
        return partner_id;
    }

    public void setPartner_id(String partner_id) {
        this.partner_id = partner_id;
    }

    public AuthenModel(String error_code, String message, String access_token, String partner_id) {

        this.error_code = error_code;
        this.message = message;
        this.access_token = access_token;
        this.partner_id = partner_id;
    }
}
