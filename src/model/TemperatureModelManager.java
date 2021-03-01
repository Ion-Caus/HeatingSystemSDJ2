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

  @Override public synchronized void addTemperature(String id,double externalTemperature, double internalTemperature)
  {
    var inside = new Temperature(id, internalTemperature);
    var outside = new Temperature("outside",externalTemperature);
    this.temperatureList.addTemperature(inside);
    property.firePropertyChange("outside",null,outside);
    property.firePropertyChange(id,null,inside);


  }

  @Override
  public void fireHeaterStateProperty(int state) {
    property.firePropertyChange("stateChange", null, state);
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
