# quarkus-config-bug

Test by running
 
    mvn test
 
    2021-01-19 21:40:24,895 INFO  [se.lbr.qua.JobScheduler] (main) Setting is: true
    2021-01-19 21:40:24,896 DEBUG [se.lbr.qua.JobScheduler] (main) Schedule for update 2021-01-19 21:40:24
    2021-01-19 21:40:24,910 DEBUG [se.lbr.qua.JobScheduler] (main) Schedule for update 2021-01-19 21:40:25
    2021-01-19 21:40:24,911 INFO  [se.lbr.qua.JobScheduler] (main) Setting is: true
    2021-01-19 21:40:24,913 DEBUG [se.lbr.qua.JobScheduler] (QuarkusQuartzScheduler_Worker-1) Execute Job
    2021-01-19 21:40:24,914 DEBUG [se.lbr.qua.JobScheduler] (QuarkusQuartzScheduler_Worker-1) Updating
    2021-01-19 21:40:24,915 DEBUG [se.lbr.qua.JobScheduler] (QuarkusQuartzScheduler_Worker-1) Setting is: false
