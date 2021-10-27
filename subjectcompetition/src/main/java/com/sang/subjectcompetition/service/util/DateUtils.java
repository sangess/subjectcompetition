package com.sang.subjectcompetition.service.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static Date getYearFirst(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }

    public static Date getStartTimeOfDay(Date date){
        Calendar day= Calendar.getInstance();
        day.setTime(date);
        day.set(Calendar.HOUR_OF_DAY,0);
        day.set(Calendar.MINUTE,0);
        day.set(Calendar.SECOND,0);
        day.set(Calendar.MILLISECOND,0);
        return day.getTime();
    }

    public static Date getEndTimeOfDay(Date date){
        Calendar day= Calendar.getInstance();
        day.setTime(date);
        day.set(Calendar.HOUR_OF_DAY,23);
        day.set(Calendar.MINUTE,59);
        day.set(Calendar.SECOND,59);
        day.set(Calendar.MILLISECOND,999);
        return day.getTime();
    }

    public static int getNowYear(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    public static Date[] getDateArray(int year){
        Date[] dates = new Date[year==0?14:13];
        Date yearFirst = null;
        if(year == 0){
            Calendar instance = Calendar.getInstance();
            instance.add(Calendar.YEAR,-1);
            yearFirst = instance.getTime();
        }else{
            yearFirst = getYearFirst(year);
        }
        dates[0] = yearFirst;
        for(int i =1; i < dates.length ; i++){
            Calendar cal=Calendar.getInstance();
            cal.setTime(dates[i-1]);
            cal.add(Calendar.MONTH, +1);
            dates[i] = cal.getTime();
        }
        return dates;
    }

    public static Date[] getRecentYear(int year){
        Date[] dates = new Date[year+1];
        Date nowYearFirst = DateUtils.getYearFirst(getNowYear());
        Calendar instance = Calendar.getInstance();
        instance.setTime(nowYearFirst);
        instance.add(Calendar.YEAR,-year+1);
        Date firstYear = instance.getTime();
        dates[0]=firstYear;
        for(int i = 1 ; i < dates.length ; i++){
            instance.add(Calendar.YEAR,+1);
            dates[i]=instance.getTime();
        }
        return dates;
    }

    public static Date getYearLast(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();
        return currYearLast;
    }

    public static Date[] getFirstAndLastDay(int year){
        Date[] dates = new Date[2];
        if(year!=0){
            dates[0]=getYearFirst(year);
            dates[1]=getYearLast(year);
        }else{
            Calendar instance = Calendar.getInstance();
            dates[1] = instance.getTime();
            instance.add(Calendar.YEAR,-1);
            dates[0] = instance.getTime();
        }
        return dates;
    }

}
