package com.sang.subjectcompetition.entity.enums;

public enum Type {

    national(1,"校级"),
    provincial(2,"市级"),
    Municipal(3,"省级"),
    school(4,"国家级");


    private Integer type;
    private String typeName;

    Type(Integer type,String typeName){
        this.type = type;
        this.typeName = typeName;
    }

    public String getTypeName(){
        return typeName;
    }

    public static String getTypeNameByType(int type){
        for(Type each:Type.values()){
            if(each.type==type){
                return each.typeName;
            }
        }
        return null;
    }
}
