package com.sang.subjectcompetition.service.util;

public class ScoreUtil {
    public static int getScore(int type,int level){
        int score=0;
        if(type==1&&level==1){
            score=level*10*type*1;//10
        }
        if(type==1&&level==2){
            score=level*20*type*1;//20
        }
        if(type==1&&level==3){
            score=level*35*type*1;//35
        }
        if(type==1&&level==4){
            score=level*55*type*1;//55
        }
        if(type==2&&level==1){
            score=level*20*type*2;//40
        }
        if(type==2&&level==2){
            score=level*35*type*2;//70
        }
        if(type==2&&level==3){
            score=level*55*type*2;//110
        }
        if(type==2&&level==4){
            score=level*70*type*2;//140
        }
        if(type==3&&level==1){
            score=level*30*type*3;//90
        }
        if(type==3&&level==2){
            score=level*55*type*3;//165
        }
        if(type==3&&level==3){
            score=level*80*type*3;//240
        }
        if(type==3&&level==4){
            score=level*110*type*3;//330
        }
        if(type==4&&level==1){
            score=level*50*type*4;//200
        }
        if(type==4&&level==2){
            score=level*100*type*4;//400
        }
        if(type==4&&level==3){
            score=level*150*type*4;//450
        }
        if(type==4&&level==4){
            score=level*300*type*4;//1200
        }
        return score;
    }
}
