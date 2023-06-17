package com.huifer.bilibili.inteface;

import java.util.HashMap;

import com.huifer.bilibili.inteface.impl.ExcelOperation;
import com.huifer.bilibili.inteface.impl.MySqlOperation;
import com.huifer.bilibili.inteface.impl.PPTOperation;
import com.huifer.bilibili.inteface.impl.RedisOperation;

public class Run {
    public static final HashMap<Integer, Operation> OPERATION_HASH_MAP = new HashMap<Integer, Operation>();

    static {
        OPERATION_HASH_MAP.put(1, new PPTOperation());
        OPERATION_HASH_MAP.put(2, new ExcelOperation());
        OPERATION_HASH_MAP.put(3, new RedisOperation());
        OPERATION_HASH_MAP.put(4, new MySqlOperation());
    }

    /**
     * 对于doWork1同样可以使用 workType进行判断
     * @param code
     * @param workType
     */
    public static void doWork2(int code, String workType) {
        Operation operation = OPERATION_HASH_MAP.get(code);
        if (operation instanceof PPTOperation) {
            if (workType.equals("create")) {
                ((PPTOperation) operation).create();
            }
        }
        else if (operation instanceof ExcelOperation) {
            if (workType.equals("create")) {
                ((ExcelOperation) operation).create();
            }
        }
        else if (operation instanceof RedisOperation) {
            if (workType.equals("redis_import")) {
                ((RedisOperation) operation).importData();
            }
        }
        else if (operation instanceof MySqlOperation) {
            if (workType.equals("mysql_import")) {
                ((MySqlOperation) operation).importData();
            }
        }
    }

    public static void main(String[] args) {
        int code = 1;
        doWork(code);
    }

    public static void doWork(int code) {
        if (code == 1) {
            PPTOperation pptOperation = new PPTOperation();
            pptOperation.create();
        }
        else if (code == 2) {
            ExcelOperation excelOperation = new ExcelOperation();
            excelOperation.create();
        }
        else if (code == 3) {
            RedisOperation redisOperation = new RedisOperation();
            redisOperation.importData();
        }
        else if (code == 4) {
            MySqlOperation mySqlOperation = new MySqlOperation();
            mySqlOperation.importData();
        }
    }

}
