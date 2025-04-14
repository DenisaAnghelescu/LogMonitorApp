
import java.time.LocalTime;

public class LogEntry {
    LocalTime timestamp;
    String jobId;
    String task;
    String status;

    public LogEntry(String timestamp, String jobId, String task, String status) {
        this.timestamp = LocalTime.parse(timestamp);
        this.jobId = jobId;
        this.task = task;
        this.status = status;
    }
}
