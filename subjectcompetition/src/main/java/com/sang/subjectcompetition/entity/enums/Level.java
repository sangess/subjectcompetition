package com.sang.subjectcompetition.entity.enums;

public enum Level {

    firstPrize(1,"其他奖项"),
    secondPrize(2,"三等奖"),
    thirdPrize(3,"二等奖"),
    otherPrize(4,"一等奖");

    private Integer level;
    private String levelName;

    Level(Integer level,String levelName){
        this.level = level;
        this.levelName = levelName;
    }

    public String getLevelName(){
        return levelName;
    }

    public static String getTypeNameByType(int level){
        for(Level each:Level.values()){
            if(each.level==level){
                return each.levelName;
            }
        }
        return null;
    }

}
