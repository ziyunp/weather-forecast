package ic.doc;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class CacheMapTest {
  final int capacity = 2;

  @Test
  public void constructorInstantiatesAnEmptyCache() {
    CacheMap cacheMap = new CacheMap(capacity);
    assertThat(cacheMap.size(), is(0));
  }

  @Test
  public void addingEntryToCacheIncreasesCacheSizeByOne() {
    CacheMap cacheMap = new CacheMap(capacity);
    cacheMap.put("First", "This is the first item.");
    assertThat(cacheMap.size(), is(1));
    cacheMap.put("Second", "This is the second item.");
    assertThat(cacheMap.size(), is(2));
  }

  @Test
  public void removesEldestEntryWhenCacheSizeExceedsCapacity() {
    CacheMap cacheMap = new CacheMap(capacity);
    cacheMap.put("First", "This item should be removed first when capacity is exceeded.");
    cacheMap.put("Second", "This is the second item.");
    assertThat(cacheMap.size(), is(2));
    cacheMap.put("Third", "Adding this item should have exceeded the cache capacity.");
    assertThat(cacheMap.size(), is(2));
    assertThat(cacheMap.containsKey("First"), is(false));
    assertThat(cacheMap.containsKey("Second"), is(true));
    assertThat(cacheMap.containsKey("Third"), is(true));
  }
}
