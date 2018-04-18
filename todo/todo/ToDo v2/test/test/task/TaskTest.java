




package test.task;





import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import todo.task.Task;





public class TaskTest extends ToDoModule {

	@Before
	public void beforeTest() {
	}

	@After
	public void afterTest() {
	}





    @Test
    public void getTasksTest() throws SQLException, ClassNotFoundException {

        List<Task> tasks = getTaskManager().getTasks();

        assertNotNull("Task list should not be null", tasks);
    }





    @Test
    public void insertTaskTest() throws SQLException {

        Task task = new Task("todo task");

        getTaskManager().insertTask(task);

        List<Task> tasks = getTaskManager().getTasks();

        assertTrue("Task list should not be empty", !tasks.isEmpty());
    }





    @Test
    public void updateTaskTest() throws Exception {

        Task task = new Task("todo task");

        getTaskManager().insertTask(task);

        List<Task> tasks = getTaskManager().getTasks();
        
        assertNotNull("Task list should not be null", tasks);

        task.isCompleted();
        getTaskManager().updateTask(task);

        assertTrue("Task list should not be empty", !tasks.isEmpty());
    }





    @Test
    public void getTasksOfUserTest() throws SQLException, ClassNotFoundException {

        List<Task> tasks = getTaskManager().getTasks("user");

        assertNotNull("Task list should not be null", tasks);
    }





    @Test
    public void insertTaskWithUserTest() throws SQLException {

        Task task = new Task("todo task");
        task.setUser("user");

        getTaskManager().insertTask(task);

        List<Task> tasks = getTaskManager().getTasks("user");

        assertTrue("Task list should not be empty", !tasks.isEmpty());
    }





    @Test
    public void updateTaskWithUserTest() throws Exception {

        Task task = new Task("todo task");
        task.setUser("user");

        getTaskManager().insertTask(task);

        getTaskManager().updateTask(task, "user");

    }





    @Test(expected = Exception.class)
    public void updateTaskWithUserExceptionTest() throws Exception {

        Task task = new Task("todo task");
        task.setUser("user");

        getTaskManager().insertTask(task);

        getTaskManager().updateTask(task, "user2");

    }

}
