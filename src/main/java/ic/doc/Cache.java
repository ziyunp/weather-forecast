package ic.doc;

import com.weather.Forecast;

public interface Cache {

  void addToCache(Object[] keys, Object forecast);

  Forecast getFromCache(Object[] keys);
}
