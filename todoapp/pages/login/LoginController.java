package todoapp.pages.login;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import todoapp.helper.SceneHelper;
import todoapp.pages.todolist.TodoListController;
import todoapp.services.LoginService;

@Component
public class LoginController implements Initializable {
    private final LoginService loginService;
    @FXML
    private PasswordField passwordTextField;

    @FXML
    private TextField usernameTextField;

    @FXML
    private Button loginButton;

    private final TodoListController todoListController;

    @Autowired
    public LoginController(LoginService loginService, TodoListController todoListController) {
        this.loginService = loginService;
        this.todoListController = todoListController;
    }

    void processLogin(ActionEvent event) {
        Boolean isLoginSuccess = loginService.validateCredentials(usernameTextField.getText(), passwordTextField.getText());
        if (isLoginSuccess) {
            SceneHelper.switchScene("todoapp/pages/todolist/todo-list-view.fxml", todoListController);
        } else {
            showPopupMessage("Failed to Login", "Wrong credential", "Wrong username or password", Alert.AlertType.ERROR);
        }
    }

    void showPopupMessage(String title, String headerText, String contentText, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);

        alert.showAndWait();
    }

    @Override
    public void initialize(final URL url, final ResourceBundle resourceBundle) {
        loginButton.setOnAction(this::processLogin);
    }
}
