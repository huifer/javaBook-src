package com.huifer.util;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述:
 * bean 工厂
 *
 * @author huifer
 * @date 2019-03-02
 */
@Data
@NoArgsConstructor
public class BeanFactory {
    /**
     * beans标签列表
     */
    private List<BeanDefined> beanDefineds;
    /**
     * id-> 具体对象 单例模式对象
     */
    private Map<String, Object> Ioc;
    private Map<String, Object> factory;


    /**
     * 将单例模式的类保存到 Ioc
     *
     * @param beanDefineds
     * @throws Exception
     */
    public BeanFactory(List<BeanDefined> beanDefineds) throws Exception {
        this.beanDefineds = beanDefineds;

        Ioc = new HashMap<>();
        for (BeanDefined bean : beanDefineds) {
            if (bean.getScope().equals("singleton")) {
                Class<?> classFile = Class.forName(bean.getClassPath());
                Object o = classFile.newInstance();

                Ioc.put(bean.getBeanId(), o);

            }
        }

    }

    /**
     * 模仿 Spring 中的getBeans
     *
     * @param beanId beans 标签的id
     * @return object 类
     */
    public Object getBean(String beanId) {
        Object obj = null;
        // 从bean标签集合中获取id一样的
        for (BeanDefined beanDefined : beanDefineds) {
            if (beanDefined.getBeanId().equals(beanId)) {
                // 构造出正确的类
                String classPath = beanDefined.getClassPath();
                try {
                    Class classFile = Class.forName(classPath);
                    obj = classFile.newInstance();
                    return obj;
                } catch (ClassNotFoundException e) {
                    System.err.println("当前类文件不存在");
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return obj;

    }

    /**
     * 考虑是否单例模式的获取
     *
     * @param beanId beanId
     * @return 具体对象
     * @throws Exception
     */
    public Object getBeanScope(String beanId) throws Exception {
        Object obj = null;
        for (BeanDefined beanDefined : beanDefineds) {
            if (beanDefined.getBeanId().equals(beanId)) {
                String classPath = beanDefined.getClassPath();
                Class<?> classFile = Class.forName(classPath);
                String scope = beanDefined.getScope();
                if (scope.equals("prototype")) {
                    // 每次做一个新的对象返回
                    obj = classFile.newInstance();
                } else {
                    // 每次返回同一个对象
                    Ioc.get(beanDefined.getBeanId());
                }
                return obj;

            }
        }
        return obj;
    }


    public Object getBeanFactory(String beanId) throws Exception {
        Object obj = null;
        for (BeanDefined beanDefined : beanDefineds) {

            if (beanDefined.getBeanId().equals(beanId)) {
                String classPath = beanDefined.getClassPath();
                Class<?> classFile = Class.forName(classPath);
                String scope = beanDefined.getScope();
                String factoryBean = beanDefined.getFactoryBean();
                String factoryMethod = beanDefined.getFactoryMethod();
                if (scope.equals("prototype")) {
                    // 每次做一个新的对象返回

                    if (factoryBean != null && factoryMethod != null) {
                        // 自己根据 factoryBean +  factoryMethod创建一个实例对象

                        Object oc = classFile.newInstance();
                        Method method = classFile.getDeclaredMethod(factoryMethod, null);
                        method.setAccessible(true);
                        obj = method.invoke(oc, null);
                    } else {
                        obj = classFile.newInstance();
                    }
                } else {
                    // 每次返回同一个对象
                    Ioc.get(beanDefined.getBeanId());
                }
                return obj;

            }
        }

        return obj;
    }


}
