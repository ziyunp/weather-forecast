package ic.doc;

/** This is the main client that users will be interacting with. */
public class WeatherForecast {
  private final int cacheCapacity = 10;
  private final long cacheExpiry = 60 * 60 * 1000; // One hour
  private final WeatherForecaster forecaster =
      new ForecasterProxy(new ForecasterAdapter(), new ForecastCache(cacheCapacity, cacheExpiry));

  public Integer getTemperature(String region, String day) {
    return forecaster.getTemperature(region, day);
  }

  public String getSummary(String region, String day) {
    return forecaster.getSummary(region, day);
  }
}
