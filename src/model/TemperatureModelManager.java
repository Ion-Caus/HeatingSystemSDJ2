package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TemperatureModelManager implements TemperatureModel
{
  private TemperatureList temperatureList;
  private PropertyChangeSupport property;

  public TemperatureModelManager()
  {
    temperatureList = new TemperatureList();
    this.property = new PropertyChangeSupport(this);
  }

  @Override public synchronized void addTemperature(String id, double value)
  {
    Temperature temperature = new Temperature(id, value);
    Temperature old = getLastInsertedTemperature(id);
    this.temperatureList.addTemperature(temperature);
//    if (old != null && old.getValue() != temperature.getValue())
//    {
//      System.out.println("--> new=" + temperature + " (old=" + old + ")");
//    }
//    else if (old == null)
//    {
//      System.out.println("--> new=" + temperature + " (old=" + old + ")");
//    }
    property.firePropertyChange(id,null,temperature);
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

  // and maybe other methods...
}
