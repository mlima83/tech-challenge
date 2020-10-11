package com.pixeon.base.utils;

import org.springframework.stereotype.Component;

@Component
public class Utils {

    public String unaccent(String text) {
        String newText = "";
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            String replacement = replaceVowel(c);
            newText += replacement;
        }

        return newText;
    }
    public String replaceVowel(char initialChar) {
        String replacement = replaceVowel(initialChar,"a","á|à|â|ã|ä|å|ā|ă|ą|À|Á|Â|Ã|Ä|Å|Ā|Ă|Ą|Æ".split("|"));
        if (replacement == null) {
            replacement = replaceVowel(initialChar,"e","è|é|ê|ё|ë|ē|ĕ|ė|ę|ě|È|Ê|Ë|Ё|Ē|Ĕ|Ė|Ę|Ě|€".split("|"));
        }
        if (replacement == null) {
            replacement = replaceVowel(initialChar,"i","ı|ì|í|î|ï|ì|ĩ|ī|ĭ|Ì|Í|Î|Ï|Ї|Ì|Ĩ|Ī|Ĭ".split("|"));
        }
        if (replacement == null) {
            replacement = replaceVowel(initialChar,"o","ò|ó|ô|õ|ö|ō|ŏ|ő|ø|Ò|Ó|Ô|Õ|Ö|Ō|Ŏ|Ő|Ø|Œ".split("|"));
        }
        if (replacement == null) {
            replacement = replaceVowel(initialChar,"u","ù|ú|û|ü|ũ|ū|ŭ|ů|Ù|Ú|Û|Ü|Ũ|Ū|Ŭ|Ů".split("|"));
        }
        if (replacement == null) {
            replacement = new String(new char[]{initialChar});
        }
        return replacement;
    }

    public String replaceVowel(char initialChar, String charResult, String[] validate) {
        for (String val : validate) {
            if (initialChar == val.charAt(0)) {
                return charResult;
            }
        }
        return null;
    }
    
    public String transformUnaccentAndCaseInsensitive(String value) {
    	return unaccent(value.toLowerCase());
    }
}
