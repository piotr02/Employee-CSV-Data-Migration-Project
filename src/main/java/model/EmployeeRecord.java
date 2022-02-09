package model;

import java.time.LocalDateTime;
import java.util.Date;

public class EmployeeRecord {

    public int employer_ID;
    public String firstName , lastName;
    public String email;
    public Date date_of_Birth;
    public LocalDateTime ldt;

    @Override
    public String toString() {
        return "EmployeeRecord{" +
                "employer_ID=" + employer_ID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", date_of_Birth=" + date_of_Birth +
                ", ldt=" + ldt +
                '}';
    }
}
