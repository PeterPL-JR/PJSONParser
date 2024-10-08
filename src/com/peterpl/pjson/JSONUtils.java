package com.peterpl.pjson;

import java.util.*;

public class JSONUtils {
    public <T> int indexOf(T[] haystack, T needle) {
        for(int i = 0; i < haystack.length; i++) {
            if(haystack[i].equals(needle)) {
                return i;
            }
        }
        return -1;
    }
    public <K, T> K keyOf(HashMap<K, T> haystack, T needle) {
        for(K key : haystack.keySet()) {
            if(haystack.get(key).equals(needle)) {
                return key;
            }
        }
        return null;
    }

    public ArrayList<Integer> allIndexesOf(String haystack, char needle) {
        ArrayList<Integer> indexesList = new ArrayList<>();
        String str = haystack;
        int offset = 0;

        while(!str.isEmpty()) {
            int index = str.indexOf(needle);

            if(index == -1) break;
            indexesList.add(index + offset);

            if(index + 1 >= str.length()) break;
            str = str.substring(index + 1);
            offset += index + 1;
        }
        return indexesList;
    }

    public int[][] listToArray(ArrayList<int[]> arrayList) {
        int[][] array = new int[arrayList.size()][];
        for(int i = 0; i < arrayList.size(); i++) {
            array[i] = new int[arrayList.get(i).length];
            for(int j = 0; j < array[i].length; j++) {
                array[i][j] = arrayList.get(i)[j];
            }
        }
        return array;
    }
}
