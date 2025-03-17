package cryst.taskManagement.service;

import cryst.taskManagement.dto.TaskScheduler;
import cryst.taskManagement.entity.Task;
import cryst.taskManagement.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@CacheConfig(cacheNames = "tasks")
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskScheduler taskScheduler;

    @Cacheable
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    @CacheEvict(allEntries = true)
    public Task createTask(Task task) {
        task.setCreatedAt(LocalDateTime.now());
        taskScheduler.addTask(task);
        return taskRepository.save(task);
    }

    @CacheEvict(allEntries = true)
    public Task updateTask(Long id, Task taskDetails) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setStatus(taskDetails.getStatus());
        task.setPriority(taskDetails.getPriority());
        return taskRepository.save(task);
    }

    @CacheEvict(allEntries = true)
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}