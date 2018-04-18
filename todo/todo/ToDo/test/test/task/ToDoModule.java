package test.task;





import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import todo.task.TaskManager;





public class ToDoModule {

    private static TaskManager manager;





    public TaskManager getTaskManager() {

        return manager;

    }





    @BeforeClass
    public static void serverStartup() throws ClassNotFoundException, SQLException {

        Class.forName("org.hsqldb.jdbcDriver");

        // in memory database
        Connection connection = DriverManager.getConnection("jdbc:hsqldb:mem", "sa", "");

        // create model
        Statement st = connection.createStatement();
        st.execute("CREATE SEQUENCE TASKID");
        st.execute("CREATE TABLE TASKS ( id INTEGER IDENTITY, description VARCHAR(256), complete INTEGER )");

        manager = new TaskManager(connection);

    }





    @AfterClass
    public static void serverTearDown() {

    }
}
