package ch.cern.todo.controller;

import ch.cern.todo.mocks.MockedTask;
import ch.cern.todo.model.Task;
import ch.cern.todo.service.TaskService;
import ch.cern.todo.utils.JsonUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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

@WebMvcTest(TaskController.class)
public class TaskControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void successSaveTask() throws Exception {
        // Arrange
        Task mockTask = MockedTask.mockedTask;
        String stringifiedMockTask = JsonUtils.valueToString(mockTask);

        // Mock the service method
        when(taskService.save(any(Task.class))).thenReturn(mockTask);

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/api/task")
                        .content(stringifiedMockTask)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().json(stringifiedMockTask));
    }

    @Test
    public void getTask_success() throws Exception {
        // Arrange
        Task mockTask = MockedTask.mockedTask;
        String stringifiedMockTask = JsonUtils.valueToString(mockTask);
        // Mock the service method
        when(taskService.findById(1l)).thenReturn(mockTask);

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/task/1")
                        .content(stringifiedMockTask)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().json(stringifiedMockTask));
    }

    @Test
    public void getTask_NotFound() throws Exception {
        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/task/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(content().string(""));
    }

    @Test
    public void getAllTasks_success() throws Exception {
        // Arrange
        List<Task> mockedTasks = MockedTask.mockedTaskList;
        // Mock the service method
        when(taskService.findAll()).thenReturn(mockedTasks);

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/task")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(mockedTasks.size()))
                .andExpect(content().json(JsonUtils.valueToString(mockedTasks)));
    }

    @Test
    public void getAllTasks_noContent() throws Exception {
        // Mock the service method
        when(taskService.findAll()).thenReturn(null);

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/task")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andExpect(content().string(""));
    }

    @Test
    public void deleteTask_success() throws Exception {
        // Mock the service method
        when(taskService.findById(1l)).thenReturn(MockedTask.mockedTask);

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/task/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    public void deleteTask_notFound() throws Exception {
        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/task/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(content().string(""));
    }
}
