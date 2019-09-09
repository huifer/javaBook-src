package com.huifer.springboot.alibaba.sentinel;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wang
 * @description:
 */
public class FirstDemo {

    public static final String PUBLIC_RESOURCE = "PUBLIC_RESOURCE";


    public static void initFlowRules(String resource) {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource(resource);
        //限流阈值类型，此处为qps类型
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        //限流阈值，表示每秒钟通过5次请求
        rule.setCount(5);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }

    public static void main(String[] args) {
        initFlowRules(PUBLIC_RESOURCE);
        for (int i = 0; i < 13; i++) {

            Entry entry = null;
            try {
                entry = SphU.entry(PUBLIC_RESOURCE);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (entry != null) {
                    entry.exit();
                }
            }
        }

    }

    private static void doSomething() {
        System.out.println("hello");
    }
}