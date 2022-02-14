package model.validator;

public class IntegerValidator implements  DataValidator{

    @Override
    public boolean validate(String integerString) {
        try {
            Integer.parseInt(integerString);
            return true;
        }catch (final NumberFormatException e){
            return false;
        }
    }
}
