package ic.doc;

import com.weather.Day;
import com.weather.Forecast;
import com.weather.Forecaster;
import com.weather.Region;

public class ForecasterAdapter implements WeatherForecaster {

  private final Forecaster forecaster;

  public ForecasterAdapter(Forecaster forecaster) {

    this.forecaster = forecaster;
  }

  public Forecast forecastFor(String region, String day) {

    return forecaster.forecastFor(Region.valueOf(region), Day.valueOf(day));
  }
}
