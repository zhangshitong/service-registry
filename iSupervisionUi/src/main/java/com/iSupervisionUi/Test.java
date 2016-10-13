package com.iSupervisionUi;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by stzhang on 2016/10/13.
 */
public class Test {
    public static void main(String[] args){
        String s = "https://supervision-service.apps.iisipcf.io:80/srv/index";
        s = s.replaceAll(":80", "");
        System.out.println(s);


        String pat = "*.apps.iisipcf.io";
        pat = pat.replaceAll("\\.", "\\\\.");
        pat = pat.replaceAll("\\*", "\\.\\*");
        System.out.println(pat);


        Pattern p = Pattern.compile(pat+"");
        Matcher m = p.matcher("supervision-service.apps.iisipcf.io");
        System.out.println(m.matches());

    }
}
