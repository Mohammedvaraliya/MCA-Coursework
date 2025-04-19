package org.kjsim.session11.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SystemStatusChecker {

    @Scheduled(fixedRate = 10000) // every 10 seconds
    public void checkSystemStatus() {
        long freeMemory = Runtime.getRuntime().freeMemory() / (1024 * 1024); // MB
        long totalMemory = Runtime.getRuntime().totalMemory() / (1024 * 1024); // MB

        System.out.println("System Status Check:");
        System.out.println("Free Memory: " + freeMemory + " MB");
        System.out.println("Total Memory: " + totalMemory + " MB");
        System.out.println("--------------------------");
    }
}