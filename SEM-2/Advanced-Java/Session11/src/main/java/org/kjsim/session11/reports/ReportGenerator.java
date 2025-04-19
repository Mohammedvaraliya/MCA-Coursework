package org.kjsim.session11.reports;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ReportGenerator {

    @Scheduled(cron = "0 */5 * * * ?")
    public void generateReport() {
        System.out.println("Generating report at " + new Date().toString());
    }
}