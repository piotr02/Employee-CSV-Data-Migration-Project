package model.validator;

public class StringLengthValidator implements DataValidator{
    private int maxLength;
    public StringLengthValidator(int maxLength){
        this.maxLength = maxLength;
    }

    @Override
    public boolean validate(String str) {
        if (str == null) return false;
        return str.length() < maxLength;
    }
}
