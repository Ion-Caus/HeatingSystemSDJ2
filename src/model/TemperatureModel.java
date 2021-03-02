package model;

import utility.observer.NamedPropertyChangeSubject;

public interface TemperatureModel extends NamedPropertyChangeSubject
{
  void addTemperature(String id,double externalTemperature, double temperature);

  void fireHeaterStateProperty(int state);

  Temperature getLastInsertedTemperature();

  Temperature getLastInsertedTemperature(String id);

  void powerUp();

  void powerDown();

  Heater getHeater();
}
