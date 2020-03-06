package ic.doc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertEquals;

import com.weather.Forecast;
import java.util.HashMap;
import org.junit.Test;

public class ForecastCacheTest {
  ForecastCache forecastCache = new ForecastCache(new HashMap());

  @Test
  public void constructorCreatesAnEmptyCache() {
    ForecastCache cache = new ForecastCache(new HashMap());
    assertThat(cache.getCacheSize(), is(0));
  }

  @Test
  public void addToCacheIncreasesCacheSizeByOne() {
    assertThat(forecastCache.getCacheSize(), is(0));
    final String[] keys = {"LONDON", "MONDAY"};
    final Forecast forecast = new Forecast("Mock summary", 25) {};

    forecastCache.addToCache(keys, forecast);
    assertThat(forecastCache.getCacheSize(), is(1));
  }

  @Test
  public void getFromCacheReturnsTheCorrectForecast() {
    final String[] keys = {"LONDON", "SUNDAY"};
    final Forecast forecast = new Forecast("Mock summary", 15) {};
    forecastCache.addToCache(keys, forecast);
    assertEquals(forecastCache.getFromCache(keys), forecast);
  }

  @Test
  public void addingTwoIdenticalKeyValuePairsWillNotOverrideTheFirstEntry() {
    final String[] keys = {"LONDON", "MONDAY"};
    final Forecast firstForecast = new Forecast("First summary", 25) {};
    final Forecast secondForecast = new Forecast("Second summary", 12) {};
    forecastCache.addToCache(keys, firstForecast);
    forecastCache.addToCache(keys, secondForecast);
    assertThat(forecastCache.getFromCache(keys), is(not(secondForecast)));
    assertEquals(forecastCache.getFromCache(keys), firstForecast);
  }
}
