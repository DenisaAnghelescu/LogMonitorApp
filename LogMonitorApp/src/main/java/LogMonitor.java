import java.io.*;
import java.time.*;
import java.util.*;
import java.nio.file.*;

public class LogMonitor {

    public static void main(String[] args) throws IOException {
        Map<String, JobDuration> jobs = new HashMap<>();

        List<String> lines = Files.readAllLines(Path.of("src/main/resources/logFile/logs.log"));


        for (String line : lines) {
            String[] parts = line.split(",");
            LogEntry entry = new LogEntry(parts[0], parts[1], parts[2], parts[3]);

            JobDuration job = jobs.getOrDefault(entry.jobId, new JobDuration());
            boolean isStart = entry.status.equalsIgnoreCase("START");
            job.update(LocalTime.from(entry.timestamp), isStart);
            jobs.put(entry.jobId, job);
        }

        for (Map.Entry<String, JobDuration> e : jobs.entrySet()) {
            String jobId = e.getKey();
            JobDuration jd = e.getValue();
            Duration duration = jd.getDuration();

            if (duration.toMinutes() > 10) {
                System.err.println("ERROR: Job " + jobId + " took " + duration.toMinutes() + " mins.");
            } else if (duration.toMinutes() > 5) {
                System.out.println("WARNING: Job " + jobId + " took " + duration.toMinutes() + " mins.");
            } else {
                System.out.println("OK: Job " + jobId + " took " + duration.toMinutes() + " mins.");
            }
        }

        //test for task 051, PID 39547
        JobDuration job = new JobDuration();
        job.update(LocalTime.parse("11:37:53"), true);  // START
        job.update(LocalTime.parse("11:49:22"), false); // END

        Duration duration = job.getDuration();
        System.out.println("Minutes: " + duration.toMinutes());
        System.out.println("Full duration: " + duration);

    }
}
