package model;

public class HeaterMidPower extends HeaterState
{
  @Override public void powerUp(Heater heater)
  {
    heater.setState(new HeaterHighPower(heater));
  }

  @Override public void powerDown(Heater heater)
  {
    heater.setState(new HeaterLowPower());
  }
}
