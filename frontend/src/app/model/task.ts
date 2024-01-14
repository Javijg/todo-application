import { TaskCategory } from "./task-category";

export interface Task {
    id?: number;
    name: string;
    description?: string;
    deadline?: string;
    category: TaskCategory;
}