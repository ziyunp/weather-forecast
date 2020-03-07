package ic.doc;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * This class is a custom cache to store forecast queries. It limits the size of the cache by
 * removing the eldest entry. The addToCache and getFromCache method perform checks on whether the
 * given keys exist in the cache before calling to Map's 'put' and 'get' methods.
 */
public class ForecastCache implements Cache {

  private LinkedHashMap<String, LinkedHashMap<String, Object>> cache;
  private final int cacheCapacity = 10;

  public ForecastCache() {

    this.cache =
        new LinkedHashMap<>(cacheCapacity) {
          protected boolean removeEldestEntry(Map.Entry eldest) {
            return this.size() > cacheCapacity;
          }
        };
  }

  @Override
  public void addToCache(Object[] keys, Object forecast) {
    String region = (String) keys[0];
    String day = (String) keys[1];

    if (!cache.containsKey(region)) {
      cache.put(region, new LinkedHashMap<>());
    }
    if (!cache.get(region).containsKey(day)) {
      cache.get(region).put(day, forecast);
    }
  }

  @Override
  public Object getFromCache(Object[] keys) {
    String region = (String) keys[0];
    String day = (String) keys[1];
    if (!cache.containsKey(region) || !cache.get(region).containsKey(day)) {
      return null;
    }
    return cache.get(region).get(day);
  }

  public int getCacheSize() {
    return cache.size();
  }
}
