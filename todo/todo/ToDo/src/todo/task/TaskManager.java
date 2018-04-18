




package todo.task;





import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;





public class TaskManager {

    private Connection _connection;





    public TaskManager(Connection connection) {
        this._connection = connection;
    }





    public List<Task> getTasks() throws SQLException {

        List<Task> tasks = new ArrayList<Task>();

        try (Statement st = _connection.createStatement()) {

            ResultSet rs = st.executeQuery("SELECT ID, DESCRIPTION, COMPLETE FROM TASKS ORDER BY ID ASC");

            while (rs.next()) {

                long id = rs.getLong(1);
                String description = rs.getString(2);
                boolean completed = rs.getInt(3) == 1;

                tasks.add(new Task(id, description, completed));

            }

        }

        return tasks;

    }





    public Task insertTask(Task task) throws SQLException {

        try (PreparedStatement st = _connection.prepareStatement("call NEXT VALUE FOR taskid")) {

            ResultSet rs = st.executeQuery();
            rs.next();
            long id = rs.getLong(1);
            task.setId(id);
            
        }

        try (PreparedStatement st = _connection.prepareStatement("INSERT INTO TASKS (ID, DESCRIPTION, COMPLETE) VALUES (?, ?,?) ")) {

            st.setLong(1, task.getId());
            st.setString(2, task.getDescription());
            st.setInt(3, task.isCompleted() ? 1 : 0);

            st.execute();

        }

        return task;

    }





    public Task updateTask(Task task) throws SQLException {

        try (PreparedStatement st = _connection.prepareStatement("UPDATE TASKS SET DESCRIPTION = ? , COMPLETE = ? WHERE ID = ? ")) {

            st.setString(1, task.getDescription());
            st.setInt(2, task.isCompleted() ? 1 : 0);
            st.setLong(3, task.getId());

            st.execute();

        }

        return task;

    }





    public void deleteTask(Task task) throws SQLException {

        try (PreparedStatement st = _connection.prepareStatement("DELETE FROM TASKS WHERE ID = ? ")) {

            st.setLong(1, task.getId());

            st.execute();

        }

    }

}
