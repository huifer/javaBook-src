/**
 * Copyright 2009-2017 the original author or authors.
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
package org.apache.ibatis.builder;

import org.apache.ibatis.mapping.Discriminator;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;

import java.util.List;

/**
 * @author Eduardo Macarron
 */
public class ResultMapResolver {
    private final MapperBuilderAssistant assistant;
    private final String id;
    private final Class<?> type;
    private final String extend;
    private final Discriminator discriminator;
    private final List<ResultMapping> resultMappings;
    private final Boolean autoMapping;

    /**
     * <resultMap id="base" type="com.huifer.mybatis.entity.Person">
     * <id column="ID" jdbcType="VARCHAR" property="id"/>
     * <result column="age" jdbcType="INTEGER" property="age"/>
     * <collection property="name" jdbcType="VARCHAR"/>
     * </resultMap>
     *
     * @param assistant      MapperBuilderAssistant 针对当前 mapper
     * @param id             resultMap 标签的 id 属性
     * @param type           resultMap 标签的 type 属性的字节码
     * @param extend         resultMap 标签的 extend 属性
     * @param discriminator  下级标签
     * @param resultMappings 夏季标签
     * @param autoMapping    resultMap 标签的 autoMapping 属性
     */
    public ResultMapResolver(MapperBuilderAssistant assistant, String id, Class<?> type, String extend, Discriminator discriminator, List<ResultMapping> resultMappings, Boolean autoMapping) {
        this.assistant = assistant;
        this.id = id;
        this.type = type;
        this.extend = extend;
        this.discriminator = discriminator;
        this.resultMappings = resultMappings;
        this.autoMapping = autoMapping;
    }

    /**
     * resolve
     * @return
     */
    public ResultMap resolve() {
        return assistant.addResultMap(this.id, this.type, this.extend, this.discriminator, this.resultMappings, this.autoMapping);
    }

}