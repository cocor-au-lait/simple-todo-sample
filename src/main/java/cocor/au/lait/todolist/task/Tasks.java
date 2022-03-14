package cocor.au.lait.todolist.task;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface Tasks extends JpaRepository<Task, UUID> {
}
