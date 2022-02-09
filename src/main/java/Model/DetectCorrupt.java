package Model;

import java.util.Arrays;

public class DetectCorrupt {

    public static void main(String[] args) {

        System.out.println(Arrays.deepToString(
                corruptReader("EmployeeRecords.csv")
        ));}



    public static String[][] corruptReader(String filename) {
        String[][] corruptArray = new String[0][];
        CSVReader fileReader = new CSVReader();
        String[][] fileArray = fileReader.readCsvFile(filename);

//        for(int i = 0; i< fileArray.length; i++){
//            // If ID isn't numeric, add to Corrupt Array file.
//            if(fileArray[i][0].contains("[a-zA-Z]+")){
//                corruptArray[i][] = fileArray[i][];
//            }
//
//        }

        // if ID includes anything but numbers -> Corrupt
        // if name prefix not in enum?
        // If Middle initial not 1 character long
        // If Gender not in M, F (or O?)
        // If date of birth/date of join don't adhere to date format
        // If Salary includes anything but numbers

        System.out.println("========================");
        System.out.println(fileArray[1][2]);



        return corruptArray;
    }


}
