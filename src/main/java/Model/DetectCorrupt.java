package Model;

import java.util.ArrayList;
import java.util.Arrays;

public class DetectCorrupt {

    public static void main(String[] args) {

        System.out.println(Arrays.deepToString(
                new ArrayList[]{corruptReader("EmployeeRecords.csv")}
        ));}



    public static ArrayList corruptReader(String filename) {
        ArrayList corruptArray = new ArrayList();
        CSVReader fileReader = new CSVReader();
        String[][] fileArray = fileReader.readCsvFile(filename);

        for(int i = 0; i< fileArray.length; i++){
            // If ID isn't numeric, add to Corrupt Array file.
            if(fileArray[i][0].matches("[a-zA-Z]+")){
                corruptArray.add(fileArray[i]);
            }


      //   If Middle initial not 1 character long

            if(!(fileArray[i][3].length() == 1)){
                corruptArray.add(fileArray[i]);

            }


      //   If Gender not in M, F

            if(!fileArray[i][5].matches(("^M$|^F$"))){
                corruptArray.add(fileArray[i]);
            }


      //   If Salary includes anything but numbers

            if(fileArray[i][9].matches("[a-zA-Z]+")){
                corruptArray.add(fileArray[i]);
            }
        }

        // if name prefix not in enum?
        // If date of birth/date of join don't adhere to date format


        System.out.println("========================");

        return corruptArray;
    }


}
