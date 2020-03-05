package ic.doc;

import com.weather.Day;
import com.weather.Forecast;
import com.weather.Forecaster;
import com.weather.Region;

public class WeatherForecaster {
  private final Forecaster forecaster = new Forecaster();

  public Forecast getForecastFor(Region region, Day day) {
    return forecaster.forecastFor(region, day);
  }

}
