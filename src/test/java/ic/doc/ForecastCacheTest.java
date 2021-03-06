package ic.doc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ForecastCacheTest {
  final int cacheCapacity = 5;
  final long hundredSecondsExpiry = 100000;
  ForecastCache forecastCache = new ForecastCache(cacheCapacity, hundredSecondsExpiry);
  final String[] keys = {"LONDON", "MONDAY"};
  final ForecastInfo testObject = new ForecastInfo(16, "Mock forecast summary");

  @Test
  public void constructorCreatesAnEmptyCache() {
    ForecastCache cache = new ForecastCache(cacheCapacity, hundredSecondsExpiry);
    assertThat(cache.getCacheSize(), is(0));
  }

  @Test
  public void addToCacheIncreasesCacheSizeByOne() {
    final String[] keys1 = {"WALES", "SUNDAY"};
    final ForecastInfo object1 = new ForecastInfo(10, "Forecast of Wales");
    final String[] keys2 = {"LONDON", "MONDAY"};
    final ForecastInfo object2 = new ForecastInfo(16, "Forecast of London");
    final String[] keys3 = {"GLASGOW", "THURSDAY"};
    final ForecastInfo object3 = new ForecastInfo(16, "Forecast of Glasgow");

    forecastCache.addToCache(keys1, object1);
    assertThat(forecastCache.getCacheSize(), is(1));

    forecastCache.addToCache(keys2, object2);
    assertThat(forecastCache.getCacheSize(), is(2));

    forecastCache.addToCache(keys3, object3);
    assertThat(forecastCache.getCacheSize(), is(3));
  }

  @Test
  public void getFromCacheReturnsTheCorrectForecast() {
    forecastCache.addToCache(keys, testObject);
    assertEquals(forecastCache.getFromCache(keys), testObject);
  }

  @Test
  public void removeCacheItemDecreasesCacheSizeByOne() {
    forecastCache.addToCache(keys, testObject);

    assertThat(forecastCache.getCacheSize(), is(1));
    forecastCache.removeCacheItem(keys);
    assertThat(forecastCache.getCacheSize(), is(0));
  }

  @Test
  public void removeCacheItemRemovesTheCorrectItem() {
    final String[] keys1 = {"WALES", "SUNDAY"};
    final ForecastInfo object1 = new ForecastInfo(10, "Forecast of Wales");
    final String[] keys2 = {"LONDON", "MONDAY"};
    final ForecastInfo object2 = new ForecastInfo(16, "Forecast of London");

    forecastCache.addToCache(keys1, object1);
    forecastCache.addToCache(keys2, object2);

    assertNotNull(forecastCache.getFromCache(keys1));
    assertNotNull(forecastCache.getFromCache(keys2));

    forecastCache.removeCacheItem(keys2);
    assertNotNull(forecastCache.getFromCache(keys1));
    assertNull(forecastCache.getFromCache(keys2));
  }

  @Test
  public void addingTwoIdenticalKeyValuePairsWillNotOverrideTheFirstEntry() {
    final ForecastInfo firstForecast = new ForecastInfo(10, "First forecast summary");
    final ForecastInfo secondForecast = new ForecastInfo(16, "Second forecast summary");
    forecastCache.addToCache(keys, firstForecast);
    forecastCache.addToCache(keys, secondForecast);
    assertThat(forecastCache.getFromCache(keys), is(not(secondForecast)));
    assertEquals(forecastCache.getFromCache(keys), firstForecast);
  }

  @Test
  public void addItemsMoreThanCacheCapacityWillNotIncreaseCacheSize() {
    final int smallCacheCapacity = 2;
    ForecastCache forecastCache = new ForecastCache(smallCacheCapacity, hundredSecondsExpiry);

    final String[] keys1 = {"WALES", "SUNDAY"};
    final ForecastInfo object1 = new ForecastInfo(10, "Forecast of Wales");
    final String[] keys2 = {"LONDON", "MONDAY"};
    final ForecastInfo object2 = new ForecastInfo(16, "Forecast of London");
    final String[] keys3 = {"GLASGOW", "THURSDAY"};
    final ForecastInfo object3 = new ForecastInfo(16, "Forecast of Glasgow");

    forecastCache.addToCache(keys1, object1);
    forecastCache.addToCache(keys2, object2);

    assertThat(forecastCache.getCacheSize(), is(smallCacheCapacity));

    forecastCache.addToCache(keys3, object3);

    assertThat(forecastCache.getCacheSize(), is(smallCacheCapacity));
  }

  @Test
  public void exceedingCacheCapacityWillRemoveEldestEntry() {
    final int smallCacheCapacity = 2;
    ForecastCache forecastCache = new ForecastCache(smallCacheCapacity, hundredSecondsExpiry);

    final String[] keys1 = {"WALES", "SUNDAY"};
    final ForecastInfo object1 = new ForecastInfo(10, "Forecast of Wales");
    final String[] keys2 = {"LONDON", "MONDAY"};
    final ForecastInfo object2 = new ForecastInfo(16, "Forecast of London");
    final String[] keys3 = {"GLASGOW", "THURSDAY"};
    final ForecastInfo object3 = new ForecastInfo(16, "Forecast of Glasgow");

    forecastCache.addToCache(keys1, object1);
    forecastCache.addToCache(keys2, object2);
    forecastCache.addToCache(keys3, object3);

    assertNull(forecastCache.getFromCache(keys1));
    assertNotNull(forecastCache.getFromCache(keys2));
    assertNotNull(forecastCache.getFromCache(keys3));
  }

  @Test
  public void expiredCacheItemWillBeRemoved() {
    final long threeSecondsExpiry = 3000;
    ForecastCache forecastCache = new ForecastCache(cacheCapacity, threeSecondsExpiry);
    forecastCache.addToCache(keys, testObject);
    assertNotNull(forecastCache.getFromCache(keys));
    try {
      System.out.println("Sleep for 5 seconds...");
      Thread.sleep(5000);
    } catch (InterruptedException e) {
    }
    assertNull(forecastCache.getFromCache(keys));
  }

  @Test
  public void zeroCacheExpiryWillNotRemoveCacheItem() {
    final long zeroExpiry = 0;
    ForecastCache forecastCache = new ForecastCache(cacheCapacity, zeroExpiry);
    forecastCache.addToCache(keys, testObject);
    assertNotNull(forecastCache.getFromCache(keys));
    try {
      System.out.println("Sleep for 2 seconds...");
      Thread.sleep(2000);
    } catch (InterruptedException e) {
    }
    assertNotNull(forecastCache.getFromCache(keys));
  }

  @Test
  public void negativeCacheExpiryWillNotRemoveCacheItem() {
    final long negativeExpiry = -1;
    ForecastCache forecastCache = new ForecastCache(cacheCapacity, negativeExpiry);
    forecastCache.addToCache(keys, testObject);
    assertNotNull(forecastCache.getFromCache(keys));
    try {
      System.out.println("Sleep for 2 seconds...");
      Thread.sleep(2000);
    } catch (InterruptedException e) {
    }
    assertNotNull(forecastCache.getFromCache(keys));
  }

  @Test
  public void clearCacheSetsCacheSizeToZero() {
    final String[] keys1 = {"WALES", "SUNDAY"};
    final ForecastInfo object1 = new ForecastInfo(10, "Forecast of Wales");
    final String[] keys2 = {"LONDON", "MONDAY"};
    final ForecastInfo object2 = new ForecastInfo(16, "Forecast of London");

    forecastCache.addToCache(keys1, object1);
    forecastCache.addToCache(keys2, object2);

    assertThat(forecastCache.getCacheSize(), is(2));

    forecastCache.clearCache();

    assertThat(forecastCache.getCacheSize(), is(0));
  }

  @Test
  public void clearCacheRemovesAllEntries() {
    final String[] keys1 = {"WALES", "SUNDAY"};
    final ForecastInfo object1 = new ForecastInfo(10, "Forecast of Wales");
    final String[] keys2 = {"LONDON", "MONDAY"};
    final ForecastInfo object2 = new ForecastInfo(16, "Forecast of London");

    forecastCache.addToCache(keys1, object1);
    forecastCache.addToCache(keys2, object2);

    assertNotNull(forecastCache.getFromCache(keys1));
    assertNotNull(forecastCache.getFromCache(keys2));

    forecastCache.clearCache();

    assertNull(forecastCache.getFromCache(keys1));
    assertNull(forecastCache.getFromCache(keys2));
  }
}
