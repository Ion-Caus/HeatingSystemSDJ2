package model;

public class HeaterHighPower extends HeaterState
{
  private Thread wait;

  public HeaterHighPower(Heater heater){
    timeout(heater);
  }

  @Override public void powerDown(Heater heater)
  {
    wait.interrupt();
    heater.setState(new HeaterMidPower());
  }

  @Override public synchronized void timeout(Heater heater){
    wait = new Thread( () -> {
      try
      {
        Thread.sleep(40000);
        System.out.println("powering down");
        heater.setState(new HeaterMidPower());
      }
      catch (InterruptedException ignored)
      {
      }
    });
    wait.start();
  }
}
