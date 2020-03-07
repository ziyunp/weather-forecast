package ic.doc;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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
  public void returnsNullIfQueriedRegionIsUnavailable() {

    Forecast forecast = adapter.getForecast("YORK", "MONDAY");
    assertNull(forecast);
  }

  @Test
  public void returnsNullIfQueriedDayIsUnavailable() {

    Forecast forecast = adapter.getForecast("LONDON", "TODAY");
    assertNull(forecast);
  }
}
