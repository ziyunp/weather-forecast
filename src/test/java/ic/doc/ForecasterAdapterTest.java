package ic.doc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import com.weather.Forecast;

import org.junit.Test;

public class ForecasterAdapterTest {

  ForecasterAdapter adapter = new ForecasterAdapter();

  @Test
  public void getForecastWillReturnAForecastObject() {
    Forecast forecast = adapter.getForecast("LONDON", "MONDAY");
    assertNotNull(forecast);
  }
  
  @Test
  public void nonUpperCaseStringsCanQueryForForecast() {

    Forecast forecast = adapter.getForecast("London", "monday");
    assertNotNull(forecast);
  }

  @Test
  public void getForecastOfUnavailableRegionWillRaiseException() {
    try {
      adapter.getForecast("YORK", "MONDAY");
      fail("Region not available in Region.class should raise IllegalArgumentException");
    } catch(IllegalArgumentException e) {}
  }
  @Test
  public void getForecastOfUnavailableDayWillRaiseException() {
    try {
      adapter.getForecast("LONDON", "TODAY");
      fail("Day not available in Day.class should raise IllegalArgumentException");
    } catch(IllegalArgumentException e) {}
  }
}