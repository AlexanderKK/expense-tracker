package me.alexander.expensetracker.model.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import me.alexander.expensetracker.validation.user.FieldMatch;
import me.alexander.expensetracker.validation.user.UniqueEmail;

@FieldMatch(
        first = "password",
        second = "passwordConfirm",
        message = "Please enter matching passwords"
)
public class RegisterDTO {

    @UniqueEmail
    @Email(message = "Please enter a valid email", regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    private String email;

    @Pattern(
        message =
            "Please enter a password with minimum length of 8 characters.\n" +
            "At least one uppercase letter, one lowercase letter, one digit and one special character (e.g., !@#$%^&*).",
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")
    private String password;

    private String passwordConfirm;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

}
