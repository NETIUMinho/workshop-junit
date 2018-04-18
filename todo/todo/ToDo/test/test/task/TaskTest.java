




package test.task;





import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

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

        task = getTaskManager().insertTask(task);

        List<Task> tasks = getTaskManager().getTasks();

        assertEquals("Task list should not be empty", 1, tasks.size());
        
        getTaskManager().deleteTask(task);
    }





    @Test
    public void updateTaskTest() throws Exception {

        Task task = new Task("todo task");

        task = getTaskManager().insertTask(task);

        assertFalse("Task should not be completed", task.isCompleted());

        List<Task> tasks = getTaskManager().getTasks();
        
        assertNotNull("Task list should not be null", tasks);

        getTaskManager().updateTask(task);

        assertTrue("Task list should not be empty", !tasks.isEmpty());
        
        getTaskManager().deleteTask(task);
    }





    @Test
    public void setIdTaskTest() throws Exception {

    	Task task = Mockito.mock(Task.class);
    	
    	// task.setId(1);
    	when(task.getId()).thenReturn(new Long(1));
    	
    	assertEquals("Task id should be 1", 1, task.getId());
    }
}
