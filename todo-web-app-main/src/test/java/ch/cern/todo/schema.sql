CREATE TABLE t_tasks (
  id numeric auto_increment,
  name varchar(100) not null,
  description varchar(500) not null,
  deadline timestamp,
  category_id numeric
);

CREATE TABLE t_task_categories (
  id numeric auto_increment,
  name varchar(100) not null,
  description varchar(500)
);

ALTER TABLE t_tasks ADD PRIMARY KEY (id);
