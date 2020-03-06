package ic.doc;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import com.weather.Forecaster;
import org.junit.Test;

public class ForecasterAdapterTest {
  final Forecaster forecaster = new Forecaster() {};
  ForecasterAdapter adapter = new ForecasterAdapter(forecaster);

  @Test
  public void getForecastWillReturnAForecastObject() {
    assertNotNull(adapter.forecastFor("LONDON", "MONDAY"));
  }

  @Test
  public void getForecastWithLowerCaseStringsWillRaiseException() {
    try {
      adapter.forecastFor("London", "monday");
      fail("Query with lower case strings should raise IllegalArgumentException");
    } catch(IllegalArgumentException e) {}
  }
  @Test
  public void getForecastOfUnavailableRegionWillRaiseException() {
    try {
      adapter.forecastFor("YORK", "MONDAY");
      fail("Region not available in Region.class should raise IllegalArgumentException");
    } catch(IllegalArgumentException e) {}
  }
  @Test
  public void getForecastOfUnavailableDayWillRaiseException() {
    try {
      adapter.forecastFor("LONDON", "TODAY");
      fail("Day not available in Day.class should raise IllegalArgumentException");
    } catch(IllegalArgumentException e) {}
  }
}