package viewmodel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Temperature;

public class TemperatureViewModel
{
  private StringProperty nameProperty, valueProperty, timeProperty;

  public TemperatureViewModel(Temperature temperature){
    this.nameProperty = new SimpleStringProperty(temperature.getId());
    this.valueProperty = new SimpleStringProperty(String.format("%.1f",temperature.getValue()));
    this.timeProperty = new SimpleStringProperty(temperature.getTime());
  }

  public StringProperty getNameProperty(){
    return nameProperty;
  }

  public StringProperty getValueProperty(){
    return valueProperty;
  }

  public StringProperty getTimeProperty() {
    return timeProperty;
  }
}
