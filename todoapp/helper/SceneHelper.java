package todoapp.helper;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SceneHelper {
    private static final Logger log = LoggerFactory.getLogger(SceneHelper.class.getName());

    private static Stage primaryStage;

    public static void setPrimaryStage(Stage stage) {
        SceneHelper.primaryStage = stage;
    }

    public static void switchScene(String fxmlFile, Object controllerClass) {
        switchScene(fxmlFile, controllerClass, null);
    }

    public static void switchScene(String fxmlFile, Object controllerClass, Object[] params) {
        FXMLLoader loader = new FXMLLoader(SceneHelper.class.getClassLoader().getResource(fxmlFile));
        if (controllerClass != null) {
            loader.setController(controllerClass);
        }

        try {
            Parent root = loader.load();
            // Jika ada parameter, inisialisasi controller
            if (params != null && params.length > 0) {
                Object controller = loader.getController();
                if (controller instanceof InitializableController) {
                    ((InitializableController) controller).initData(params);
                }
            }

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception error) {
            log.error(error.getMessage(), error);
        }

    }
}