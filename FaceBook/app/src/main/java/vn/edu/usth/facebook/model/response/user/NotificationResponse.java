package vn.edu.usth.facebook.model.response.user;

import java.util.List;

public class NotificationResponse {
    private List<String> message;
    private List<String> dateCreated;

    public List<String> getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(List<String> dateCreated) {
        this.dateCreated = dateCreated;
    }

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "NotificationResponse{" +
                "dateCreated=" + dateCreated +
                ", message=" + message +
                '}';
    }
}
