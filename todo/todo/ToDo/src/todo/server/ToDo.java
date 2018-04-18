package todo.server;





import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import todo.task.Task;
import todo.task.TaskManager;





public class ToDo {

    public static void main(String[] args) throws Exception {

        Server server = new Server(8081);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        Class.forName("org.hsqldb.jdbcDriver");
        Connection connection = DriverManager.getConnection("jdbc:hsqldb:db", "sa", "");

        // launch HSQL Database GUI
        org.hsqldb.util.DatabaseManagerSwing.main(new String[] { "--noexit", "--url", "jdbc:hsqldb:db" });

        // setup database
        // setupDb(connection);

        context.addServlet(new ServletHolder(new MainServlet(connection)), "/*");
        context.addServlet(new ServletHolder(new UpdateServlet(connection)), "/update/*");

        server.setHandler(context);


        server.start();
        server.join();

    }





    private static Connection setupDb(Connection connection) throws ClassNotFoundException, SQLException {

        Statement st = connection.createStatement();
        st.execute("CREATE SEQUENCE TASKID");
        st.execute("CREATE TABLE TASKS ( id INTEGER IDENTITY, description VARCHAR(256), complete INTEGER )");

        TaskManager manager = new TaskManager(connection);

        manager.insertTask(new Task("task1"));
        manager.insertTask(new Task("task2"));
        manager.insertTask(new Task("task3"));
        manager.insertTask(new Task("task4"));

        return connection;

    }
}