package cryst.taskManagement.dto;

import cryst.taskManagement.entity.Task;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.PriorityQueue;

@Service
public class TaskScheduler {

    private PriorityQueue<Task> taskQueue;

    public TaskScheduler() {
        taskQueue = new PriorityQueue<>(Comparator
                .comparing(Task::getPriority)
                .thenComparing(Task::getCreatedAt)
        );
    }

    public void addTask(Task task) {
        taskQueue.add(task);
    }

    public Task getNextTask() {
        return taskQueue.poll();
    }
}