package ic.doc;

/** This is the main client that users will be interacting with. */
public class WeatherForecast {

  private final WeatherForecaster forecaster =
      new ForecasterProxy(new ForecasterAdapter(), new ForecastCache());

  public ForecastInfo getForecastFor(String region, String day) {
    return forecaster.getForecast(region, day);
  }

}
