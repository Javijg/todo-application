package ch.cern.todo.mocks;

import ch.cern.todo.model.Task;

import java.util.ArrayList;
import java.util.List;

public class MockedTask {

    public static List<Task> mockedTaskList = new ArrayList<Task>() {{
        add(new Task(1l, "Task 1", "Description 1", null,MockedTaskCategory.mockedTaskCategory));
        add(new Task(2l, "Task 2", "Description 2", null,MockedTaskCategory.mockedTaskCategory));
        add(new Task(3l, "Task 3", "Description 3", null,MockedTaskCategory.mockedTaskCategory));
    }};

    public static Task mockedTask = mockedTaskList.get(0);

}
