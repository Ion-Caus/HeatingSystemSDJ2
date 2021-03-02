package utility;

import javafx.util.StringConverter;

public class DoubleStringConverter extends StringConverter<Number>
{
  @Override public String toString(Number number)
  {
    return number == null || number.doubleValue() == 0 ? "" : number.toString();
  }

  @Override public Number fromString(String s)
  {
    try
    {
      return Double.parseDouble(s);
    }
    catch (Exception e)
    {
      return 0;
    }
  }
}
