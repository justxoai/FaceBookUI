package vn.edu.usth.facebook.model.request;

public class ResetPasswordRequest {
    private String password;
    private String confirmPassword;

    // Getters and Setters

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
                ", password='" + password + '\'' +
                '}';
    }
}