package ch.cern.todo.service;

import ch.cern.todo.model.TaskCategory;
import ch.cern.todo.repository.TaskCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskCategoryService {

    @Autowired
    private TaskCategoryRepository repository;

    public TaskCategory save(TaskCategory category){
        return repository.save(category);
    }

    public TaskCategory findById(Long categoryId){
        return repository.findById(categoryId).orElse(null);
    }

    public List<TaskCategory> findAll(){
        return repository.findAll();
    }

    public void deleteById(Long categoryId){
        repository.deleteById(categoryId);
    }
}