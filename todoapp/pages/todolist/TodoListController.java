package todoapp.pages.todolist;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import todoapp.entities.TodoList;
import todoapp.services.TodoListService;

@Component
public class TodoListController implements Initializable {
    private final TodoListService todoListService;
    @FXML
    ListView<TodoList> listView;

    @FXML
    Button saveButton;

    @FXML
    TextField inputTodo;

    @FXML
    Button deleteButton;


    @Autowired
    public TodoListController(final TodoListService todolistService) {
        this.todoListService = todolistService;
    }

    @Override
    public void initialize(final URL url, final ResourceBundle resourceBundle) {
        populateTodoListData();
        saveButton.setOnAction(this::handleSaveTodoButton);
        deleteButton.setOnAction(this::handleDeleteButton);
        deleteButton.setDisable(true);

    }

    private void handleSaveTodoButton(ActionEvent event) {
        TodoList selectedTodo = listView.getSelectionModel().getSelectedItem();
        if (Objects.nonNull(selectedTodo)) {
            handleEditTodo(listView.getSelectionModel().getSelectedIndex() + 1);
        } else {
            String newTodo = inputTodo.getText();
            todoListService.addTodoList(newTodo);
            inputTodo.setText("");
            populateTodoListData();
        }
        deleteButton.setDisable(true);
    }

    private void handleEditTodo(final Integer id) {
        String newTodo = inputTodo.getText();
        todoListService.editTodoList(id, newTodo);
        inputTodo.setText("");
        populateTodoListData();
    }

    private void handleDeleteButton(ActionEvent event) {
        todoListService.removeTodoList(listView.getSelectionModel().getSelectedIndex() + 1);
        populateTodoListData();
        deleteButton.setDisable(true);
        inputTodo.setText("");
    }


    private void populateTodoListData() {
        List<TodoList> todolists = Arrays.stream(todoListService.getTodoList()).toList();
        ObservableList<TodoList> items = FXCollections.observableArrayList(todolists);
        listView.setItems(items);

        listView.setOnMouseClicked(event -> {
            TodoList selectedItem = listView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                inputTodo.setText(selectedItem.getTodo());
                deleteButton.setDisable(false);
            }
        });
    }
}
