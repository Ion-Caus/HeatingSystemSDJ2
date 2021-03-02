package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.*;
import model.Temperature;
import model.TemperatureModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MainWindowViewModel implements PropertyChangeListener
{
  private StringProperty heaterState, warningProperty;
  private DoubleProperty temperatureInsideClosest, temperatureInsideFurthest, temperatureOutside, minTemperature, maxTemperature;
  private TemperatureModel model;

  public MainWindowViewModel(TemperatureModel model)
  {
    this.model = model;
    temperatureInsideClosest = new SimpleDoubleProperty(
        model.getLastInsertedTemperature("closest").getValue());
    temperatureInsideFurthest = new SimpleDoubleProperty(
        model.getLastInsertedTemperature("furthest").getValue());
    temperatureOutside = new SimpleDoubleProperty(
        model.getLastInsertedTemperature("outside").getValue());
    minTemperature = new SimpleDoubleProperty();
    maxTemperature = new SimpleDoubleProperty();
    heaterState = new SimpleStringProperty(model.getHeater().getState());
    warningProperty = new SimpleStringProperty();
    this.model.addListener(null, this);
  }

  public void clear(){
    warningProperty.set("");
  }

  private void checkCriticalTemperature(double t1)
  {
    if (t1 > maxTemperature.get())
    {
      warningProperty.set("Temperature too high!");
    }
    else if (t1 < minTemperature.get())
      warningProperty.set("Temperature too low!");
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    switch (evt.getPropertyName())
    {
      case "outside":
        Platform.runLater(() -> {
          Temperature temp = (Temperature) evt.getNewValue();
          temperatureOutside.set(temp.getValue());
        });
        break;
      case "closest":
        Platform.runLater(() -> {
          Temperature temp = (Temperature) evt.getNewValue();
          temperatureInsideClosest.set(temp.getValue());
          checkCriticalTemperature(temperatureInsideClosest.get());
        });
        break;
      case "furthest":
        Platform.runLater(() -> {
          Temperature temp = (Temperature) evt.getNewValue();
          temperatureInsideFurthest.set(temp.getValue());
          checkCriticalTemperature(temperatureInsideFurthest.get());
        });
        break;
      case "state":
        Platform.runLater(() -> heaterState.set((String) evt.getNewValue()));
        break;
    }
  }

  public DoubleProperty getTemperatureInsideClosestProperty()
  {
    return temperatureInsideClosest;
  }

  public DoubleProperty getTemperatureInsideFurthestProperty()
  {
    return temperatureInsideFurthest;
  }

  public DoubleProperty getTemperatureOutsideProperty()
  {
    return temperatureOutside;
  }

  public DoubleProperty getMinTemperature(){
    return minTemperature;
  }

  public DoubleProperty getMaxTemperature(){
    return maxTemperature;
  }

  public StringProperty getHeaterStateProperty()
  {
    return heaterState;
  }

  public StringProperty getWarningProperty()
  {
    return warningProperty;
  }

  public void powerUp(){
    model.powerUp();
  }

  public void powerDown(){
    model.powerDown();
  }
}
