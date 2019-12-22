# Mybatis Transaction
- mybatis 事务相关的类在 `org.apache.ibatis.transaction`
- 两个接口`org.apache.ibatis.transaction.Transaction`和`org.apache.ibatis.transaction.TransactionFactory`
## Transaction
```java
public interface Transaction {

    /**
     * 获取数据库连接
     * Retrieve inner database connection.
     * @return DataBase connection
     * @throws SQLException
     */
    Connection getConnection() throws SQLException;

    /**
     * 事务提交
     * Commit inner database connection.
     * @throws SQLException
     */
    void commit() throws SQLException;

    /**
     * 事务回滚
     * Rollback inner database connection.
     * @throws SQLException
     */
    void rollback() throws SQLException;

    /**
     * 关闭事务
     * Close inner database connection.
     * @throws SQLException
     */
    void close() throws SQLException;

    /**
     * 获取超时时间
     * Get transaction timeout if set.
     * @throws SQLException
     */
    Integer getTimeout() throws SQLException;

}

```

## TransactionFactory
```java
public interface TransactionFactory {

    /**
     * 设置事务相关的属性
     * Sets transaction factory custom properties.
     * @param props
     */
    default void setProperties(Properties props) {
        // NOP
    }

    /**
     * 创建事务实例对象
     * Creates a {@link Transaction} out of an existing connection.
     * @param conn Existing database connection
     * @return Transaction
     * @since 3.1.0
     */
    Transaction newTransaction(Connection conn);

    /**
     * 创建事务实例对象
     * Creates a {@link Transaction} out of a datasource.
     * @param dataSource DataSource to take the connection from
     * @param level Desired isolation level
     * @param autoCommit Desired autocommit
     * @return Transaction
     * @since 3.1.0
     */
    Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit);

}
```

## mybatis 事务类型
```java
        typeAliasRegistry.registerAlias("JDBC", JdbcTransactionFactory.class);
        typeAliasRegistry.registerAlias("MANAGED", ManagedTransactionFactory.class);
```