package viewmodel;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Temperature;
import model.TemperatureModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class MainWindowViewModel implements PropertyChangeListener {
    private StringProperty temperature, thermometerid;
    private IntegerProperty heaterState;
    private TemperatureModel model;

    public MainWindowViewModel(TemperatureModel model){
        this.model = model;
        temperature = new SimpleStringProperty();
        thermometerid = new SimpleStringProperty();
        heaterState= new SimpleIntegerProperty();
        this.model.addListener(null,this);

    }



    public void getValue(){
        Temperature t = model.getLastInsertedTemperature(thermometerid.get());
        if (t != null)
        {
            temperature.set(t.toString());
        }
        else
        {
            temperature.set("No data");
        }
    }

    private void setValue(Temperature t){
        temperature.set(t.toString());
    }

    public StringProperty getTemperature(){
        return temperature;
    }

    public StringProperty getThermometerid(){
        return thermometerid;
    }


    public IntegerProperty heaterStateProperty() {
        return heaterState;
    }

    /*public void updateThermometerId(){
        model.removeListener(thermometerid.get(),this);
        String input = filtervalue.get();
        if (input == null || input.isEmpty() || input.equalsIgnoreCase("All"))
        {
            input = "All";
        }
        thermometerid.set(input);
        if (thermometerid.get() == null || thermometerid.get().equalsIgnoreCase("ALL"))
        {
            model.addListener(null,this);
            filtervalue.set("All");
        }
        else
        {
            model.addListener(thermometerid.get(),this);
            filtervalue.set(thermometerid.get());
        }
        filtervalue.set(null);
        getValue();
    }*/

    @Override public void propertyChange(PropertyChangeEvent evt)
    {
        //Platform.runLater(()->temperature.set(evt.getNewValue().toString()));
    }


}
