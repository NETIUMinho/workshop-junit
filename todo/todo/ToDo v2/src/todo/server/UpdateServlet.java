




package todo.server;





import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todo.task.Task;
import todo.task.TaskManager;





public class UpdateServlet extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Connection        _connection;





    public UpdateServlet(Connection connection) {
        _connection = connection;
    }





    protected void doGet(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException {

        long id = Long.parseLong(request.getParameter("id"));
        boolean complete = Boolean.parseBoolean(request.getParameter("complete"));

        TaskManager manager = new TaskManager(_connection);

        Task taskToUpdate = null;

        try {
            for (Task task : manager.getTasks()) {
                if (task.getId() == id) {
                    taskToUpdate = task;
                }
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }

        taskToUpdate.setCompleted(complete);
        try {
            manager.updateTask(taskToUpdate);
        } catch (Exception e) {
            throw new ServletException(e);
        }

        MainServlet servlet = new MainServlet(this._connection);
        servlet.doGet(request, response);

    }
}
