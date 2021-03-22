package dialog;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class BindingDemo {
    public static void main(String[] args) {
        IntegerProperty aProp = new SimpleIntegerProperty(10);
        IntegerProperty bProp = new SimpleIntegerProperty(25);
        aProp.bind(bProp);
        bProp.set(1234);
        System.out.println(aProp.get());
        System.out.println(bProp.get());
        aProp.unbind();
        aProp.set(12);
        System.out.println(aProp.get());
        System.out.println(bProp.get());


    }
}
