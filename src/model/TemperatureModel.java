package model;

import utility.observer.NamedPropertyChangeSubject;

import java.beans.PropertyChangeListener;

public interface TemperatureModel extends NamedPropertyChangeSubject,
    PropertyChangeListener
{
  void addTemperature(String id, double temperature);

  Temperature getLastInsertedTemperature();

  Temperature getLastInsertedTemperature(String id);

  void powerUp();

  void powerDown();

  Heater getHeater();
}
