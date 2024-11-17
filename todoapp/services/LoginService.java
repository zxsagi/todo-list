package todoapp.services;

import org.springframework.stereotype.Component;

@Component
public class LoginService {
    public static String USERNAME = "jeremypanjaitan";
    public static String PASSWORD = "12345";

    public Boolean validateCredentials(String username, String password) {
        return username.equals(USERNAME) && password.equals(PASSWORD);
    }
}
