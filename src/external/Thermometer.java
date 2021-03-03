package external;

import model.Heater;
import model.TemperatureModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Thermometer implements Runnable, PropertyChangeListener
{
  private double internalTemperameture;
  private double externalTemperature;
  private int heaterPowerState;
  private String id;
  private int distanceFromHeater;
  private boolean running;
  private Thread runningThread;
  private TemperatureModel model;
  private Heater heater;
  private boolean isExternal;

  public Thermometer(String id, double internalTemperameture,
      int distanceFromHeater,boolean isExternal, TemperatureModel model)
  {
    this.id = id;
    this.internalTemperameture = internalTemperameture;
    this.distanceFromHeater = distanceFromHeater;
    this.isExternal = isExternal;
    this.heaterPowerState = 0;     // heaters power {0, 1, 2 or 3}
    this.externalTemperature = 0.0;  // starting outdoor temperature
    this.model = model;
    model.addListener("state",this);
    model.addTemperature("outside",externalTemperature); //adding starting outdoor temperature into the model for other threads to work with
  }

  @Override public void run()
  {
    running = true;
    runningThread = Thread.currentThread();
    while (running)
    {
      try
      {
        int seconds = (isExternal)? 10:(int)(Math.random() * 4 + 4);
        Thread.sleep(seconds * 1000);
        externalTemperature = model.getLastInsertedTemperature("outside").getValue(); //fetching latest external temperature for internal thermometers
        if(isExternal){
          externalTemperature = externalTemperature(externalTemperature, -20, 20);
          model.addTemperature(id,externalTemperature);
        }else{
          internalTemperameture = temperature(internalTemperameture,
              heaterPowerState, distanceFromHeater, externalTemperature, seconds);
          model.addTemperature(id,internalTemperameture);
        }
      }
      catch (InterruptedException e)
      {
        //
      }
    }
  }

  public void stop()
  {
    running = false;
    if (runningThread != null)
    {
      runningThread.interrupt();
    }
  }

  /**
   * Calculating the temperature measured in one of two locations.
   * This includes a term from a heater (depending on location and
   * heaters power), and a term from an outdoor heat loss.
   * Values are only valid in the outdoor temperature range [-20; 20]
   * and when s, the number of seconds between each measurements are
   * between 4 and 8 seconds.
   *
   * @param internalTemperameture the last measured temperature
   * @param heaterPowerState      the heaters power {0, 1, 2 or 3} where 0 is turned off,
   *                              1 is low, 2 is medium and 3 is high
   * @param distanceFromHeater    the distance between heater and measurements {1 or 7}
   *                              where 1 is close to the heater and 7 is in the opposite corner
   * @param externalTemperature   the outdoor temperature (valid in the range [-20; 20])
   * @param s                     the number of seconds since last measurement [4; 8]
   * @return the temperature
   */

  private double temperature(double internalTemperameture, int heaterPowerState,
      int distanceFromHeater, double externalTemperature, int s)
  {
    double tMax = Math.min(11 * heaterPowerState + 10,
        11 * heaterPowerState + 10 + externalTemperature);
    tMax = Math.max(Math.max(internalTemperameture, tMax), externalTemperature);
    double heaterTerm = 0;
    if (heaterPowerState > 0)
    {
      double den = Math
          .max((tMax * (20 - 5 * heaterPowerState) * (distanceFromHeater + 5)),
              0.1);
      heaterTerm = 30 * s * Math.abs(tMax - internalTemperameture) / den;
    }
    double outdoorTerm =
        (internalTemperameture - externalTemperature) * s / 250.0;
    internalTemperameture = Math.min(
        Math.max(internalTemperameture - outdoorTerm + heaterTerm,
            externalTemperature), tMax);
    return internalTemperameture;
  }

  /**
   * Calculating the external temperature.
   * Values are only valid if the temperature is being measured
   * approximately every 10th second.
   *
   * @param externalTemperature the last measured external temperature
   * @param min                 a lower limit (may temporally be deceeded)
   * @param max                 an upper limit (may temporally be exceeded)
   * @return an updated external temperature
   */

  public double externalTemperature(double externalTemperature, double min,
      double max)
  {
    double left = externalTemperature - min;
    double right = max - externalTemperature;
    int sign = Math.random() * (left + right) > left ? 1 : -1;
    externalTemperature += sign * Math.random();
    System.out.printf("External temperature: %s \n", externalTemperature);
    return externalTemperature;
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    switch ((String) evt.getNewValue())
    {
      case "HeaterOff":
        this.heaterPowerState = 0;
        break;
      case "HeaterLowPower":
        this.heaterPowerState = 1;
        break;
      case "HeaterMidPower":
        this.heaterPowerState = 2;
        break;
      case "HeaterHighPower":
        this.heaterPowerState = 3;
        break;
    }
  }
}
