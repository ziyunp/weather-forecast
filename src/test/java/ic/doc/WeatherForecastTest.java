package ic.doc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import com.weather.Forecast;
import org.junit.Test;

public class WeatherForecastTest {
  final WeatherForecast forecaster = new WeatherForecast();

  @Test
  public void canGetForecastByRegionAndDayWithStringsOfAnyLetterCase() {

    Forecast londonForecast = forecaster.getForecastFor("London", "monDAy");
    assertNotNull(londonForecast);
    assertNotNull(londonForecast.summary());
    assertNotNull(londonForecast.temperature());

    Forecast edinburghForecast = forecaster.getForecastFor("edinburgh", "SUNDAY");
    assertNotNull(edinburghForecast);
    assertNotNull(edinburghForecast.summary());
    assertNotNull(edinburghForecast.temperature());
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
    } catch (IllegalArgumentException e) {

    }
  }

  @Test
  public void twoIdenticalConsecutiveQueriesShouldReturnSameForecast() {
    Forecast walesForecast1 = forecaster.getForecastFor("Wales", "Tuesday");
    Forecast walesForecast2 = forecaster.getForecastFor("Wales", "Tuesday");
    assertEquals(walesForecast1, walesForecast2);
  }
}
