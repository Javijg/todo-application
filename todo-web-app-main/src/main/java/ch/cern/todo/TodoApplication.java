package ch.cern.todo;

import ch.cern.todo.model.TaskCategory;
import ch.cern.todo.service.TaskCategoryService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TodoApplication {

	@Autowired
	private TaskCategoryService taskCategoryService;

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(TodoApplication.class, args);
		Object datasource = context.getBean("dataSource");
		System.out.println(datasource);
	}

	@Bean
	InitializingBean initData() {
		return () -> {
			taskCategoryService.save(new TaskCategory(null, "Everything else", "Everything else category"));
		};
	}
}
