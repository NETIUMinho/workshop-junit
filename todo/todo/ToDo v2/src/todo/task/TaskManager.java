




package todo.task;





import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;





public class TaskManager {

    private Connection _connection;





    public TaskManager(Connection connection) {
        this._connection = connection;
    }





    public List<Task> getTasks(String user) throws SQLException {

        List<Task> tasks = new ArrayList<Task>();

        try (PreparedStatement st = _connection.prepareStatement("SELECT ID, DESCRIPTION, COMPLETE FROM TASKS WHERE USERNAME = ? ORDER BY ID ASC")) {

            if (user == null) {
                st.setNull(1, org.hsqldb.types.Types.VARCHAR);
            } else {
                st.setString(1, user);
            }

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                long id = rs.getLong(1);
                String description = rs.getString(2);
                boolean completed = rs.getInt(3) == 1;

                tasks.add(new Task(id, description, completed));

            }

        }

        return tasks;

    }





    public List<Task> getTasks() throws SQLException {

        List<Task> tasks = new ArrayList<Task>();

        try (PreparedStatement st = _connection.prepareStatement("SELECT ID, DESCRIPTION, COMPLETE FROM TASKS WHERE USERNAME is NULL ORDER BY ID ASC")) {

            ResultSet rs = st.executeQuery();

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

        try (PreparedStatement st = _connection.prepareStatement("INSERT INTO TASKS (ID, DESCRIPTION, COMPLETE, USERNAME) VALUES (?, ?,?, ?) ")) {
            st.setLong(1, task.getId());
            st.setString(2, task.getDescription());
            st.setInt(3, task.isCompleted() ? 1 : 0);
            st.setString(4, task.getUser());

            st.execute();

        }

        return task;

    }





    public Task updateTask(Task task, String user) throws Exception {

        if (user != null && !user.equals(task.getUser())) {
            throw new Exception("user cannot update task!");
        }
        
        try (PreparedStatement st = _connection.prepareStatement("UPDATE TASKS SET DESCRIPTION = ? , COMPLETE = ?, USERNAME = ? WHERE ID = ? ")) {

            st.setString(1, task.getDescription());
            st.setInt(2, task.isCompleted() ? 1 : 0);
            st.setString(3, task.getUser());
            st.setLong(4, task.getId());
            
            st.execute();

        }

        return task;

    }





    public Task updateTask(Task task) throws Exception {

        return updateTask(task, null);

    }





    public void deleteTask(Task task) throws SQLException {

        try (PreparedStatement st = _connection.prepareStatement("DELETE FROM TASKS WHERE ID = ? ")) {

            st.setLong(1, task.getId());

            st.execute();

        }

    }

}
