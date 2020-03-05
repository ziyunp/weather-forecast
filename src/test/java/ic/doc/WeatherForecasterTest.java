package ic.doc;

import static org.junit.Assert.*;

import com.weather.Day;
import com.weather.Forecast;
import com.weather.Region;
import org.junit.Test;

public class WeatherForecasterTest {
  final WeatherForecaster forecaster = new WeatherForecaster();
  @Test
  public void canGetForecastByRegionAndDay() {

    Forecast londonForecast = forecaster.getForecastFor(Region.LONDON, Day.MONDAY);
    System.out.println("London outlook: " + londonForecast.summary());
    System.out.println("London temperature: " + londonForecast.temperature());

    Forecast edinburghForecast = forecaster.getForecastFor(Region.EDINBURGH, Day.MONDAY);

    System.out.println("Edinburgh outlook: " + edinburghForecast.summary());
    System.out.println("Edinburgh temperature: " + edinburghForecast.temperature());
  }
}