package ic.doc;

public interface Cache {

  void addToCache(Object[] keys, Object value);

  Object getFromCache(Object[] keys);

  void removeCacheItem(Object[] keys);

  int getCacheSize();

  void clearCache();
}
