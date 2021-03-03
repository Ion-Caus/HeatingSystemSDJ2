package model;
public class Temperature
{
   private String id;
   private double value;
   private DateTime time;

   public Temperature(String id, double value)
   {
      this.id = id;
      this.value = value;
      time = new DateTime();
   }
   public double getValue()
   {
      return value;
   }
   public String getId() { return id;}

   public String getTime() {
      return time.getTime();
   }

   public String toString()
   {
      return String.format("%s: %.1f", id, value);
   }
}
