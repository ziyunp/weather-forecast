package ic.doc;

import com.weather.Day;
import com.weather.Forecast;
import com.weather.Forecaster;
import com.weather.Region;

/**
 * This class acts as an adapter to the third party forecaster library. It wraps the third party
 * library with the WeatherForecaster interface and store the fetched Forecast in our own
 * ForecastInfo class. Queries will be converted to the compatible format to the 3rd party library.
 */
public class ForecasterAdapter implements WeatherForecaster {

  private final Forecaster forecaster;

  public ForecasterAdapter() {

    this.forecaster = new Forecaster();
  }

  @Override
  public ForecastInfo getForecast(String region, String day) {
    Region regionQuery = formatRegionQuery(region);
    Day dayQuery = formatDayQuery(day);
    if (regionQuery == null || dayQuery == null) {
      return null;
    }
    Forecast forecast = forecaster.forecastFor(regionQuery, dayQuery);
    return new ForecastInfo(forecast.temperature(), forecast.summary());
  }

  private Region formatRegionQuery(String query) {
    query = query.toUpperCase();
    Region region = null;
    try {
      region = Region.valueOf(query);
    } catch (IllegalArgumentException e) {
      System.out.println("Error: The queried region is invalid.");
    }
    return region;
  }

  private Day formatDayQuery(String query) {
    query = query.toUpperCase();
    Day day = null;
    try {
      day = Day.valueOf(query);
    } catch (IllegalArgumentException e) {
      System.out.println("Error: The queried day is invalid.");
    }
    return day;
  }
}
