package ch.cern.todo.controller;

import ch.cern.todo.mocks.MockedTaskCategory;
import ch.cern.todo.model.TaskCategory;
import ch.cern.todo.service.TaskCategoryService;
import ch.cern.todo.utils.JsonUtils;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
@WebMvcTest(TaskCategoryController.class)
public class TaskCategoryControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskCategoryService taskCategoryService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void successSaveTaskCategory() throws Exception {
        // Arrange
        TaskCategory mockTaskCategory = MockedTaskCategory.mockedTaskCategory;
        String stringifiedMockTaskCategory = JsonUtils.valueToString(mockTaskCategory);

        // Mock the service method
        when(taskCategoryService.save(any(TaskCategory.class))).thenReturn(mockTaskCategory);

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/api/task-category")
                        .content(stringifiedMockTaskCategory)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().json(stringifiedMockTaskCategory));
    }

    @Test
    public void getTask_success() throws Exception {
        // Arrange
        TaskCategory mockTaskCategory = MockedTaskCategory.mockedTaskCategory;
        String stringifiedMockTaskCategory = JsonUtils.valueToString(mockTaskCategory);
        // Mock the service method
        when(taskCategoryService.findById(1l)).thenReturn(mockTaskCategory);

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/task-category/1")
                        .content(stringifiedMockTaskCategory)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().json(stringifiedMockTaskCategory));
    }

    @Test
    public void getTask_NotFound() throws Exception {
        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/task-category/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(content().string(""));
    }

    @Test
    public void getAllTasks_success() throws Exception {
        // Arrange
        List<TaskCategory> mockedTaskCategories = MockedTaskCategory.mockedTaskCategoryList;
        // Mock the service method
        when(taskCategoryService.findAll()).thenReturn(mockedTaskCategories);

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/task-category")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(mockedTaskCategories.size()))
                .andExpect(content().json(JsonUtils.valueToString(mockedTaskCategories)));
    }

    @Test
    public void getAllTasks_noContent() throws Exception {
        // Mock the service method
        when(taskCategoryService.findAll()).thenReturn(null);

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/task-category")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andExpect(content().string(""));
    }

    @Test
    public void deleteTask_success() throws Exception {
        when(taskCategoryService.findById(1l)).thenReturn(MockedTaskCategory.mockedTaskCategory);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/task-category/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    public void deleteTask_notFound() throws Exception {
        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/task-category/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(content().string(""));
    }
}
