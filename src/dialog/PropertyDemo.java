package dialog;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class PropertyDemo {
    public static void main(String[] args) {
        ObservableList<Integer> observableList = FXCollections.observableArrayList(1, 2);
        observableList.addListener(new ListChangeListener<Integer>() {
            @Override
            public void onChanged(Change<? extends Integer> c) {
                System.out.format("Chelsea-Juventus: %d-%d%n", c.getList().get(0), c.getList().get(1));
            }
        });
        observableList.set(1, 2);
    }
}
