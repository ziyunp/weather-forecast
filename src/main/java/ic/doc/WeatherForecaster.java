package ic.doc;

import com.weather.Forecast;

public interface WeatherForecaster {

  Forecast forecastFor(String region, String day);
}
