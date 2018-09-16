package in.restroin.partnerrestroin.models;

public class MessageModel {
    private String message, error_code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public MessageModel(String message, String error_code) {

        this.message = message;
        this.error_code = error_code;
    }
}
