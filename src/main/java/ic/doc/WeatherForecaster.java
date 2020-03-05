package ic.doc;

import com.weather.Forecast;
import com.weather.Forecaster;

public class WeatherForecaster {

  private final ForecastService forecaster = new ForecastService(new Forecaster());

  public Forecast getForecastFor(String region, String day) {
    return forecaster.forecastFor(region, day);
  }

}
