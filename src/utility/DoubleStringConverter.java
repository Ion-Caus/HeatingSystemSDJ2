package utility;

import javafx.util.StringConverter;

public class DoubleStringConverter extends StringConverter<Number>
{
  @Override public String toString(Number number)
  {
    return number == null ? "" : String.format("%.1f",number);
  }

  @Override public Number fromString(String s)
  {
    try
    {
      return Double.parseDouble(s.replace(",","."));
    }
    catch (Exception e)
    {
      return 0;
    }
  }
}
