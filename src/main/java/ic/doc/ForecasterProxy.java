package ic.doc;

import com.weather.Forecast;

public class ForecasterProxy {

  private final WeatherForecaster forecaster;
  private final Cache cache;

  public ForecasterProxy(WeatherForecaster forecaster, Cache cache) {
    this.forecaster = forecaster;
    this.cache = cache;
  }

  public Forecast forecastFor(String region, String day) {

    // Convert any given strings to uppercase
    region = toCompatibleFormat(region);
    day = toCompatibleFormat(day);

    // Try querying from cache
    String[] keys = {region, day};
    Forecast cachedForecast = cache.getFromCache(keys);

    if (cachedForecast == null) {
      // If query is not in cache, make a query to forecaster, then add to cache
      Forecast fetchedForecast = forecaster.forecastFor(region, day);
      cache.addToCache(keys, fetchedForecast);
      return fetchedForecast;
    }
    return cachedForecast;
  }

  private String toCompatibleFormat(String query) {
    // For the current 3rd party library, query must be in upper case
    return query.toUpperCase();
  }
}
