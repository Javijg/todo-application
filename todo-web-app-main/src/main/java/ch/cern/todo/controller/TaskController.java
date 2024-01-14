package ch.cern.todo.controller;

import ch.cern.todo.model.Task;
import ch.cern.todo.repository.TaskRespository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/task")
public class TaskController {
    @Autowired
    private TaskRespository taskRespository;

    private Log log = LogFactory.getLog(getClass());

    @PostMapping
    public ResponseEntity<Task> saveTask(@RequestBody Task task) {

        try {
            Task savedEntity = taskRespository.save(task);
            return new ResponseEntity<>(savedEntity, HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            log.error("Save request violates data integrity for task category id " + task.getId() + ", " + e);
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            log.error("There was an error while saving task category id " + task.getId() + ", " + e);
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path="/{taskId}")
    public ResponseEntity<Task> getTask(@PathVariable Long taskId) {

        Task task = taskRespository.findById(taskId).orElse(null);
        if(task == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Task>> getTasks() {

        List<Task> tasks = taskRespository.findAll();
        if(tasks == null || tasks.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @DeleteMapping(path="/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {

        Task taskCategory = taskRespository.findById(taskId).orElse(null);
        if(taskCategory == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            taskRespository.deleteById(taskId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            log.error("Could not delete task category with id " + taskCategory.getId() + ", " + e);
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
}
