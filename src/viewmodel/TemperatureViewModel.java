package viewmodel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Temperature;

public class TemperatureViewModel
{
  private StringProperty nameProperty,valueProperty;

  public TemperatureViewModel(Temperature temperature){
    this.nameProperty = new SimpleStringProperty(temperature.getId());
    this.valueProperty = new SimpleStringProperty(String.format("%.1f",temperature.getValue()));
  }

  public StringProperty getNameProperty(){
    return nameProperty;
  }

  public StringProperty getValueProperty(){
    return valueProperty;
  }

}
