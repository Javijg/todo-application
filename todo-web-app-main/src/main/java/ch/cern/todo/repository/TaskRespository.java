package ch.cern.todo.repository;

import ch.cern.todo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskRespository extends JpaRepository<Task, Long> {
}
