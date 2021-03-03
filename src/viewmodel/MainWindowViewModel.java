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
  private BooleanProperty turnedOff, maxedOut;
  private DoubleProperty temperatureInsideClosest, temperatureInsideFurthest, temperatureOutside, minTemperature, maxTemperature;
  private TemperatureModel model;

  public MainWindowViewModel(TemperatureModel model)
  {
    this.model = model;
    temperatureInsideClosest = new SimpleDoubleProperty();
    temperatureInsideFurthest = new SimpleDoubleProperty();
    temperatureOutside = new SimpleDoubleProperty();
    minTemperature = new SimpleDoubleProperty(-20);
    maxTemperature = new SimpleDoubleProperty(20);
    turnedOff = new SimpleBooleanProperty(true);
    maxedOut = new SimpleBooleanProperty(false);
    heaterState = new SimpleStringProperty(model.getHeater().getState());
    warningProperty = new SimpleStringProperty();
    this.model.addListener(null, this);
  }

  public void clear()
  {
    warningProperty.set("");
  }

  private synchronized void checkCriticalTemperature(double t1, double t2)
  {
    if (t1 < maxTemperature.get() && t2 < maxTemperature.get() &&
        t1 > minTemperature.get() && t2 > minTemperature.get())
      clear();

    if (t1 > maxTemperature.get() || t2 > maxTemperature.get())
    {
      warningProperty.set("Temperature too high!");
    }
    else if (t1 < minTemperature.get() || t2 < minTemperature.get())
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
          checkCriticalTemperature(temperatureInsideClosest.get(),
              temperatureInsideFurthest.get());
        });
        break;
      case "furthest":
        Platform.runLater(() -> {
          Temperature temp = (Temperature) evt.getNewValue();
          temperatureInsideFurthest.set(temp.getValue());
          checkCriticalTemperature(temperatureInsideClosest.get(),
              temperatureInsideFurthest.get());
        });
        break;
      case "state":
        Platform.runLater(() -> {
          String state = (String)evt.getNewValue();
          turnedOff.set("HeaterOff".equals(state));
          maxedOut.set("HeaterHighPower".equals(state));
          heaterState.set(state);
        });
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

  public DoubleProperty getMinTemperature()
  {
    return minTemperature;
  }

  public DoubleProperty getMaxTemperature()
  {
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

  public BooleanProperty getTurnedOff(){
    return turnedOff;
  }

  public BooleanProperty getMaxedOut(){
    return maxedOut;
  }

  public void powerUp()
  {
    model.powerUp();
  }

  public void powerDown()
  {
    model.powerDown();
  }
}
