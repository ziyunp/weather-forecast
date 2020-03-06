package ic.doc;


import com.weather.Forecast;

public class WeatherForecaster {

  private final ForecastService forecaster = new ForecastService();

  public Forecast getForecastFor(String region, String day) {
    return forecaster.forecastFor(region, day);
  }

}
