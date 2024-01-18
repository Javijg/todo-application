package ch.cern.todo.service;

import ch.cern.todo.model.Task;
import ch.cern.todo.repository.TaskRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRespository repository;

    @Autowired
    private TaskCategoryService taskCategoryService;

    public Task save(Task task){
        Task savedEntity = repository.save(task);
        if(savedEntity == null) return savedEntity;
        savedEntity.setCategory(taskCategoryService.findById(savedEntity.getCategory().getId()));
        return task;
    }

    public Task findById(Long taskId){
        return repository.findById(taskId).orElse(null);
    }

    public List<Task> findAll(){
        return repository.findAll(Sort.by(Sort.Direction.ASC, "deadline"));
    }

    public void deleteById(Long taskId){
        repository.deleteById(taskId);
    }
}


