package ic.doc;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import com.weather.Forecast;

import org.junit.Test;

public class ForecasterAdapterTest {

  ForecasterAdapter adapter = new ForecasterAdapter();

  @Test
  public void getForecastWillReturnAForecastObject() {
    ForecastInfo forecast = adapter.getForecast("LONDON", "MONDAY");
    assertNotNull(forecast);
  }

  @Test
  public void nonUpperCaseStringsCanQueryForForecast() {

    ForecastInfo forecast = adapter.getForecast("London", "monday");
    assertNotNull(forecast);
  }

  @Test
  public void returnsNullIfQueriedRegionIsUnavailable() {

    ForecastInfo forecast = adapter.getForecast("YORK", "MONDAY");
    assertNull(forecast);
  }

  @Test
  public void returnsNullIfQueriedDayIsUnavailable() {

    ForecastInfo forecast = adapter.getForecast("LONDON", "TODAY");
    assertNull(forecast);
  }
}
