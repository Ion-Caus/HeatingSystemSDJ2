package external;

public abstract class HeaterState
{

  public void powerUp(Heater heater){}

  public void powerDown(Heater heater){}

  public synchronized void timeout(Heater heater)
  {
  }
}
