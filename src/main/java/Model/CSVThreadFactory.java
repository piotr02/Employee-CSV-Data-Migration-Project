//package model;
//
//import java.util.ArrayList;
//
//public class CSVThreadFactory {
//    private ArrayList<CSVThread> threadList = new ArrayList<>();
//
//    /**
//     * Creates a list of threads based on desired number of threads.
//     * @param numberOfThreads desired number of threads
//     * @param employeeList list of employees
//     */
//    public void createThreads(int numberOfThreads, ArrayList<Employee> employeeList){
//        for(int i = 0; i < numberOfThreads; i++){
//            this.threadList.add(new CSVThread("thread " + (i+1), employeeList));
//        }
//    }
//
//    /**
//     * Starts all threads and then joins them
//     * @param threadList list of threads
//     */
//    public void executeThreads(ArrayList<CSVThread> threadList){
//        CSVThread[] threads = threadList.toArray(new CSVThread[0]);
//        for(int i = 0; i < threads.length; i++){
//            threads[i].start();
//        }
//        for(int i = 0; i < threads.length; i++){
//            try {
//                threads[i].join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    /**
//     * Runs the threads
//     */
//    public void run(){
//        executeThreads(threadList);
//    }
//
//    //!!!!!!!!!!
//    // TEMPORARY, TO DELETE LATER
//    //!!!!!!!!!!
//    public static void main(String[] args) {
//        CSVThreadFactory threadFactory = new CSVThreadFactory();
//
//        String[][] csvData = CSVReader.readCsvFile("TestEmployeeRecords.csv");
//        EmployeeCsvDataValidatorNew dataValidator = new EmployeeCsvDataValidatorNew(csvData);
//        ArrayList<String[]> sqlReadyRecords = new EmployeeDateConversion(dataValidator.getUniqueCleanRecords()).toSqlReadyRecords();
//        ArrayList<Employee> employeeRecords = new RecordsToEmployee(sqlReadyRecords).getEmployeeArrayListFunctional();
//
//        EmployeeDB.createDatabase();
//        threadFactory.createThreads(2, employeeRecords);
//        double start = System.currentTimeMillis();
//        threadFactory.run();
//        double end = System.currentTimeMillis();
//
//        System.out.println("Process took: " + ((end - start)/1000) + " seconds.");
//    }
//}
