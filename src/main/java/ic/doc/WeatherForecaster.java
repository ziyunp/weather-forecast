package ic.doc;

public interface WeatherForecaster {

  ForecastInfo getForecast(String region, String day);
}
