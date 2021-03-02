package model;

import utility.observer.NamedPropertyChangeSubject;

public interface TemperatureModel extends NamedPropertyChangeSubject
{
  void addTemperature(String id,double externalTemperature, double temperature);

  Temperature getLastInsertedTemperature();

  Temperature getLastInsertedTemperature(String id);

  void powerUp();

  void powerDown();

  Heater getHeater();
}
