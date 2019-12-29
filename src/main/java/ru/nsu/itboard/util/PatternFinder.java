package ru.nsu.itboard.util;

import java.util.regex.Pattern;

public class PatternFinder {
    public static boolean containsIgnoreCase(String in, String toFind){
        return Pattern.compile(Pattern.quote(toFind), Pattern.CASE_INSENSITIVE).matcher(in).find();
    }
}
