package se.lbroman.quarkus;

import io.quarkus.arc.Arc;
import io.quarkus.runtime.Startup;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.quartz.*;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

@ApplicationScoped
@Startup
public class JobScheduler {

    private static final Logger LOG = LoggerFactory.getLogger(JobScheduler.class);

    @Inject
    Scheduler quartz;

    @ConfigProperty(name = "example.setting")
    boolean setting;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @PostConstruct
    public void init() {
        LOG.info("Starting");
        LOG.info("Setting is: {}",setting);
        scheduleNextJob(GregorianCalendar.getInstance());
        Calendar cal = GregorianCalendar.getInstance();
        cal.add(Calendar.SECOND,1);
        scheduleNextJob(cal);
        LOG.info("Setting is: {}",setting);
    }

    private void doUpdate() {
        LOG.debug("Updating");
        LOG.debug("Setting is: {}",setting);
        LOG.debug("Done");
    }


    void scheduleNextJob(Calendar cal) {
        LOG.debug("Schedule for update {}", sdf.format(cal.getTime()));
        JobDetail job = JobBuilder.newJob(UpdateJob.class).build();
        Trigger trigger = TriggerBuilder.newTrigger().startAt(cal.getTime())
                .build();
        try {
            quartz.scheduleJob(job,trigger);
        } catch (SchedulerException e) {
            LOG.error("Failed to schedule new job",e);
        }

    }

    public static class UpdateJob implements Job {

        @Inject
        JobScheduler jobScheduler;

        @Override
        public void execute(JobExecutionContext arg0) throws JobExecutionException {
            LOG.debug("Execute Job");
            Arc.container().instance(JobScheduler.class).get().doUpdate();
            jobScheduler.doUpdate();
        }

    }
}
