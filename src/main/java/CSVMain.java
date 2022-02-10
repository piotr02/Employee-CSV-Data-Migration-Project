import controller.CSVController;
import view.CSVView;
import controller.*;

import java.util.Scanner;

public class CSVMain {

    public static void main(String[] args) {

        boolean isRunning = true;


        while (isRunning == true) {

            System.out.println("=== ======================================================= ===");
            System.out.println("=== Available Commands: 'read', 'validate' ================ ===");
            System.out.println("=== ======================================================= ===");


            //logger.info("Looking for User Input");


            CSVView view = new CSVView();
            String csvChoice = view.csvSelector();

            CSVController controller = new CSVController();
            String result = controller.initiateCSV(csvChoice);
            view.displayCSV(result);

//            SortView view = new SortView();
//            String sortChoice = view.sortSelector();

//            SortController controller = new SortController();
//            String result = controller.initiateSort(sortChoice);
//            view.displaySort(result);


            System.out.println("Would you like to run another Method?");
            Scanner scanner = new Scanner(System.in);
            String selectContinue = scanner.next().toLowerCase();

            if(selectContinue.equals("no") || selectContinue.equals("n")){
                isRunning = false;
            } else { isRunning = true; }
        }

    }

}
