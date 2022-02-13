package controller;

import model.CSVTool;
import model.CSVToolFactory;
import model.DataValidatorFactory;
import model.ReaderFactory;

public class CSVController {


    public String initiateCSV(String desired) {
        CSVTool c = getTool(desired);
        return c.validate();
    }

    public CSVTool getTool(String toolType){


        CSVToolFactory tf = switch (toolType.toLowerCase()){

            case "read" ->
                    new ReaderFactory();

            case "validate" ->
                    new DataValidatorFactory();


            default ->  { System.out.println("Unsupported request method: " + toolType.toLowerCase());
                // yield instead of return
                yield null;}
        };

        return tf.getInstance();


    }




}
