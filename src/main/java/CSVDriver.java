import Model.EmployeeCsvDataValidator;

import java.util.ArrayList;
import java.util.Arrays;

import static Model.CSVReader.readCsvFile;

public class CSVDriver {


    public static void main(String[] args) {

        System.out.println("");
        System.out.println("============= Read CSV ======================");
        System.out.println("");


        System.out.println(Arrays.deepToString(
                readCsvFile("EmployeeRecords.csv")));

        System.out.println("");
        System.out.println("============ Seperate Corrupt ===============");
        System.out.println("");

        EmployeeCsvDataValidator dataValidator = new EmployeeCsvDataValidator();
        dataValidator.setData(readCsvFile("EmployeeRecords.csv"));
        dataValidator.spilitData();
        ArrayList<String[]> corruptedData =  dataValidator.getCorrupted();
        ArrayList<String[]> validData =  dataValidator.getValid();

        corruptedData.forEach(array ->{
            System.out.println(Arrays.toString(array));
        });

    }


//    public static boolean isEmployeeRowCorrupt(String[] row, HashSet<String> existingIds){
//
//        if(row.length != 10) return true;
//
//
//        String id = row[0];
//        String middleInitial = row[3];
//        String gender = row[5];
//        String salary = row[9];
//
//        // If ID isn't numeric, add to Corrupt Array file.
//        if(id.matches("[a-zA-Z]+")){
//             return true;
//        }
//        if(existingIds.contains(id)){
//            return true;
//        }
//
//        //   If Middle initial not 1 character long
//        if(!(middleInitial.length() == 1)){
//             return true;
//        }
//        //   If Gender not in M, F
//        if(!gender.matches(("^M$|^F$"))){
//            return true;
//        }
//        //   If Salary includes anything but numbers
//        if(salary.matches("[a-zA-Z]+")){
//            return true;
//        }
//
//        // if name prefix not in enum?
//
//        // If date of birth/date of join don't adhere to date format
//
//        return false;
//    }
//
//


//    public static ArrayList corruptReader(String filename) {
//        ArrayList corruptArray = new ArrayList();
//        CSVReader fileReader = new CSVReader();
//        String[][] fileArray = fileReader.readCsvFile(filename);
//        HashSet<String> idSet = new HashSet<>();
//        for(int i = 0; i< fileArray.length; i++){
//            boolean corrupted = false;
//            if(fileArray[i].length != 10){
//                corrupted = true;
//            }
//
//            if(!corrupted){
//
//                String id = fileArray[i][0];
//                String middleInitial = fileArray[i][3];
//                String gender = fileArray[i][5];
//                String salary = fileArray[i][9];
//
//                // If ID isn't numeric, add to Corrupt Array file.
//                if(id.matches("[a-zA-Z]+")){
//                    corrupted = true;
//                }else {
//                    //If ID Already Exists add to corrupted
//                    if(idSet.contains(id)){
//                        corrupted = true;
//                    }
//                    idSet.add(id);
//                }
//
//                //   If Middle initial not 1 character long
//                if(!(middleInitial.length() == 1)){
//                    corrupted = true;
//                }
//                //   If Gender not in M, F
//                if(!gender.matches(("^M$|^F$"))){
//                    corrupted = true;
//                }
//                //   If Salary includes anything but numbers
//                if(salary.matches("[a-zA-Z]+")){
//                    corrupted = true;
//                }
//
//            }
//            if(corrupted){
//                corruptArray.add(fileArray[i]);
//            }
//
//        }
//
//        // if name prefix not in enum?
//        // If date of birth/date of join don't adhere to date format
//
//        System.out.println("========================");
//
//        return corruptArray;
//    }


}