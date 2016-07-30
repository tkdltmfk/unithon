package com.unithon.gamsung;

/**
 * Created by kyun on 2016-07-30.
 */
public class AnalyzeEmotion {

    public static int analyzeemotion(String input) {
        int counthappy = 0;
        int countsad = 0;

        int happy1 = input.indexOf("기쁘");
        int happy2 = input.indexOf("기쁜");
        int happy3 = input.indexOf("즐거");
        int happy4 = input.indexOf("즐겁");
        int happy5 = input.indexOf("행복");
        int happy6 = input.indexOf("보람");
        int happy7 = input.indexOf("좋은");
        int happy8 = input.indexOf("좋아");
        int happy9 = input.indexOf("좋다");
        int sad1 = input.indexOf("슬피");
        int sad2 = input.indexOf("슬픈");
        int sad3 = input.indexOf("슬퍼");
        int sad4 = input.indexOf("슬프");
        int sad5 = input.indexOf("힘들");
        int sad6 = input.indexOf("힘든");
        int sad7 = input.indexOf("힘드");
        int sad8 = input.indexOf("좋냐");

        while (happy1 != -1) {
            happy1 = input.indexOf("기쁘", happy1 + 1);
            counthappy++;
        }
        while (happy2 != -1) {
            happy2 = input.indexOf("기쁜", happy2 + 1);
            counthappy++;
        }
        while (happy3 != -1) {
            happy3 = input.indexOf("즐거", happy3 + 1);
            counthappy++;
        }
        while (happy4 != -1) {
            happy4 = input.indexOf("즐겁", happy4 + 1);
            counthappy++;
        }
        while (happy5 != -1) {
            happy5 = input.indexOf("행복", happy5 + 1);
            counthappy++;
        }
        while (happy6 != -1) {
            happy6 = input.indexOf("보람", happy6 + 1);
            counthappy++;
        }
        while (happy7 != -1) {
            happy7 = input.indexOf("좋은", happy7 + 1);
            counthappy++;
        }
        while (happy8 != -1) {
            happy8 = input.indexOf("좋아", happy8 + 1);
            counthappy++;
        }
        while (happy9 != -1) {
            happy9 = input.indexOf("좋다", happy9 + 1);
            counthappy++;
        }

        while (sad1 != -1) {
            sad1 = input.indexOf("슬피", sad1 + 1);
            countsad++;
        }
        while (sad2 != -1) {
            sad2 = input.indexOf("슬픈", sad2 + 1);
            countsad++;
        }
        while (sad3 != -1) {
            sad3 = input.indexOf("슬퍼", sad3 + 1);
            countsad++;
        }
        while (sad4 != -1) {
            sad4 = input.indexOf("슬프", sad4 + 1);
            countsad++;
        }
        while (sad5 != -1) {
            sad5 = input.indexOf("힘들", sad5 + 1);
            countsad++;
        }
        while (sad6 != -1) {
            sad6 = input.indexOf("힘든", sad6 + 1);
            countsad++;
        }
        while (sad7 != -1) {
            sad7 = input.indexOf("힘드", sad7 + 1);
            countsad++;
        }
        while (sad8 != -1) {
            sad8 = input.indexOf("좋냐냐", sad8 + 1);
            countsad++;
        }

        return counthappy - countsad;
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
