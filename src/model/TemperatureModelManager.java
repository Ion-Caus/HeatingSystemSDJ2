package model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TemperatureModelManager implements TemperatureModel
{
  private TemperatureList temperatureList;
  private PropertyChangeSupport property;
  private Heater heater;

  public TemperatureModelManager()
  {
    temperatureList = new TemperatureList();
    this.property = new PropertyChangeSupport(this);
    this.heater = new Heater();
    heater.addListener("state",this);
  }

  @Override public synchronized void addTemperature(String id,double externalTemperature, double internalTemperature)
  {
    var inside = new Temperature(id, internalTemperature);
    var outside = new Temperature("outside",externalTemperature);
    this.temperatureList.addTemperature(inside);
    property.firePropertyChange("outside",null,outside);
    property.firePropertyChange(id,null,inside);

  }


  @Override public synchronized Temperature getLastInsertedTemperature()
  {
    return temperatureList.getLastTemperature(null);
  }

  @Override public synchronized Temperature getLastInsertedTemperature(
      String id)
  {
    return temperatureList.getLastTemperature(id);
  }

  @Override public void powerUp()
  {
    heater.powerUp();
  }

  @Override public void powerDown()
  {
    heater.powerDown();
  }

  @Override public Heater getHeater()
  {
    return this.heater;
  }

  @Override public void addListener(String propertyName,
      PropertyChangeListener listener)
  {
      if(propertyName==null)
      {
        this.property.addPropertyChangeListener(listener);
      }else property.addPropertyChangeListener(propertyName,listener);
  }

  @Override public void removeListener(String propertyName,
      PropertyChangeListener listener)
  {
    if(propertyName==null)
    {
      this.property.removePropertyChangeListener(listener);
    }else property.removePropertyChangeListener(propertyName,listener);
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    property.firePropertyChange("state",null,evt.getNewValue());
  }

  // and maybe other methods...
}
