package ic.doc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ForecastCacheTest {
  final int cacheCapacity = 3;
  final long cacheExpiry = 10000;
  ForecastCache forecastCache = new ForecastCache(cacheCapacity, cacheExpiry);
  final String[] keys = {"LONDON", "MONDAY"};
  final ForecastInfo testObject = new ForecastInfo(16, "Mock forecast summary");

  @Test
  public void constructorCreatesAnEmptyCache() {
    ForecastCache cache = new ForecastCache(cacheCapacity, cacheExpiry);
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

  @Test
  public void exceedingCacheCapacityWillRemoveEldestEntry() {
    final int cacheCapacity = 2;
    final long cacheExpiry = 100000;
    ForecastCache forecastCache = new ForecastCache(cacheCapacity, cacheExpiry);
    final String[] keys1 = {"WALES", "SUNDAY"};
    final ForecastInfo object1 = new ForecastInfo(10, "Forecast of Wales");
    final String[] keys2 = {"LONDON", "MONDAY"};
    final ForecastInfo object2 = new ForecastInfo(16, "Forecast of London");
    final String[] keys3 = {"GLASGOW", "THURSDAY"};
    final ForecastInfo object3 = new ForecastInfo(16, "Forecast of Glasgow");

    forecastCache.addToCache(keys1, object1);
    forecastCache.addToCache(keys2, object2);

    assertThat(forecastCache.getCacheSize(), is(2));
    assertNotNull(forecastCache.getFromCache(keys1));
    assertNotNull(forecastCache.getFromCache(keys2));

    forecastCache.addToCache(keys3, object3);

    assertThat(forecastCache.getCacheSize(), is(2));
    assertNull(forecastCache.getFromCache(keys1));
    assertNotNull(forecastCache.getFromCache(keys2));
    assertNotNull(forecastCache.getFromCache(keys3));
  }
}
