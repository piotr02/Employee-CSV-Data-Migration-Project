import controller.CSVController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.CSVView;

import java.util.Scanner;


public class CSVMain {
    public static Logger csvlogger = LogManager.getLogger("Sort Logger");
    public static void main(String[] args) {

        boolean isRunning = true;


        while (isRunning) {

            System.out.println("=== ======================================================= ===");
            System.out.println("=== Available Commands: 'read', 'validate', 'write' ======= ===");
            System.out.println("=== ======================================================= ===");
            csvlogger.info("Looking for User Input");
            CSVView view = new CSVView();
            String csvChoice = view.csvSelector();
            csvlogger.info("Attempting to perform a feature.");
            CSVController controller = new CSVController();
            String result = controller.initiateCSV(csvChoice);
            view.displayCSV(result);
            csvlogger.info("Method Complete.");
            System.out.println("Would you like to run another Method?");
            Scanner scanner = new Scanner(System.in);
            String selectContinue = scanner.next().toLowerCase();

            if(selectContinue.equals("no") || selectContinue.equals("n")){
                csvlogger.info("Program Aborted");
                isRunning = false;
            }
        }


    }

}
