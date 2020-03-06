package ic.doc;

import static org.junit.Assert.*;

import com.weather.Forecast;
import org.junit.Test;

public class WeatherForecastTest {
  final WeatherForecast forecaster = new WeatherForecast();
  @Test
  public void canGetForecastByRegionAndDayWithStringsOfAnyLetterCase() {

    Forecast londonForecast = forecaster.getForecastFor("London", "Monday");
    System.out.println("London outlook: " + londonForecast.summary());
    System.out.println("London temperature: " + londonForecast.temperature());

    Forecast edinburghForecast = forecaster.getForecastFor("edinburgh", "SUNDAY");

    System.out.println("Edinburgh outlook: " + edinburghForecast.summary());
    System.out.println("Edinburgh temperature: " + edinburghForecast.temperature());

  }
  @Test
  public void exceptionRaisedWhenGetForecastByUnavailableRegion() {
    try {
      forecaster.getForecastFor("York", "Monday");
      fail("Region not available in Region.class should raise IllegalArgumentException");
    } catch (IllegalArgumentException e) {

    }
  }
  @Test
  public void exceptionRaisedWhenGetForecastByUnavailableDay() {
    try {
      forecaster.getForecastFor("London", "Today");
      fail("Day not available in Day.class should raise IllegalArgumentException");
    } catch(IllegalArgumentException e) {

    }
  }
}