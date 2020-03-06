package ic.doc;

import com.weather.Forecast;

/**
 * This class acts as a gateway before querying to the forecaster. It implements a cache, which
 * stores and returns query results. If a query exists in the cache, the stored result will be returned to
 * the client. Otherwise, it will send the query to the forecaster and store the results in the cache.
 */
public class ForecasterProxy implements WeatherForecaster {

  private final WeatherForecaster forecaster;
  private final Cache cache;

  public ForecasterProxy(WeatherForecaster forecaster, Cache cache) {
    this.forecaster = forecaster;
    this.cache = cache;
  }

  @Override
  public Forecast getForecast(String region, String day) {

    // Try querying from cache
    String[] keys = {region, day};
    Forecast cachedForecast = cache.getFromCache(keys);

    if (cachedForecast == null) {
      // If query is not in cache, make a query to forecaster, then add to cache
      Forecast fetchedForecast = forecaster.getForecast(region, day);
      cache.addToCache(keys, fetchedForecast);
      return fetchedForecast;
    }
    return cachedForecast;
  }
}
