package ic.doc;

import com.weather.Forecast;
import com.weather.Forecaster;

public class WeatherForecast {

  private final ForecasterProxy forecaster =
      new ForecasterProxy(
          new ForecasterAdapter(new Forecaster()), new ForecastCache(new CacheMap(10)));

  public Forecast getForecastFor(String region, String day) {
    return forecaster.forecastFor(region, day);
  }
}
