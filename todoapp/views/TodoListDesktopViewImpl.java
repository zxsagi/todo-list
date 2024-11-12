package todoapp.views;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;
import todoapp.Main;
import todoapp.helper.SceneHelper;
import todoapp.pages.login.LoginController;

@Component("todoListDesktopView")
public class TodoListDesktopViewImpl extends Application implements TodoListView {


    public TodoListDesktopViewImpl() {
    }

    @Override
    public void run() {
        launch();
    }

    @Override
    public void start(final Stage stage) throws Exception {
        SceneHelper.setPrimaryStage(stage);

        LoginController loginController = Main.applicationContext.getBean(LoginController.class);
        SceneHelper.switchScene("todoapp/pages/login/login-view.fxml", loginController);
    }
}
