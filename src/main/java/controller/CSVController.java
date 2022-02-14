package controller;

import model.*;

public class CSVController {

    public String initiateCSV(String desired) {
        CSVTool c = getTool(desired);
        return c.validate();
    }

    public CSVTool getTool(String toolType){

        CSVToolFactory tf = switch (toolType.toLowerCase()){

            case "read" ->
                    new CSVReaderFactory();

            case "validate" ->
                    new DataValidatorFactory();

            case "write" ->
                    new CSVWriterFactory();

            default ->  { System.out.println("Unsupported request method: " + toolType.toLowerCase());
                // yield instead of return
                yield null;}
        };
        return tf.getInstance();
    }
}
