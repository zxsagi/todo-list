package todoapp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import todoapp.config.Database;
import todoapp.views.TodoListView;

@ComponentScan(basePackages = "todoapp")
public class Main {

    public static ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Main.class);

    public static void main(String[] args) {
        TodoListView todoListView = applicationContext.getBean("todoListDesktopView", TodoListView.class);
        todoListView.run();
    }

    @Bean
    Database database() {
        Database database = new Database("todo_si", "root", "", "localhost", "3306");
        database.setup();
        return database;
    }
}
