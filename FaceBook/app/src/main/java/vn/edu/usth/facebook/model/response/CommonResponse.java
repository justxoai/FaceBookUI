package vn.edu.usth.facebook.model.response;

public class CommonResponse {
    private int status;
    private String message;

    public CommonResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
}
