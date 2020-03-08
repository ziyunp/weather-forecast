package ic.doc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ForecastCacheTest {
  ForecastCache forecastCache = new ForecastCache();
  final String[] keys = {"LONDON", "MONDAY"};
  final ForecastInfo testObject = new ForecastInfo(16, "Mock forecast summary");

  @Test
  public void constructorCreatesAnEmptyCache() {
    ForecastCache cache = new ForecastCache();
    assertThat(cache.getCacheSize(), is(0));
  }

  @Test
  public void addToCacheIncreasesCacheSizeByOne() {
    assertThat(forecastCache.getCacheSize(), is(0));
    forecastCache.addToCache(keys, testObject);
    assertThat(forecastCache.getCacheSize(), is(1));
  }

  @Test
  public void getFromCacheReturnsTheCorrectForecast() {
    forecastCache.addToCache(keys, testObject);
    assertEquals(forecastCache.getFromCache(keys), testObject);
  }

  @Test
  public void addingTwoIdenticalKeyValuePairsWillNotOverrideTheFirstEntry() {
    final String[] keys = {"LONDON", "MONDAY"};
    final ForecastInfo firstForecast = new ForecastInfo(10, "First forecast summary");
    final ForecastInfo secondForecast = new ForecastInfo(16, "Second forecast summary");
    forecastCache.addToCache(keys, firstForecast);
    forecastCache.addToCache(keys, secondForecast);
    assertThat(forecastCache.getFromCache(keys), is(not(secondForecast)));
    assertEquals(forecastCache.getFromCache(keys), firstForecast);
  }
}
