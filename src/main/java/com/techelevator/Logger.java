package com.techelevator;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Scanner;

public class Logger {
    private static final String DEFAULT_APP_LOG = "app.log";
    private static final String DEFAULT_TRANS_LOG = "transactionlog.txt";

    private static final DateTimeFormatter LOG_DATE_FORMAT = DateTimeFormatter.ofPattern("MM/dd/uuuu hh:mm:ss a");

    private static final String LOG_ENTRY_FORMAT = "%s: %s";

    private final String appLogFile;
    private final String transLogFile;
    private Map<String, Integer> totalSalesLog;
    private final String INVENTORY_LIST = "vendingmachine.csv";

    public Map<String, Integer> getTotalSalesLog() {
        return totalSalesLog;
    }

    public Logger() {
        this.appLogFile = DEFAULT_APP_LOG;
        this.transLogFile = DEFAULT_TRANS_LOG;
    }

    public void logTransaction(String trans) throws FileNotFoundException {

        try(PrintWriter lw = new PrintWriter(new FileOutputStream(new File(transLogFile), true))){
            int sellingCounter=0;
            ItemCounter ic = new ItemCounter();

            try {
                File dataFile = new File(INVENTORY_LIST);
                try (Scanner fileScanner = new Scanner(dataFile)) {
                    while (fileScanner.hasNext()) {
                        String[] data = fileScanner.nextLine().split("\\|");
                        String slotNumber = data[0];
                        Item newItem = new Item(data[1]);
                        totalSalesLog.put(newItem.getName(), sellingCounter);

                        }

                    }
                }catch (FileNotFoundException fnf) {
                System.out.println(fnf.getCause());//TODO pass in an error message / throw?

            }



            lw.println(totalSalesLog);

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
