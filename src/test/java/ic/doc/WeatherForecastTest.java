package ic.doc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class WeatherForecastTest {
  final WeatherForecast forecaster = new WeatherForecast();

  @Test
  public void canGetForecastByRegionAndDayWithStringsOfAnyLetterCase() {

    ForecastInfo londonForecast = forecaster.getForecastFor("London", "monDAy");
    assertNotNull(londonForecast);
    assertNotNull(londonForecast.summary());
    assertNotNull(londonForecast.temperature());

    ForecastInfo edinburghForecast = forecaster.getForecastFor("edinburgh", "SUNDAY");
    assertNotNull(edinburghForecast);
    assertNotNull(edinburghForecast.summary());
    assertNotNull(edinburghForecast.temperature());
  }

  @Test
  public void returnsNullWhenGetForecastByUnavailableRegion() {
    ForecastInfo forecast = forecaster.getForecastFor("York", "Monday");
    assertNull(forecast);
  }

  @Test
  public void returnsNullWhenGetForecastByUnavailableDay() {
    ForecastInfo forecast = forecaster.getForecastFor("London", "Today");
    assertNull(forecast);
  }

  @Test
  public void twoIdenticalConsecutiveQueriesShouldReturnSameForecast() {
    ForecastInfo walesForecast1 = forecaster.getForecastFor("Wales", "Tuesday");
    ForecastInfo walesForecast2 = forecaster.getForecastFor("Wales", "Tuesday");
    assertEquals(walesForecast1, walesForecast2);
  }
}
