import java.time.Duration;
import java.time.LocalTime;

class JobDuration {
    LocalTime start;
    LocalTime end;

    public void update(LocalTime time, boolean isStart) {
        if (isStart) {
            if (start == null || time.isBefore(start)) start = time;
        } else {
            if (end == null || time.isAfter(end)) end = time;
        }
    }

    public Duration getDuration() {
        if (start != null && end != null) {
            return Duration.between(start, end);
        }
        else  {
            return Duration.ZERO;
        }
    }
}