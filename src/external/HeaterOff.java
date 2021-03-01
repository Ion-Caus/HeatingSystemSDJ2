package external;

public class HeaterOff extends HeaterState
{

  @Override public void powerUp(Heater heater)
  {
    heater.setState(new HeaterLowPower());
  }

}
