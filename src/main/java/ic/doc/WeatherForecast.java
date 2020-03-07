package ic.doc;

import com.weather.Forecast;

/** This is the main client that users will be interacting with. */
public class WeatherForecast {

  private final WeatherForecaster forecaster =
      new ForecasterProxy(new ForecasterAdapter(), new ForecastCache());

  public Forecast getForecastFor(String region, String day) {
    return (Forecast) forecaster.getForecast(region, day);
  }
}
