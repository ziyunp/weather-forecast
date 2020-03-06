package ic.doc;

import java.util.LinkedHashMap;
import java.util.Map;

public class CacheMap extends LinkedHashMap {
  private final int capacity;

  public CacheMap(int capacity) {
    this.capacity = capacity;
  }

  protected boolean removeEldestEntry(Map.Entry eldest) {
    return this.size() > capacity;
  }
}
