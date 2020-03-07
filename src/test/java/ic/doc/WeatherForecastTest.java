package ic.doc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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
  public void returnsNullWhenGetForecastByUnavailableRegion() {
    Forecast forecast = forecaster.getForecastFor("York", "Monday");
    assertNull(forecast);
  }

  @Test
  public void returnsNullWhenGetForecastByUnavailableDay() {
    Forecast forecast = forecaster.getForecastFor("London", "Today");
    assertNull(forecast);
  }

  @Test
  public void twoIdenticalConsecutiveQueriesShouldReturnSameForecast() {
    Forecast walesForecast1 = forecaster.getForecastFor("Wales", "Tuesday");
    Forecast walesForecast2 = forecaster.getForecastFor("Wales", "Tuesday");
    assertEquals(walesForecast1, walesForecast2);
  }
}
