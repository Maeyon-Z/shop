package com.zzz.shop.utils.id;

/**
 * @author zzz
 * 2022-04-25 10:01
 * 雪花算法生成分布式序列号
 */
public class SnowFlake {
    /**
     * 起始的时间戳:2022-04-12 11:56:45，使用时此值不可修改
     */
    private final static long START_STAMP = 1649735805910L;

    /**
     * 每一部分占用的位数
     */
    private final static long SEQUENCE_BIT   = 12; //序列号占用的位数
    private final static long MACHINE_BIT    = 5;   //机器标识占用的位数
    private final static long DATA_CENTER_BIT = 5;//数据中心占用的位数

    /**
     * 每一部分的最大值
     */
    private final static long MAX_DATA_CENTER_NUM = -1L ^ (-1L << DATA_CENTER_BIT);
    private final static long MAX_MACHINE_NUM    = -1L ^ (-1L << MACHINE_BIT);
    private final static long MAX_SEQUENCE       = -1L ^ (-1L << SEQUENCE_BIT);

    /**
     * 每一部分向左的位移
     */
    private final static long MACHINE_LEFT    = SEQUENCE_BIT;
    private final static long DATA_CENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    private final static long TIMESTAMP_LEFT   = DATA_CENTER_LEFT + DATA_CENTER_BIT;

    private long dataCenterId;  //数据中心
    private long machineId;     //机器标识
    private long sequence = 0L; //序列号
    private long lastStamp = -1L;//上一次时间戳

    public SnowFlake(long dataCenterId, long machineId) {
        if (dataCenterId > MAX_DATA_CENTER_NUM || dataCenterId < 0) {
            throw new IllegalArgumentException("dataCenterId can't be greater than MAX_DATA_CENTER_NUM or less than 0");
        }
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException("machineId can't be greater than MAX_MACHINE_NUM or less than 0");
        }
        this.dataCenterId = dataCenterId;
        this.machineId = machineId;
    }

    /**
     * 产生下一个ID
     */
    public synchronized long nextId() {
        long currStmp = getNewStamp();
        if (currStmp < lastStamp) {
            throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
        }

        if (currStmp == lastStamp) {
            //相同毫秒内，序列号自增
            sequence = (sequence + 1) & MAX_SEQUENCE;
            //同一毫秒的序列数已经达到最大
            if (sequence == 0L) {
                currStmp = getNextMill();
            }
        } else {
            //不同毫秒内，序列号置为0
            sequence = 0L;
        }

        lastStamp = currStmp;

        return (currStmp - START_STAMP) << TIMESTAMP_LEFT //时间戳部分
                | dataCenterId << DATA_CENTER_LEFT       //数据中心部分
                | machineId << MACHINE_LEFT             //机器标识部分
                | sequence;                             //序列号部分
    }

    private long getNextMill() {
        long mill = getNewStamp();
        while (mill <= lastStamp) {
            mill = getNewStamp();
        }
        return mill;
    }

    private long getNewStamp() {
        return System.currentTimeMillis();
    }

    public static Long getMaxDataCenterNum() {
        return MAX_DATA_CENTER_NUM;
    }

    public static Long getMaxMachineNum() {
        return MAX_MACHINE_NUM;
    }

    public static void main(String[] args) {
        System.out.println(MAX_DATA_CENTER_NUM);
        System.out.println(MAX_MACHINE_NUM);
    }
}
