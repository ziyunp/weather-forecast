package ic.doc;

import com.weather.Forecast;
import java.util.Map;

public class ForecastCache implements Cache {

  private Map<String, Map<String, Forecast>> cache;

  public ForecastCache() {

    this.cache = new CacheMap();
  }

  @Override
  public void addToCache(Object[] keys, Object forecast) {
    String region = (String) keys[0];
    String day = (String) keys[1];

    if (!cache.containsKey(region)) {
      cache.put(region, new CacheMap());
    }
    if (!cache.get(region).containsKey(day)) {
      ((Map)cache.get(region)).put(day, forecast);
    }
  }

  @Override
  public Forecast getFromCache(Object[] keys) {
    String region = (String) keys[0];
    String day = (String) keys[1];
    if (!cache.containsKey(region) || !cache.get(region).containsKey(day)) {
      return null;
    }
    return (Forecast)((Map)cache.get(region)).get(day);
  }
}
