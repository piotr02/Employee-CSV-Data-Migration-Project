package model.validator;

public class EmailValidator implements DataValidator{
    @Override
    public boolean validate(String email) {
        if(email == null) return false;
        //The regex for an email
        return email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }
}
