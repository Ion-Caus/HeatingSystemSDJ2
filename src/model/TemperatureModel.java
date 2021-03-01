package model;

import utility.observer.NamedPropertyChangeSubject;

public interface TemperatureModel extends NamedPropertyChangeSubject
{
  void addTemperature(String id,double externalTemperature, double temperature);

  void fireStateProperty(int state);

  Temperature getLastInsertedTemperature();

  Temperature getLastInsertedTemperature(String id);
}
