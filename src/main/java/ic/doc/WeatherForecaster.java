package ic.doc;

import com.weather.Forecast;

public interface WeatherForecaster {

  Forecast getForecast(String region, String day);
}
