package com.unithon.gamsung;

/**
 * Created by kyun on 2016-07-30.
 */
public class AnalyzeEmotion {



    public static int analyzeemotion(String input) {
        int counthappy = 0;
        int countsad = 0;
        int[] happy = new int[100];
        for(int i = 0;i<100;i++) happy[i] = 0;
        int[] sad = new int[100];
        for(int i = 0;i<100;i++) happy[i] = 0;

        happy[0] = input.indexOf("기쁘");
        happy[1] = input.indexOf("기쁜");
        happy[2]= input.indexOf("즐거");
        happy[3] = input.indexOf("즐겁");
        happy[4] = input.indexOf("행복");
        happy[5] = input.indexOf("보람");
        happy[6] = input.indexOf("좋은");
        happy[7] = input.indexOf("좋아");
        happy[8] = input.indexOf("좋다");
        happy[9] = input.indexOf("뿌듯");
        happy[10] = input.indexOf("기뻐");
        happy[11] = input.indexOf("흥미");
        happy[12] = input.indexOf("따뜻");
        happy[13] = input.indexOf("친절");
        happy[14]= input.indexOf("오늘");
        happy[15] = input.indexOf("괜찮");
        happy[16] = input.indexOf("살고");

        sad[0] = input.indexOf("슬피");
        sad[1] = input.indexOf("슬픈");
        sad[2] = input.indexOf("슬퍼");
        sad[3] = input.indexOf("슬프");
        sad[4] = input.indexOf("힘들");
        sad[5] = input.indexOf("힘든");
        sad[6] = input.indexOf("힘드");
        sad[7] = input.indexOf("좋냐");
        sad[8] = input.indexOf("우울");
        sad[9] = input.indexOf("무서");
        sad[10] = input.indexOf("피곤");
        sad[11] = input.indexOf("지친");
        sad[12] = input.indexOf("지쳐");
        sad[13] = input.indexOf("졸립");
        sad[14] = input.indexOf("졸리");
        sad[15] = input.indexOf("짜증");
        sad[16] = input.indexOf("심각");


        while (happy[0] != -1) {
            happy[0] = input.indexOf("기쁘", happy[0] + 1);
            counthappy++;
        }
        while (happy[1] != -1) {
            happy[1] = input.indexOf("기쁜", happy[1] + 1);
            counthappy++;
        }
        while (happy[2]!= -1) {
            happy[2] = input.indexOf("즐거", happy[2] + 1);
            counthappy++;
        }
        while (happy[3] != -1) {
            happy[3] = input.indexOf("즐겁", happy[3] + 1);
            counthappy++;
        }
        while (happy[4] != -1) {
            happy[4] = input.indexOf("행복", happy[4] + 1);
            counthappy++;
        }
        while (happy[5] != -1) {
            happy[5] = input.indexOf("보람", happy[5] + 1);
            counthappy++;
        }
        while (happy[6] != -1) {
            happy[6] = input.indexOf("좋은", happy[6] + 1);
            counthappy++;
        }
        while (happy[7] != -1) {
            happy[7] = input.indexOf("좋아", happy[7] + 1);
            counthappy++;
        }
        while (happy[8] != -1) {
            happy[8] = input.indexOf("좋다", happy[8] + 1);
            counthappy++;
        }
        while (happy[9] != -1) {
            happy[9] = input.indexOf("뿌듯", happy[9] + 1);
            counthappy++;
        }
        while (happy[10] != -1) {
            happy[10] = input.indexOf("기뻐", happy[10] + 1);
            counthappy++;
        }
        while (happy[11] != -1) {
            happy[11] = input.indexOf("흥미", happy[11] + 1);
            counthappy++;
        }
        while (happy[12] != -1) {
            happy[12] = input.indexOf("따뜻", happy[12] + 1);
            counthappy++;
        }
        while (happy[13] != -1) {
            happy[13] = input.indexOf("친절", happy[13] + 1);
            counthappy++;
        }
        while (happy[14] != -1) {
            happy[14] = input.indexOf("오늘", happy[14] + 1);
            counthappy++;
        }
        while (happy[15] != -1) {
            happy[15] = input.indexOf("괜찮", happy[15] + 1);
            counthappy++;
        }
        while (happy[16] != -1) {
            happy[16] = input.indexOf("살고", happy[16] + 1);
            counthappy++;
        }

        while (sad[0] != -1) {
            sad[0] = input.indexOf("슬피", sad[0] + 1);
            countsad++;
        }
        while (sad[1] != -1) {
            sad[1] = input.indexOf("슬픈", sad[1] + 1);
            countsad++;
        }
        while (sad[2] != -1) {
            sad[2] = input.indexOf("슬퍼", sad[2] + 1);
            countsad++;
        }
        while (sad[3] != -1) {
            sad[3] = input.indexOf("슬프", sad[3] + 1);
            countsad++;
        }
        while (sad[4] != -1) {
            sad[4] = input.indexOf("힘들", sad[4] + 1);
            countsad++;
        }
        while (sad[5] != -1) {
            sad[5] = input.indexOf("힘든", sad[5] + 1);
            countsad++;
        }
        while (sad[6] != -1) {
            sad[6] = input.indexOf("힘드", sad[6] + 1);
            countsad++;
        }
        while (sad[7] != -1) {
            sad[7] = input.indexOf("좋냐", sad[7]+ 1);
            countsad++;
        }
        while (sad[8] != -1) {
            sad[8] = input.indexOf("우울", sad[8]+ 1);
            countsad++;
        }
        while (sad[9] != -1) {
            sad[9] = input.indexOf("무서", sad[9]+ 1);
            countsad++;
        }
        while (sad[10] != -1) {
            sad[10] = input.indexOf("피곤", sad[10]+ 1);
            countsad++;
        }
        while (sad[11] != -1) {
            sad[11] = input.indexOf("지친", sad[11]+ 1);
            countsad++;
        }
        while (sad[12] != -1) {
            sad[12] = input.indexOf("지쳐", sad[12]+ 1);
            countsad++;
        }
        while (sad[13] != -1) {
            sad[13] = input.indexOf("졸립", sad[13]+ 1);
            countsad++;
        }
        while (sad[14] != -1) {
            sad[14] = input.indexOf("졸리", sad[14]+ 1);
            countsad++;
        }
        while (sad[15] != -1) {
            sad[15] = input.indexOf("짜증", sad[15]+ 1);
            countsad++;
        }
        while (sad[16] != -1) {
            sad[16] = input.indexOf("심각", sad[16]+ 1);
            countsad++;
        }

        return counthappy - countsad;
    }

    public static String TagEmotion(String input){
        String a = "";
        int check = analyzeemotion(input);

        int[] happy = new int[100];
        for(int i = 0;i<100;i++) happy[i] = 0;
        int[] sad = new int[100];
        for(int i = 0;i<100;i++) sad[i] = 0;

        happy[0] = input.indexOf("기쁘");
        happy[1] = input.indexOf("기쁜");
        happy[2]= input.indexOf("즐거");
        happy[3] = input.indexOf("즐겁");
        happy[4] = input.indexOf("행복");
        happy[5] = input.indexOf("보람");
        happy[6] = input.indexOf("좋은");
        happy[7] = input.indexOf("좋아");
        happy[8] = input.indexOf("좋다");
        happy[9] = input.indexOf("뿌듯");
        happy[10] = input.indexOf("기뻐");
        happy[11] = input.indexOf("흥미");
        happy[12] = input.indexOf("따뜻");
        happy[13] = input.indexOf("친절");
        happy[14]= input.indexOf("오늘");
        happy[15] = input.indexOf("괜찮");
        happy[16] = input.indexOf("살고");

        sad[0] = input.indexOf("슬피");
        sad[1] = input.indexOf("슬픈");
        sad[2] = input.indexOf("슬퍼");
        sad[3] = input.indexOf("슬프");
        sad[4] = input.indexOf("힘들");
        sad[5] = input.indexOf("힘든");
        sad[6] = input.indexOf("힘드");
        sad[7] = input.indexOf("좋냐");
        sad[8] = input.indexOf("우울");
        sad[9] = input.indexOf("무서");
        sad[10] = input.indexOf("피곤");
        sad[11] = input.indexOf("지친");
        sad[12] = input.indexOf("지쳐");
        sad[13] = input.indexOf("졸립");
        sad[14] = input.indexOf("졸리");
        sad[15] = input.indexOf("짜증");
        sad[16] = input.indexOf("심각");

        if(check>0){
            for(int i =0 ; i<17 ; i++){
                if(happy[i] != 0 ){
                    a = input.substring(happy[i]+1, happy[i]+2);
                }
            }
        }else if(check<0){
            for(int i =0 ; i<17 ; i++){
                if(sad[i] != 0 ){
                    a = input.substring(sad[i]+1, sad[i]+2);
                }
            }
        }
        return a;
    }


    /*String[] analy;
        int count = input.indexOf(" ");
        int max = 0;
        while (count != -1) {
            count = input.indexOf(" ", count + 1);
            max++;
        }
        int first;
        int last = 0;
        analy = new String[max];
        count = input.indexOf(" ");
        for (int i = 0; i < max; i++) {
            count = input.indexOf(" ", count + 1);
            first = last + 1;
            last = count;
            analy[i] = input.substring(first, last);
        }*/

}
