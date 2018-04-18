




package todo.server;





import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todo.task.Task;
import todo.task.TaskManager;





public class MainServlet extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Connection        _connection;





    public MainServlet(Connection connection) {
        _connection = connection;
    }





    protected void doGet(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException {

        TaskManager manager = new TaskManager(_connection);
        List<Task> tasks = null;
        try {
            tasks = manager.getTasks();
        } catch (SQLException e) {
            throw new ServletException(e);
        }

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("<table>");
        for (Task task : tasks) {
            response.getWriter()
.println("<tr><td>" + task.getDescription() + "</td><td>" + task.isCompleted()
                             + "</td><td><a href=\"/update?id=" + task.getId()
                             + "&complete=true\">complete</a></td></td><td><a href=\"/update?id=" + task.getId()
                             + "&complete=false\">uncomplete</a></td></tr>");
        }
        response.getWriter().println("</table>");

    }
}
