package ch.cern.todo.mocks;

import ch.cern.todo.model.TaskCategory;

import java.util.ArrayList;
import java.util.List;

public class MockedTaskCategory {

    public static List<TaskCategory> mockedTaskCategoryList = new ArrayList<TaskCategory>() {{
        add(new TaskCategory(1l, "Cat 1", "Description 1"));
        add(new TaskCategory(2l, "Cat 2", "Description 2"));
    }};

    public static TaskCategory mockedTaskCategory = new TaskCategory(1l, "Category", null);
}
