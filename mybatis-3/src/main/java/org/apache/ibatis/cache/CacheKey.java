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
package org.apache.ibatis.cache;

import org.apache.ibatis.reflection.ArrayUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * @author Clinton Begin
 */
public class CacheKey implements Cloneable, Serializable {

    public static final CacheKey NULL_CACHE_KEY = new CacheKey() {
        @Override
        public void update(Object object) {
            throw new CacheException("Not allowed to update a null cache key instance.");
        }

        @Override
        public void updateAll(Object[] objects) {
            throw new CacheException("Not allowed to update a null cache key instance.");
        }
    };
    private static final long serialVersionUID = 1146682552656046210L;
    private static final int DEFAULT_MULTIPLIER = 37;
    private static final int DEFAULT_HASHCODE = 17;

    private final int multiplier;
    private int hashcode;
    private long checksum;
    private int count;
    // 8/21/2017 - Sonarlint flags this as needing to be marked transient.  While true if content is not serializable, this is not always true and thus should not be marked transient.
    private List<Object> updateList;

    public CacheKey() {
        this.hashcode = DEFAULT_HASHCODE;
        this.multiplier = DEFAULT_MULTIPLIER;
        this.count = 0;
        this.updateList = new ArrayList<>();
    }

    public CacheKey(Object[] objects) {
        this();
        updateAll(objects);
    }

    public int getUpdateCount() {
        return updateList.size();
    }

    public void update(Object object) {
        // 求 输入参数的hash
        int baseHashCode = object == null ? 1 : ArrayUtil.hashCode(object);

        // 自增
        count++;
        // 校验和
        checksum += baseHashCode;
        // 更新 hash
        baseHashCode *= count;
        // hash = 乘数 * hashcode + 新的hashcode
        hashcode = multiplier * hashcode + baseHashCode;

        updateList.add(object);
    }

    public void updateAll(Object[] objects) {
        for (Object o : objects) {
            update(o);
        }
    }

    /**
     * 相同元素判断
     *
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            // 等于自生
            return true;
        }
        if (!(object instanceof CacheKey)) {
            /**
             * 不是这个类型{@link CacheKey}
             */
            return false;
        }

        // 类型转换
        final CacheKey cacheKey = (CacheKey) object;

        if (hashcode != cacheKey.hashcode) {
            // 当前的hashcode 和 输入的hashcode 比较
            return false;
        }
        if (checksum != cacheKey.checksum) {
            // 当前的校验和 与输入的校验和比较
            return false;
        }
        if (count != cacheKey.count) {
            // 当前总数 和 输入的总数比较
            return false;
        }

        for (int i = 0; i < updateList.size(); i++) {
            // 循环校验每个数据 做数据上的比较(A:a=1,B:a=1)
            Object thisObject = updateList.get(i);
            Object thatObject = cacheKey.updateList.get(i);
            if (!ArrayUtil.equals(thisObject, thatObject)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return hashcode;
    }

    @Override
    public String toString() {
        StringJoiner returnValue = new StringJoiner(":");
        returnValue.add(String.valueOf(hashcode));
        returnValue.add(String.valueOf(checksum));
        updateList.stream().map(ArrayUtil::toString).forEach(returnValue::add);
        return returnValue.toString();
    }

    @Override
    public CacheKey clone() throws CloneNotSupportedException {
        CacheKey clonedCacheKey = (CacheKey) super.clone();
        clonedCacheKey.updateList = new ArrayList<>(updateList);
        return clonedCacheKey;
    }

}
