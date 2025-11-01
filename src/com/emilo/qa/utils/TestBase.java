package com.emilo.qa.utils;

import io.restassured.RestAssured;
import io.qameta.allure.Attachment;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Common Test Base Class — handles:
 *  - Base URI setup
 *  - Unified console + file logging
 *  - Automatic log file creation under test-output/
 *  - Allure report log attachments
 */
public class TestBase {

    private static PrintWriter logWriter;
    private static final String LOG_DIR = "test-output";
    private static final String LOG_FILE = LOG_DIR + File.separator + "execution-log.txt";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    static {
        try {
            // Ensure the log directory exists
            new File(LOG_DIR).mkdirs();

            logWriter = new PrintWriter(new FileWriter(LOG_FILE, true));
            logWriter.println("\n===== Test Execution Started at: " + LocalDateTime.now().format(FORMATTER) + " =====");
            logWriter.flush();
        } catch (IOException e) {
            System.err.println(" Failed to initialize log file: " + e.getMessage());
        }
    }

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://emilo-live-stream-back-1.onrender.com";
        log(" Base URI set to: " + RestAssured.baseURI);
    }

    /**
     * Logs message to console, log file, and Allure report.
     */
    protected void log(String message) {
        String timestamp = LocalDateTime.now().format(FORMATTER);
        String logMessage = timestamp + " - " + message;

        // Console + File
        System.out.println(logMessage);
        if (logWriter != null) {
            logWriter.println(logMessage);
            logWriter.flush();
        }

        // Allure report attachment (visible in report)
        attachLogToAllure(logMessage);
    }

    @Attachment(value = "Log Entry", type = "text/plain")
    private String attachLogToAllure(String message) {
        return message;
    }

    @AfterSuite(alwaysRun = true)
    public void closeLogger() {
        if (logWriter != null) {
            logWriter.println("===== Test Execution Finished at: " + LocalDateTime.now().format(FORMATTER) + " =====");
            logWriter.flush();
            logWriter.close();
        }
        System.out.println(" Logs written to: " + new File(LOG_FILE).getAbsolutePath());
    }
}
