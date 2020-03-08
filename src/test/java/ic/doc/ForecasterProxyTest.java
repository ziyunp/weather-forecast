package ic.doc;

import static org.junit.Assert.assertEquals;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class ForecasterProxyTest {
  @Rule public JUnitRuleMockery context = new JUnitRuleMockery();

  final String region = "LONDON";
  final String day = "MONDAY";
  final String[] keys = {region, day};
  final ForecastInfo testObject = new ForecastInfo(29, "Mock forecast info.");

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
            will(returnValue(testObject));
            exactly(1).of(cache).addToCache(keys, testObject);
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
            will(returnValue(testObject));
          }
        });

    forecasterProxy.getForecast(region, day);
  }

  @Test
  public void getTemperatureWillExtractInfoFromFetchedForecastIfQueryIsNotInCache() {
    context.checking(
        new Expectations() {
          {
            exactly(1).of(cache).getFromCache(keys);
            will(returnValue(null));
            exactly(1).of(forecaster).getForecast(region, day);
            will(returnValue(testObject));
            exactly(1).of(cache).addToCache(keys, testObject);
          }
        });
    Integer temperature = forecasterProxy.getTemperature(region, day);
    assertEquals(temperature, testObject.temperature());
  }

  @Test
  public void getTemperatureWillExtractInfoFromCachedForecastIfQueryIsInCache() {
    context.checking(
        new Expectations() {
          {
            exactly(1).of(cache).getFromCache(keys);
            will(returnValue(testObject));
          }
        });
    Integer temperature = forecasterProxy.getTemperature(region, day);
    assertEquals(temperature, testObject.temperature());
  }

  @Test
  public void getSummaryWillExtractInfoFromFetchedForecastIfQueryIsNotInCache() {
    context.checking(
        new Expectations() {
          {
            exactly(1).of(cache).getFromCache(keys);
            will(returnValue(null));
            exactly(1).of(forecaster).getForecast(region, day);
            will(returnValue(testObject));
            exactly(1).of(cache).addToCache(keys, testObject);
          }
        });
    String summary = forecasterProxy.getSummary(region, day);
    assertEquals(summary, testObject.summary());
  }

  @Test
  public void getSummaryWillExtractInfoFromForecastInfoIfQueryIsInCache() {
    context.checking(
        new Expectations() {
          {
            exactly(1).of(cache).getFromCache(keys);
            will(returnValue(testObject));
          }
        });
    String summary = forecasterProxy.getSummary(region, day);
    assertEquals(summary, testObject.summary());
  }
}
