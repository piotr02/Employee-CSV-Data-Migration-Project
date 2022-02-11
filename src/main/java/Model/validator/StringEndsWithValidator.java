package model.validator;

public class StringEndsWithValidator implements DataValidator{
    String endsWith;
    public StringEndsWithValidator(String endsWith){
        this.endsWith = endsWith;
    }

    @Override
    public boolean validate(String prefix) {
        if(prefix == null) return  false;
        return prefix.endsWith(this.endsWith);
    }
}
