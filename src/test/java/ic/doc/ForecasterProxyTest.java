package ic.doc;

import com.weather.Forecast;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class ForecasterProxyTest {
  @Rule public JUnitRuleMockery context = new JUnitRuleMockery();

  final String lowerCaseRegion = "london";
  final String lowerCaseDay = "monday";
  final String upperCaseRegion = "LONDON";
  final String upperCaseDay = "MONDAY";
  final String[] keys = {upperCaseRegion, upperCaseDay};
  final Forecast forecast = new Forecast("Mock summary", 25) {};

  WeatherForecaster forecaster = context.mock(WeatherForecaster.class);
  Cache cache = context.mock(Cache.class);

  ForecasterProxy forecasterProxy = new ForecasterProxy(forecaster, cache);

  @Test
  public void queryInLowerCaseWillBeConvertedToUpperCase() {

    context.checking(
        new Expectations() {
          {
            exactly(1).of(cache).getFromCache(keys);
            will(returnValue(null));
            exactly(1).of(forecaster).forecastFor(upperCaseRegion, upperCaseDay);
            will(returnValue(forecast));
            exactly(1).of(cache).addToCache(keys, forecast);
          }
        });
    forecasterProxy.forecastFor(lowerCaseRegion, lowerCaseDay);
  }

  @Test
  public void queryToForecasterWhenNotInCache() {

    context.checking(
        new Expectations() {
          {
            exactly(1).of(cache).getFromCache(keys);
            will(returnValue(null));
            exactly(1).of(forecaster).forecastFor(upperCaseRegion, upperCaseDay);
            will(returnValue(forecast));
            exactly(1).of(cache).addToCache(keys, forecast);
          }
        });
    forecasterProxy.forecastFor(upperCaseRegion, upperCaseDay);
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

    forecasterProxy.forecastFor(upperCaseRegion, upperCaseDay);
  }
}
