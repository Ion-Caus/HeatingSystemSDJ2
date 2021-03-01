package model;
public class Temperature
{
   private String id;
   private double value;

   public Temperature(String id, double value)
   {
      this.id = id;
      this.value = value;
   }
   public double getValue()
   {
      return value;
   }
   public String getId() { return id;}
   public String toString()
   {
      return String.format("%s: %.1f", id, value);
   }
}
