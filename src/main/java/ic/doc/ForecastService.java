package ic.doc;

import com.weather.Day;
import com.weather.Forecast;
import com.weather.Forecaster;
import com.weather.Region;
import java.util.HashMap;
import java.util.Map;

public class ForecastService {

  private final Forecaster forecaster;
  private Map<String,Forecast> cache;

  public ForecastService(Forecaster forecaster) {
    this.forecaster = forecaster;
    this.cache = new HashMap<String,Forecast>();
  }

  public Forecast forecastFor(String region, String day) {
    return forecaster.forecastFor(Region.valueOf(region), Day.valueOf(day));
  }

}
