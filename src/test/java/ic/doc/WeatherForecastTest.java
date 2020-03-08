package ic.doc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

/** This is a system test */
public class WeatherForecastTest {
  final WeatherForecast forecaster = new WeatherForecast();

  @Test
  public void canGetForecastByRegionAndDayWithStringsOfAnyLetterCase() {

    int londonTemp = forecaster.getTemperature("London", "monDAy");
    assertNotNull(londonTemp);

    String edinburghSummary = forecaster.getSummary("edinburgh", "sunday");
    assertNotNull(edinburghSummary);

    String walesSummary = forecaster.getSummary("WALES", "SATURDAY");
    assertNotNull(walesSummary);
  }

  @Test
  public void returnsNullWhenGetForecastByUnavailableRegion() {

    Integer brightonTemp = forecaster.getTemperature("Brighton", "Saturday");
    assertNull(brightonTemp);

    String yorkSummary = forecaster.getSummary("York", "Monday");
    assertNull(yorkSummary);
  }

  @Test
  public void returnsNullWhenGetForecastByUnavailableDay() {

    Integer londonTemp = forecaster.getTemperature("London", "Today");
    assertNull(londonTemp);

    String edinburghSummary = forecaster.getSummary("edinburgh", "Yesterday");
    assertNull(edinburghSummary);
  }

  @Test
  public void twoIdenticalConsecutiveQueriesShouldReturnSameForecast() {

    Integer walesTemp1 = forecaster.getTemperature("Wales", "Tuesday");
    String walesSummary1 = forecaster.getSummary("wales", "tuesday");

    Integer walesTemp2 = forecaster.getTemperature("Wales", "Tuesday");
    String walesSummary2 = forecaster.getSummary("WALES", "TUESDAY");

    assertEquals(walesTemp1, walesTemp2);
    assertEquals(walesSummary1, walesSummary2);
  }
}
