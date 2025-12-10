import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Task {
    String title;
    LocalDate deadline;
    String priority;   // "high", "medium", "easy"
    Status status;     // IN_PROGRESS, DONE
    List<Task> subtasks;

    public Task(String title, LocalDate deadline, String priority) {
        this.title = title;
        this.deadline = deadline;
        this.priority = priority;
        this.status = Status.IN_PROGRESS;
        this.subtasks = new ArrayList<>();
    }

    public void addSubtask(Task sub) {
        subtasks.add(sub);
    }

    public List<Task> getSubtasks() {
        return subtasks;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public String getPriority() {
        return priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status newStatus) {
        this.status = newStatus;
    }

    @Override
    public String toString() {
        return title + " (" + priority + ") - " + status;
    }
}
