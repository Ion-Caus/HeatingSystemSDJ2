package model;

public class HeaterLowPower extends HeaterState
{
  @Override public void powerUp(Heater heater)
  {
    heater.setState(new HeaterMidPower());
  }

  @Override public void powerDown(Heater heater)
  {
    heater.setState(new HeaterOff());
  }
}
