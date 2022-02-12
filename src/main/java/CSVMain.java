import controller.CSVController;
import view.CSVView;
import controller.*;

import java.util.Scanner;

public class CSVMain {

    public static void main(String[] args) {

        boolean isRunning = true;


        while (isRunning) {

            System.out.println("=== ======================================================= ===");
            System.out.println("=== Available Commands: 'read', 'validate' ================ ===");
            System.out.println("=== ======================================================= ===");

            CSVView view = new CSVView();
            String csvChoice = view.csvSelector();

            CSVController controller = new CSVController();
            String result = controller.initiateCSV(csvChoice);
            view.displayCSV(result);

            System.out.println("Would you like to run another Method?");
            Scanner scanner = new Scanner(System.in);
            String selectContinue = scanner.next().toLowerCase();

            if(selectContinue.equals("no") || selectContinue.equals("n")){
                isRunning = false;
            }
        }


    }

}
