package ic.doc;

/** This is the main client that users will be interacting with. */
public class WeatherForecast {

  private final WeatherForecaster forecaster =
      new ForecasterProxy(new ForecasterAdapter(), new ForecastCache());

  public Integer getTemperature(String region, String day) {
    return forecaster.getTemperature(region, day);
  }

  public String getSummary(String region, String day) {
    return forecaster.getSummary(region, day);
  }
}
