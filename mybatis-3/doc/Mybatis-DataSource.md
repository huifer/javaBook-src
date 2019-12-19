# Mybatis DataSource
- `org.apache.ibatis.datasource.DataSourceFactory`
```java
/**
 * 数据源工厂
 * @author Clinton Begin
 */
public interface DataSourceFactory {

    /**
     * 设置 dataSource 属性
     * @param props
     */
    void setProperties(Properties props);

    /**
     * 获取 dataSource
     * @return {@link DataSource}
     */
    DataSource getDataSource();

}

```