package model;

import utility.observer.NamedPropertyChangeSubject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Heater implements NamedPropertyChangeSubject
{
  private HeaterState heaterState;
  private PropertyChangeSupport property;

  public Heater()
  {
    this.heaterState = new HeaterOff();
    this.property = new PropertyChangeSupport(this);
  }

  public void powerUp()
  {
    this.heaterState.powerUp(this);
  }

  public void powerDown(){
    this.heaterState.powerDown(this);
  }

  public void setState(HeaterState state){
    this.heaterState = state;
  }

  public String getState(){
    var stateName = heaterState.getClass().getSimpleName();
    return stateName;
  }

  @Override public void addListener(String propertyName,
      PropertyChangeListener listener)
  {
    property.addPropertyChangeListener(propertyName,listener);
  }

  @Override public void removeListener(String propertyName,
      PropertyChangeListener listener)
  {
    property.removePropertyChangeListener(propertyName,listener);
  }
}