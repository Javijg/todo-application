package ch.cern.todo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "task_categories")
public class TaskCategory {
    @Id
    @SequenceGenerator(
            name = "seq_category",
            sequenceName = "seq_category",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_category"
    )
    private Long id;

    @Column(length = 100, nullable = false, unique = true)
    private String name;

    @Column(length = 500)
    private String description;
}
