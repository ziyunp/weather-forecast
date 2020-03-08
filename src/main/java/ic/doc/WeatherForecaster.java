package ic.doc;

public interface WeatherForecaster {

  ForecastInfo getForecast(String region, String day);

  default Integer getTemperature(String region, String day) {
    ForecastInfo forecast = getForecast(region, day);
    if (forecast != null) return forecast.temperature();
    return null;
  }

  default String getSummary(String region, String day) {
    ForecastInfo forecast = getForecast(region, day);
    if (forecast != null) return forecast.summary();
    return null;
  };
}
