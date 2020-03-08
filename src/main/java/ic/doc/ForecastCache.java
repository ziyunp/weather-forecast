package ic.doc;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This class is a custom cache to store forecast queries. It limits the size of the cache by
 * removing the eldest entry and schedules to remove a cache item on expiry. The addToCache and
 * getFromCache method
 * perform checks on
 * whether the
 * given keys exist in the cache before calling to Map's 'put' and 'get' methods.
 */
public class ForecastCache implements Cache {

  private LinkedHashMap<String, LinkedHashMap<String, ForecastInfo>> cache;
  private final int cacheCapacity;
  private final long cacheExpiry;

  public ForecastCache(int cacheCapacity, long cacheExpiry) {

    this.cache =
        new LinkedHashMap<>(cacheCapacity) {
          protected boolean removeEldestEntry(Map.Entry eldest) {
            return this.size() > cacheCapacity;
          }
        };
    this.cacheCapacity = cacheCapacity;
    this.cacheExpiry = cacheExpiry;
  }

  @Override
  public void addToCache(Object[] keys, Object forecast) {
    String region = (String) keys[0];
    String day = (String) keys[1];

    if (!cache.containsKey(region)) {
      cache.put(region, new LinkedHashMap<>());
    }
    if (!cache.get(region).containsKey(day)) {
      cache.get(region).put(day, (ForecastInfo) forecast);
      Timer timer = new Timer();
      timer.schedule(scheduleRemove(keys), cacheExpiry);
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

  @Override
  public void removeCacheItem(Object[] keys) {
    String region = (String) keys[0];
    String day = (String) keys[1];
    if (cache.containsKey(region) && cache.get(region).containsKey(day)) {
      cache.get(region).remove(day);
      if (cache.get(region).size() == 0) {
        cache.get(region).remove(region);
      }
    }
  }

  private TimerTask scheduleRemove(Object[] keys) {
    return new TimerTask() {
      @Override
      public void run() {
        removeCacheItem(keys);
      }
    };
  }

  public int getCacheSize() {
    return cache.size();
  }
}
