package ic.doc;

/** This class acts as a gateway that decides whether to query to the third party library. */
public class ForecasterProxy implements WeatherForecaster {

  private final WeatherForecaster forecaster;
  private final Cache cache;

  public ForecasterProxy(WeatherForecaster forecaster, Cache cache) {
    this.forecaster = forecaster;
    this.cache = cache;
  }

  @Override
  public ForecastInfo getForecast(String region, String day) {

    // Try querying from cache
    String[] keys = {region, day};
    ForecastInfo cachedForecast = (ForecastInfo) cache.getFromCache(keys);

    if (cachedForecast == null) {
      // If query is not in cache, make a query to forecaster, then add to cache
      ForecastInfo fetchedForecast = forecaster.getForecast(region, day);
      cache.addToCache(keys, fetchedForecast);
      return fetchedForecast;
    }
    return cachedForecast;
  }
}
