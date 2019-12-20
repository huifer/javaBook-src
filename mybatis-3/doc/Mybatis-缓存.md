# mybatis 缓存
- `org.apache.ibatis.cache.Cache`


## LruCache
```java
/**
 * Lru (least recently used) cache decorator.
 * LRU  緩存策略 最近最少使用的：移除最长时间不被使用的对象。
 *
 * @author Clinton Begin
 */
public class LruCache implements Cache {

    private final Cache delegate;
    /**
     * {@link  LinkedHashMap}
     */
    private Map<Object, Object> keyMap;
    private Object eldestKey;

    public LruCache(Cache delegate) {
        this.delegate = delegate;
        setSize(1024);
    }

    @Override
    public String getId() {
        return delegate.getId();
    }

    @Override
    public int getSize() {
        return delegate.getSize();
    }

    /**
     * 设置大小
     *
     * @param size
     */
    public void setSize(final int size) {
        keyMap = new LinkedHashMap<Object, Object>(size, .75F, true) {
            private static final long serialVersionUID = 4267176411845948333L;

            @Override
            protected boolean removeEldestEntry(Map.Entry<Object, Object> eldest) {
                // 数量超出预设值 执行
                boolean tooBig = size() > size;
                if (tooBig) {
//                    获取被移除的key
                    eldestKey = eldest.getKey();
                }
                return tooBig;
            }
        };
    }

    @Override
    public void putObject(Object key, Object value) {
        delegate.putObject(key, value);
        cycleKeyList(key);
    }

    @Override
    public Object getObject(Object key) {
        keyMap.get(key); //touch
        return delegate.getObject(key);
    }

    @Override
    public Object removeObject(Object key) {
        return delegate.removeObject(key);
    }

    @Override
    public void clear() {
        delegate.clear();
        keyMap.clear();
    }

    /**
     * 删除最早的一个key
     * @param key
     */
    private void cycleKeyList(Object key) {
        keyMap.put(key, key);
        if (eldestKey != null) {
            delegate.removeObject(eldestKey);
            eldestKey = null;
        }
    }

}
```