package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class CSVReader implements CSVTool{


    public static String[][] readCsvFile(String filename) {
    String[][] stringArray  = new String[0][];
      try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
          StringBuilder sb = new StringBuilder();
          String line;
          while ((line = br.readLine()) != null){
              sb.append(line);
              sb.append("\n");
          }
          String[] recordString = sb.toString().split("\n");
          stringArray = new String[recordString.length][];
          for(int i = 0; i < recordString.length; i++){
              stringArray[i] = recordString[i].split(",");
          }

      } catch (IOException e) {
          e.printStackTrace();
      }
        System.out.println("Reading done");
      return stringArray;
  }

    @Override
    public String validate() {
        return "";
    }
}
