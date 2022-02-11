package model.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateValidator implements DataValidator{
    private String format;
    public DateValidator(String format){
        this.format = format;
    }

    @Override
    public boolean validate(String date) {
        SimpleDateFormat fromRecordString = new SimpleDateFormat(format);
        fromRecordString.setLenient(false);
        try {
            java.util.Date formattedDate = fromRecordString.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
