package ToDoList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Task {
    String title;
    LocalDate deadline;
    String priority;
    Status status;
    int progress;
    List<Task> subtasks;

    public Task(String title, LocalDate deadline, String priority, Status status) {
        this.title = title;
        this.deadline = deadline;
        this.priority = priority;
        this.status = status;
        this.subtasks = new ArrayList<>();
        updateProgress();
    }

    public void addSubtask(Task sub) {
        subtasks.add(sub);
        updateProgress();
    }

    public void updateProgress() {
        if (subtasks.isEmpty()) {
            progress = switch (status) {
                case DONE -> 100;
                case IN_PROGRESS -> 50;
                default -> 0;
            };
        } else {
            int done = 0;
            for (Task t : subtasks) if (t.status == Status.DONE) done++;
            progress = (int) ((done * 100.0) / subtasks.size());
            if (progress == 100) status = Status.DONE;
            else if (progress > 0) status = Status.IN_PROGRESS;
            else status = Status.PENDING;
        }
    }
}
