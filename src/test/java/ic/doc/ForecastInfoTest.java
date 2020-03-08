package ic.doc;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class ForecastInfoTest {
  final Integer testTemp = 26;
  final String testSummary = "Sunny";
  ForecastInfo forecastInfo = new ForecastInfo(testTemp, testSummary);
  
  @Test
  public void canGetStoredTemperatureValue() {
    Integer temperature = forecastInfo.temperature();
    assertThat(temperature, is(testTemp));
  }

  @Test
  public void canGetStoredSummaryString() {
    String summary = forecastInfo.summary();
    assertEquals(summary, testSummary);
  }
}