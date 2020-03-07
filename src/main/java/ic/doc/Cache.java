package ic.doc;

public interface Cache {

  void addToCache(Object[] keys, Object forecast);

  Object getFromCache(Object[] keys);
}
