package com.techelevator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static final String DEFAULT_APP_LOG = "app.log";
    private static final String DEFAULT_TRANS_LOG = "transactionlog.txt";

    private static final DateTimeFormatter LOG_DATE_FORMAT = DateTimeFormatter.ofPattern("MM/dd/uuuu hh:mm:ss a");

    private static final String LOG_ENTRY_FORMAT = "%s: %s";

    private final String appLogFile;
    private final String transLogFile;

    public Logger() {
        this.appLogFile = DEFAULT_APP_LOG;
        this.transLogFile = DEFAULT_TRANS_LOG;
    }

    public void logTransaction(String trans){

        try(PrintWriter lw = new PrintWriter(new FileOutputStream(new File(transLogFile), true))){

            lw.println(String.format(LOG_ENTRY_FORMAT, timeStamp(), trans));

        }catch(IOException iox){
            //ALLIGATOR CATCH: NOT A GOOD PRACTICE! FOR DEMO ONLY
        }

    }

    public void logEvent(String event){

        try(PrintWriter lw = new PrintWriter(new FileOutputStream(new File(appLogFile), true))){

            lw.println(String.format(LOG_ENTRY_FORMAT, timeStamp(), event));

        }catch(IOException iox){
            //ALLIGATOR CATCH: NOT A GOOD PRACTICE! FOR DEMO ONLY
        }

    }

    private String timeStamp(){

        //TODO: REFACTOR/SIMPLIFY TIME STAMP STRING CREATION
        String ts = "";

        LocalDateTime dateTimeNow = LocalDateTime.now();
        ts = dateTimeNow.format(LOG_DATE_FORMAT);

        return ts;
    }

}
