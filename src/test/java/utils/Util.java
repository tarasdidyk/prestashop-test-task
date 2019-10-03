package utils;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import common.BaseTest;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

public class Util extends BaseTest {

    public static long PAGE_LOAD_TIMEOUT = 20;
    public static long IMPLICIT_WAIT = 20;

    public static void delay(int minutes, int seconds) throws IllegalArgumentException {
        if (minutes < 0 || seconds < 0) {
            throw new IllegalArgumentException("Arguments cannot be negative!");
        }
        LocalDateTime timeLimit = LocalDateTime.now().plusMinutes(minutes);
        timeLimit = timeLimit.plusSeconds(seconds);
        while (LocalDateTime.now().isBefore(timeLimit)) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }
        }
    }

    public static void reloadPage() {
        driver.navigate().refresh();
    }

}