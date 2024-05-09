package com.example.dictionaryapp;

public class DB {
    public static String[] getData(int id) {
        if (id == R.id.action_en_vi) {
            return getEnVi();
        } else if (id == R.id.action_vi_en) {
            return getViEn();
        }

        return new String[0];
    }

    public static String[] getEnVi()
    {
        String[] source = new String[]
                {
                        "a","A","aab","aabc","b","bbc","c","d","e","f","g"
                        ,"h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"
                };
        return  source;
    }

    public static  String[] getViEn()
    {
        String[] source = new  String[]
                {
                        "a","ă","â","bờ","cờ","dờ","đờ","e","ê","gờ","hờ","nờ","mờ","i","k","ka","l","lờ","lol"
                };
        return source;
    }
}
