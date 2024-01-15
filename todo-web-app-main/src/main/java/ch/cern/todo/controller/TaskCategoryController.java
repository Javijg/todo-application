package ch.cern.todo.controller;

import ch.cern.todo.model.TaskCategory;
import ch.cern.todo.service.TaskCategoryService;
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

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/task-category")
public class TaskCategoryController {

    @Autowired
    private TaskCategoryService taskCategoryService;

    private Log log = LogFactory.getLog(getClass());

    @PostMapping
    public ResponseEntity<TaskCategory> saveCategory(@RequestBody TaskCategory taskCategory) {

        try {
            TaskCategory savedCategory = taskCategoryService.save(taskCategory);
            return new ResponseEntity<>(savedCategory, HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            log.error("Save request violates data integrity for task category id " + taskCategory.getId() + ", " + e);
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            log.error("There was an error while saving task category id " + taskCategory.getId() + ", " + e);
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path="/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId) {

        TaskCategory taskCategory = taskCategoryService.findById(categoryId);
        if(taskCategory == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            taskCategoryService.deleteById(categoryId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            log.error("Could not delete task category with id " + taskCategory.getId() + ", " + e);
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path="/{categoryId}")
    public ResponseEntity<TaskCategory> getCategory(@PathVariable Long categoryId) {

        TaskCategory taskCategory = taskCategoryService.findById(categoryId);
        if(taskCategory == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(taskCategory, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TaskCategory>> getCategories() {

        List<TaskCategory> taskCategories = taskCategoryService.findAll();
        if(taskCategories == null || taskCategories.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(taskCategories, HttpStatus.OK);
    }
}