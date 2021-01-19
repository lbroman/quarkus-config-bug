package se.lbroman.quarkus;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

@QuarkusTest
public class SchedulerTest {

    private static final Logger LOG = LoggerFactory.getLogger(SchedulerTest.class);

    @Inject JobScheduler scheduler;

    @BeforeEach
    public void setUp() throws InterruptedException {
        Thread.sleep(5000);
    }

    @Test
    public void testInit() throws InterruptedException {
        Thread.sleep(10000);
        LOG.info("Setting is now {}",scheduler.setting);
    }

}
