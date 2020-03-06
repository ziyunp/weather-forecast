package ic.doc;

import com.weather.Day;
import com.weather.Forecast;
import com.weather.Forecaster;
import com.weather.Region;

/**
 * This class acts as an adapter to the third party forecaster library. It wraps the third party
 * library with the WeatherForecaster interface. Queries will be converted to the compatible
 * format to the 3rd party library here.
 */
public class ForecasterAdapter implements WeatherForecaster {

  private final Forecaster forecaster;

  public ForecasterAdapter() {

    this.forecaster = new Forecaster();
  }

  public Forecast getForecast(String region, String day) {

    // Convert queries to compatible format
    region = toCompatibleFormat(region);
    day = toCompatibleFormat(day);

    return forecaster.forecastFor(Region.valueOf(region), Day.valueOf(day));
  }

  private String toCompatibleFormat(String query) {
    // For the current 3rd party library, query must be in upper case
    return query.toUpperCase();
  }
}
