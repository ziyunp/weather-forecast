package ic.doc;

import com.weather.Day;
import com.weather.Forecast;
import com.weather.Forecaster;
import com.weather.Region;

public class ForecastService {

  private final Forecaster forecaster;
  private final ForecastCache cache;

  public ForecastService() {
    this.forecaster = new Forecaster();
    this.cache = new ForecastCache();
  }

  public Forecast forecastFor(String region, String day) {

    // Convert any given strings to uppercase
    region = region.toUpperCase();
    day = day.toUpperCase();

    // Try querying from cache
    String[] keys = {region, day};
    Forecast cachedForecast = cache.getFromCache(keys);

    if (cachedForecast == null) {
      // If query is not in cache, make a query to forecaster, then add to cache
      Forecast fetchedForecast = forecaster.forecastFor(Region.valueOf(region), Day.valueOf(day));
      cache.addToCache(keys, fetchedForecast);
      return fetchedForecast;
    }
    return cachedForecast;
  }

}
