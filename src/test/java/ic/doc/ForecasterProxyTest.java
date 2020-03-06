package ic.doc;

import com.weather.Forecast;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class ForecasterProxyTest {
  @Rule public JUnitRuleMockery context = new JUnitRuleMockery();

  final String region = "LONDON";
  final String day = "MONDAY";
  final String[] keys = {region, day};
  final Forecast forecast = new Forecast("Mock summary", 25) {};

  WeatherForecaster forecaster = context.mock(WeatherForecaster.class);
  Cache cache = context.mock(Cache.class);

  ForecasterProxy forecasterProxy = new ForecasterProxy(forecaster, cache);

  @Test
  public void queryToForecasterWhenNotInCache() {

    context.checking(
        new Expectations() {
          {
            exactly(1).of(cache).getFromCache(keys);
            will(returnValue(null));
            exactly(1).of(forecaster).getForecast(region, day);
            will(returnValue(forecast));
            exactly(1).of(cache).addToCache(keys, forecast);
          }
        });
    forecasterProxy.getForecast(region, day);
  }

  @Test
  public void getFromCacheWhenQueryIsInCache() {

    context.checking(
        new Expectations() {
          {
            exactly(1).of(cache).getFromCache(keys);
            will(returnValue(forecast));
          }
        });

    forecasterProxy.getForecast(region, day);
  }
}
