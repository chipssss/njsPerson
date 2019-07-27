package com.njs.agriculture.utils;

import java.util.Date;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/7/27
 * @Description:
 */
public class DateUtil {
    //使用单例，双重校验锁，缩小锁的范围
    private static DateUtil dateUtil;

    private DateUtil(){}

    public static DateUtil getDateUtil(){
        if(dateUtil == null){
            synchronized (DateUtil.class){//判断如果实例为空，抢过来
                if(dateUtil == null){//关键点，抢过来时候，有可能别人刚释放并已经实例化
                    dateUtil = new DateUtil();
                }
            }
        }
        return dateUtil;
    }

    //计算时间差，以天数为单位。如：2018-08-08 和 2018-08-05 相差3天
    public int getDistanceTime(Date startTime, Date endTime) {
        int days = 0;
        long time1 = startTime.getTime();
        long time2 = endTime.getTime();

        long diff;
        if (time1 < time2) {
            diff = time2 - time1;
        } else {
            diff = time1 - time2;
        }
        days = (int) (diff / (24 * 60 * 60 * 1000));
        return days;
    }


}
