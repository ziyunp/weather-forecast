package ic.doc;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class ForecasterAdapterTest {

  ForecasterAdapter adapter = new ForecasterAdapter();

  @Test
  public void getForecastWillReturnForecastInfo() {
    ForecastInfo forecast = adapter.getForecast("LONDON", "MONDAY");
    assertNotNull(forecast);
  }

  @Test
  public void getTemperatureWillReturnIntegerValue() {
    Integer temperature = adapter.getTemperature("LONDON", "MONDAY");
    assertNotNull(temperature);
  }

  @Test
  public void getSummaryWillReturnStringValue() {
    String summary = adapter.getSummary("LONDON", "MONDAY");
    assertNotNull(summary);
  }

  @Test
  public void nonUpperCaseStringsCanQueryForForecast() {

    ForecastInfo forecast = adapter.getForecast("london", "monday");
    assertNotNull(forecast);
  }

  @Test
  public void nonUpperCaseStringsCanQueryForTemperature() {

    Integer temperature = adapter.getTemperature("lonDon", "mONday");
    assertNotNull(temperature);
  }

  @Test
  public void nonUpperCaseStringsCanQueryForSummary() {

    String summary = adapter.getSummary("LondoN", "moNdAy");
    assertNotNull(summary);
  }

  @Test
  public void returnsNullIfQueriedRegionIsUnavailable() {

    ForecastInfo forecast = adapter.getForecast("YORK", "MONDAY");
    Integer temperature = adapter.getTemperature("brighton", "MONDAY");
    String summary = adapter.getSummary("Bournemouth", "MONDAY");
    assertNull(forecast);
    assertNull(temperature);
    assertNull(summary);
  }

  @Test
  public void returnsNullIfQueriedDayIsUnavailable() {

    ForecastInfo forecast = adapter.getForecast("LONDON", "TODAY");
    Integer temperature = adapter.getTemperature("LONDON", "yesterday");
    String summary = adapter.getSummary("LONDON", "Tomorrow");
    assertNull(forecast);
    assertNull(temperature);
    assertNull(summary);
  }
}
