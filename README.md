# LogMonitorApp
This application was built to monitor logs in a file. The goal is to read a .csv file "logs.log", masures how long each job takes from start to finish and calculate the duration of each job.

Log Structure:
- HH:MM:SS is a timestamp in hours, minutes and seconds
- job description
- each log entry is either the "START" or "END" of a process
- each job has a "PID" associated with it, e.g. 46578
