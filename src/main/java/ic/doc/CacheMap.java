package ic.doc;

import java.util.LinkedHashMap;
import java.util.Map;

public class CacheMap extends LinkedHashMap {
  private static int capacity = 10;
  private Map<String, Object> cache;

  public CacheMap() {
    this.cache = new LinkedHashMap<>(capacity);
  }

  protected boolean removeEldestEntry(Map.Entry eldest) {
    return cache.size() > capacity;
  }
}
