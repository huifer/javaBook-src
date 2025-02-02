/**
 * Copyright 2009-2019 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.ibatis.jdbc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * sql 脚本创建工具
 *
 * @author Clinton Begin
 * @author Jeff Butler
 * @author Adam Gent
 * @author Kazuki Shimizu
 */
public abstract class AbstractSQL<T> {

    /**
     * and 替换文本
     */
    private static final String AND = ") \nAND (";
    /**
     * or 替换文本
     */
    private static final String OR = ") \nOR (";

    private final SQLStatement sql = new SQLStatement();

    public abstract T getSelf();

    /**
     * update
     * @param table
     * @return
     */
    public T UPDATE(String table) {
        sql().statementType = SQLStatement.StatementType.UPDATE;
        sql().tables.add(table);
        return getSelf();
    }

    public T SET(String sets) {
        sql().sets.add(sets);
        return getSelf();
    }

    /**
     * @since 3.4.2
     */
    public T SET(String... sets) {
        sql().sets.addAll(Arrays.asList(sets));
        return getSelf();
    }

    /**
     *
     * @param tableName 表名
     * @return
     */
    public T INSERT_INTO(String tableName) {
        sql().statementType = SQLStatement.StatementType.INSERT;

        // DEBUG阶段 下面这句话执行后 sql文多了 INSERT INTO 来源是从哪里来的
        // 变量监控的时候调用了toString方法
        sql().tables.add(tableName);
        return getSelf();
    }

    public T VALUES(String columns, String values) {
        INTO_COLUMNS(columns);
        INTO_VALUES(values);
        return getSelf();
    }

    /**
     * insert 字段列
     * @since 3.4.2
     */
    public T INTO_COLUMNS(String... columns) {
        sql().columns.addAll(Arrays.asList(columns));
        return getSelf();
    }

    /**
     * insert 值
     * @since 3.4.2
     */
    public T INTO_VALUES(String... values) {
        List<String> list = sql().valuesList.get(sql().valuesList.size() - 1);
        Collections.addAll(list, values);
        return getSelf();
    }

    /**
     * select 列
     * @param columns
     * @return
     */
    public T SELECT(String columns) {
        sql().statementType = SQLStatement.StatementType.SELECT;
        sql().select.add(columns);
        return getSelf();
    }

    /**
     * select 列
     * @since 3.4.2
     */
    public T SELECT(String... columns) {
        sql().statementType = SQLStatement.StatementType.SELECT;
        sql().select.addAll(Arrays.asList(columns));
        return getSelf();
    }

    /**
     * select  去重列
     * @param columns
     * @return
     */
    public T SELECT_DISTINCT(String columns) {
        sql().distinct = true;
        SELECT(columns);
        return getSelf();
    }

    /**
     * select 多列去重
     * @since 3.4.2
     */
    public T SELECT_DISTINCT(String... columns) {
        sql().distinct = true;
        SELECT(columns);
        return getSelf();
    }

    /**
     *  删除表
     * @param table
     * @return
     */
    public T DELETE_FROM(String table) {
        sql().statementType = SQLStatement.StatementType.DELETE;
        sql().tables.add(table);
        return getSelf();
    }

    /**
     * from
     * @param table
     * @return
     */
    public T FROM(String table) {
        sql().tables.add(table);
        return getSelf();
    }

    /**
     * @since 3.4.2
     */
    public T FROM(String... tables) {
        sql().tables.addAll(Arrays.asList(tables));
        return getSelf();
    }

    public T JOIN(String join) {
        sql().join.add(join);
        return getSelf();
    }

    /**
     * @since 3.4.2
     */
    public T JOIN(String... joins) {
        sql().join.addAll(Arrays.asList(joins));
        return getSelf();
    }

    public T INNER_JOIN(String join) {
        sql().innerJoin.add(join);
        return getSelf();
    }

    /**
     * @since 3.4.2
     */
    public T INNER_JOIN(String... joins) {
        sql().innerJoin.addAll(Arrays.asList(joins));
        return getSelf();
    }

    public T LEFT_OUTER_JOIN(String join) {
        sql().leftOuterJoin.add(join);
        return getSelf();
    }

    /**
     * @since 3.4.2
     */
    public T LEFT_OUTER_JOIN(String... joins) {
        sql().leftOuterJoin.addAll(Arrays.asList(joins));
        return getSelf();
    }

    public T RIGHT_OUTER_JOIN(String join) {
        sql().rightOuterJoin.add(join);
        return getSelf();
    }

    /**
     * @since 3.4.2
     */
    public T RIGHT_OUTER_JOIN(String... joins) {
        sql().rightOuterJoin.addAll(Arrays.asList(joins));
        return getSelf();
    }

    public T OUTER_JOIN(String join) {
        sql().outerJoin.add(join);
        return getSelf();
    }

    /**
     * @since 3.4.2
     */
    public T OUTER_JOIN(String... joins) {
        sql().outerJoin.addAll(Arrays.asList(joins));
        return getSelf();
    }

    public T WHERE(String conditions) {
        sql().where.add(conditions);
        sql().lastList = sql().where;
        return getSelf();
    }

    /**
     * @since 3.4.2
     */
    public T WHERE(String... conditions) {
        sql().where.addAll(Arrays.asList(conditions));
        sql().lastList = sql().where;
        return getSelf();
    }

    public T OR() {
        sql().lastList.add(OR);
        return getSelf();
    }

    public T AND() {
        sql().lastList.add(AND);
        return getSelf();
    }

    public T GROUP_BY(String columns) {
        sql().groupBy.add(columns);
        return getSelf();
    }

    /**
     * @since 3.4.2
     */
    public T GROUP_BY(String... columns) {
        sql().groupBy.addAll(Arrays.asList(columns));
        return getSelf();
    }

    public T HAVING(String conditions) {
        sql().having.add(conditions);
        sql().lastList = sql().having;
        return getSelf();
    }

    /**
     * @since 3.4.2
     */
    public T HAVING(String... conditions) {
        sql().having.addAll(Arrays.asList(conditions));
        sql().lastList = sql().having;
        return getSelf();
    }

    public T ORDER_BY(String columns) {
        sql().orderBy.add(columns);
        return getSelf();
    }

    /**
     * @since 3.4.2
     */
    public T ORDER_BY(String... columns) {
        sql().orderBy.addAll(Arrays.asList(columns));
        return getSelf();
    }

    /**
     * Set the limit variable string(e.g. {@code "#{limit}"}).
     *
     * @param variable a limit variable string
     * @return a self instance
     * @see #OFFSET(String)
     * @since 3.5.2
     */
    public T LIMIT(String variable) {
        sql().limit = variable;
        sql().limitingRowsStrategy = SQLStatement.LimitingRowsStrategy.OFFSET_LIMIT;
        return getSelf();
    }

    /**
     * Set the limit value.
     *
     * @param value an offset value
     * @return a self instance
     * @see #OFFSET(long)
     * @since 3.5.2
     */
    public T LIMIT(int value) {
        return LIMIT(String.valueOf(value));
    }

    /**
     * Set the offset variable string(e.g. {@code "#{offset}"}).
     *
     * @param variable a offset variable string
     * @return a self instance
     * @see #LIMIT(String)
     * @since 3.5.2
     */
    public T OFFSET(String variable) {
        sql().offset = variable;
        sql().limitingRowsStrategy = SQLStatement.LimitingRowsStrategy.OFFSET_LIMIT;
        return getSelf();
    }

    /**
     * Set the offset value.
     *
     * @param value an offset value
     * @return a self instance
     * @see #LIMIT(int)
     * @since 3.5.2
     */
    public T OFFSET(long value) {
        return OFFSET(String.valueOf(value));
    }

    /**
     * Set the fetch first rows variable string(e.g. {@code "#{fetchFirstRows}"}).
     *
     * @param variable a fetch first rows variable string
     * @return a self instance
     * @see #OFFSET_ROWS(String)
     * @since 3.5.2
     */
    public T FETCH_FIRST_ROWS_ONLY(String variable) {
        sql().limit = variable;
        sql().limitingRowsStrategy = SQLStatement.LimitingRowsStrategy.ISO;
        return getSelf();
    }

    /**
     * Set the fetch first rows value.
     *
     * @param value a fetch first rows value
     * @return a self instance
     * @see #OFFSET_ROWS(long)
     * @since 3.5.2
     */
    public T FETCH_FIRST_ROWS_ONLY(int value) {
        return FETCH_FIRST_ROWS_ONLY(String.valueOf(value));
    }

    /**
     * Set the offset rows variable string(e.g. {@code "#{offset}"}).
     *
     * @param variable a offset rows variable string
     * @return a self instance
     * @see #FETCH_FIRST_ROWS_ONLY(String)
     * @since 3.5.2
     */
    public T OFFSET_ROWS(String variable) {
        sql().offset = variable;
        sql().limitingRowsStrategy = SQLStatement.LimitingRowsStrategy.ISO;
        return getSelf();
    }

    /**
     * Set the offset rows value.
     *
     * @param value an offset rows value
     * @return a self instance
     * @see #FETCH_FIRST_ROWS_ONLY(int)
     * @since 3.5.2
     */
    public T OFFSET_ROWS(long value) {
        return OFFSET_ROWS(String.valueOf(value));
    }

    /*
     * used to add a new inserted row while do multi-row insert.
     *
     * @since 3.5.2
     */
    public T ADD_ROW() {
        sql().valuesList.add(new ArrayList<>());
        return getSelf();
    }

    private SQLStatement sql() {
        return sql;
    }

    public <A extends Appendable> A usingAppender(A a) {
        sql().sql(a);
        return a;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sql().sql(sb);
        return sb.toString();
    }

    private static class SafeAppendable {
        private final Appendable a;
        private boolean empty = true;

        public SafeAppendable(Appendable a) {
            super();
            this.a = a;
        }

        public SafeAppendable append(CharSequence s) {
            try {
                if (empty && s.length() > 0) {
                    empty = false;
                }
                a.append(s);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return this;
        }

        public boolean isEmpty() {
            return empty;
        }

    }

    /**
     * 各类语句的组装 最后应该是一个sql文
     */
    private static class SQLStatement {

        StatementType statementType;
        List<String> sets = new ArrayList<>();
        List<String> select = new ArrayList<>();
        /**
         * 表名
         */
        List<String> tables = new ArrayList<>();
        List<String> join = new ArrayList<>();
        List<String> innerJoin = new ArrayList<>();
        List<String> outerJoin = new ArrayList<>();
        List<String> leftOuterJoin = new ArrayList<>();
        List<String> rightOuterJoin = new ArrayList<>();
        List<String> where = new ArrayList<>();
        List<String> having = new ArrayList<>();
        List<String> groupBy = new ArrayList<>();
        List<String> orderBy = new ArrayList<>();
        List<String> lastList = new ArrayList<>();
        List<String> columns = new ArrayList<>();
        List<List<String>> valuesList = new ArrayList<>();
        boolean distinct;
        String offset;
        String limit;
        LimitingRowsStrategy limitingRowsStrategy = LimitingRowsStrategy.NOP;

        public SQLStatement() {
            // Prevent Synthetic Access
            valuesList.add(new ArrayList<>());
        }

        private void sqlClause(SafeAppendable builder, String keyword, List<String> parts, String open, String close,
                               String conjunction) {
            if (!parts.isEmpty()) {
                if (!builder.isEmpty()) {
                    // 换行
                    builder.append("\n");
                }
                builder.append(keyword);
                builder.append(" ");
                builder.append(open);
                String last = "________";
                for (int i = 0, n = parts.size(); i < n; i++) {
                    String part = parts.get(i);
                    if (i > 0 && !part.equals(AND) && !part.equals(OR) && !last.equals(AND) && !last.equals(OR)) {
                        builder.append(conjunction);
                    }
                    builder.append(part);
                    last = part;
                }
                builder.append(close);
            }
        }

        /**
         * select 组装
         * @param builder
         * @return
         */
        private String selectSQL(SafeAppendable builder) {
            if (distinct) {
                sqlClause(builder, "SELECT DISTINCT", select, "", "", ", ");
            } else {
                sqlClause(builder, "SELECT", select, "", "", ", ");
            }

            sqlClause(builder, "FROM", tables, "", "", ", ");
            joins(builder);
            sqlClause(builder, "WHERE", where, "(", ")", " AND ");
            sqlClause(builder, "GROUP BY", groupBy, "", "", ", ");
            sqlClause(builder, "HAVING", having, "(", ")", " AND ");
            sqlClause(builder, "ORDER BY", orderBy, "", "", ", ");
            limitingRowsStrategy.appendClause(builder, offset, limit);
            return builder.toString();
        }

        /**
         * join 组装
         * @param builder
         */
        private void joins(SafeAppendable builder) {
            sqlClause(builder, "JOIN", join, "", "", "\nJOIN ");
            sqlClause(builder, "INNER JOIN", innerJoin, "", "", "\nINNER JOIN ");
            sqlClause(builder, "OUTER JOIN", outerJoin, "", "", "\nOUTER JOIN ");
            sqlClause(builder, "LEFT OUTER JOIN", leftOuterJoin, "", "", "\nLEFT OUTER JOIN ");
            sqlClause(builder, "RIGHT OUTER JOIN", rightOuterJoin, "", "", "\nRIGHT OUTER JOIN ");
        }

        /**
         * insert 组装
         * @param builder
         * @return
         */
        private String insertSQL(SafeAppendable builder) {
            sqlClause(builder, "INSERT INTO", tables, "", "", "");
            sqlClause(builder, "", columns, "(", ")", ", ");
            for (int i = 0; i < valuesList.size(); i++) {
                sqlClause(builder, i > 0 ? "," : "VALUES", valuesList.get(i), "(", ")", ", ");
            }
            return builder.toString();
        }

        /**
         * delete 组装
         * @param builder
         * @return
         */
        private String deleteSQL(SafeAppendable builder) {
            sqlClause(builder, "DELETE FROM", tables, "", "", "");
            sqlClause(builder, "WHERE", where, "(", ")", " AND ");
            limitingRowsStrategy.appendClause(builder, null, limit);
            return builder.toString();
        }

        /**
         * update 组装
         * @param builder
         * @return
         */
        private String updateSQL(SafeAppendable builder) {
            sqlClause(builder, "UPDATE", tables, "", "", "");
            joins(builder);
            sqlClause(builder, "SET", sets, "", "", ", ");
            sqlClause(builder, "WHERE", where, "(", ")", " AND ");
            limitingRowsStrategy.appendClause(builder, null, limit);
            return builder.toString();
        }

        /**
         * sql 文本创建方式
         * @param a
         * @return
         */
        public String sql(Appendable a) {
            SafeAppendable builder = new SafeAppendable(a);
            if (statementType == null) {
                return null;
            }

            String answer;
            // 不同sql类型的sql创建方式
            switch (statementType) {
                case DELETE:
                    // 删除sql
                    answer = deleteSQL(builder);
                    break;

                case INSERT:
                    // 插入sql
                    answer = insertSQL(builder);
                    break;

                case SELECT:
                    // 查询sql
                    answer = selectSQL(builder);
                    break;

                case UPDATE:
                    // 更新sql
                    answer = updateSQL(builder);
                    break;

                default:
                    answer = null;
            }

            return answer;
        }

        public enum StatementType {
            DELETE, INSERT, SELECT, UPDATE
        }

        private enum LimitingRowsStrategy {
            NOP {
                @Override
                protected void appendClause(SafeAppendable builder, String offset, String limit) {
                    // NOP
                }
            },
            ISO {
                @Override
                protected void appendClause(SafeAppendable builder, String offset, String limit) {
                    if (offset != null) {
                        builder.append(" OFFSET ").append(offset).append(" ROWS");
                    }
                    if (limit != null) {
                        builder.append(" FETCH FIRST ").append(limit).append(" ROWS ONLY");
                    }
                }
            },
            OFFSET_LIMIT {
                @Override
                protected void appendClause(SafeAppendable builder, String offset, String limit) {
                    if (limit != null) {
                        builder.append(" LIMIT ").append(limit);
                    }
                    if (offset != null) {
                        builder.append(" OFFSET ").append(offset);
                    }
                }
            };

            protected abstract void appendClause(SafeAppendable builder, String offset, String limit);

        }
    }
}
