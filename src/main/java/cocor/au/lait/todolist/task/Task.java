package cocor.au.lait.todolist.task;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.util.UUID;

@Entity
@Data
public class Task {

	@Id
	@GeneratedValue
	private UUID id;

	private String summary;

}
