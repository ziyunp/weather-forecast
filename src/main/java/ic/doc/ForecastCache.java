package ic.doc;

import com.weather.Forecast;
import java.util.Map;

public class ForecastCache implements Cache {

  private Map cache;
  private final int cacheCapacity = 10;

  public ForecastCache(Map cache) {

    this.cache = cache;
  }

  @Override
  public void addToCache(Object[] keys, Object forecast) {
    String region = (String) keys[0];
    String day = (String) keys[1];

    if (!cache.containsKey(region)) {
      cache.put(region, new CacheMap(cacheCapacity));
    }
    if (!((CacheMap) cache.get(region)).containsKey(day)) {
      ((CacheMap) cache.get(region)).put(day, forecast);
    }
  }

  @Override
  public Forecast getFromCache(Object[] keys) {
    String region = (String) keys[0];
    String day = (String) keys[1];
    if (!cache.containsKey(region) || !((CacheMap) cache.get(region)).containsKey(day)) {
      return null;
    }
    return (Forecast) ((CacheMap) cache.get(region)).get(day);
  }

  public int getCacheSize() {
    return cache.size();
  }
}
