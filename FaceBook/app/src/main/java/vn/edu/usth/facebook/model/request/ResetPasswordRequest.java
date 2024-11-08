package vn.edu.usth.facebook.model.request;

public class ResetPasswordRequest {
    private String token;
    private String newPassword;
    private String confirmPassword;

    // Getters and Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public String toString() {
        return "ResetPasswordRequest{" +
                "confirmPassword='" + confirmPassword + '\'' +
                ", token='" + token + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }
}