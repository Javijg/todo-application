import { Task } from "../model/task";
import { mockedCategories } from "./mock-category";

export const mockedTask: Task = {
      id: 1,
      name: 'Task1',
      description: "Desc",
      deadline: null,
      category: mockedCategories[0]
};
  