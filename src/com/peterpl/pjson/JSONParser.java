package com.peterpl.pjson;

import com.peterpl.pjson.syntax.*;
import com.peterpl.pjson.syntax.handler.complexType.*;
import com.peterpl.pjson.syntax.handler.simpleType.*;

import java.util.*;

public class JSONParser {
    private JSONObjectHandler objParser = new JSONObjectHandler(this);
    private JSONArrayHandler arrParser = new JSONArrayHandler(this);

    private int[][] strings, objectBlocks, arrayBlocks;
    private JSONUtils utils = new JSONUtils();

    public JSONValue parse(String json) {
        json = compressJSON(json);
        updateBlocks(json);

        return parseJSONValue(json);
    }

    public JSONValue parseJSONValue(String json) {
        IntegerHandler integerParser = new IntegerHandler();
        FloatHandler floatParser = new FloatHandler();
        BooleanHandler booleanParser = new BooleanHandler();
        StringHandler stringParser = new StringHandler();
        NullHandler nullParser = new NullHandler();

        if(objParser.is(json)) return objParser.parse(json);
        if(arrParser.is(json)) return arrParser.parse(json);

        if(integerParser.is(json)) return integerParser.parse(json);
        if(floatParser.is(json)) return floatParser.parse(json);
        if(booleanParser.is(json)) return booleanParser.parse(json);
        if(stringParser.is(json)) return stringParser.parse(json);
        if(nullParser.is(json)) return nullParser.parse();

        throw new JSONException();
    }

    public void updateBlocks(String json) {
        strings = findStrings(json);

        objectBlocks = findBlocks(json, '{', '}');
        arrayBlocks = findBlocks(json, '[', ']');
    }

    public int findBlock(int blockBegin, char bracket) {
        int[][] array = bracket == '{' ? objectBlocks : arrayBlocks;

        for(int[] block : array) {
            if(block[0] == blockBegin + 1) {
                return block[1];
            }
        }
        return -1;
    }

    public int findNext(String haystack, char needle) {
        ArrayList<Integer> indexes = utils.allIndexesOf(haystack, needle);

        for(int index : indexes) {
            if(!isString(index)) {
                return index;
            }
        }
        return -1;
    }

    private boolean isString(int charIndex) {
        for(int[] str : strings) {
            if(charIndex >= str[0] && charIndex <= str[1]) {
                return true;
            }
        }
        return false;
    }

    private String compressJSON(String json) {
        final Character[] WHITE_SPACES = {' ', '\n', '\t'};
        strings = findStrings(json);

        String newJson = "";
        for(int i = 0; i < json.length(); i++) {
            char ch = json.charAt(i);
            if(utils.indexOf(WHITE_SPACES, ch) != -1) {
                boolean isString = false;
                for(int[] str : strings) {
                    if(i >= str[0] && i <= str[1]) {
                        isString = true;
                        break;
                    }
                }
                if(isString) {
                    newJson += ch;
                }
                continue;
            }
            newJson += ch;
        }
        return newJson;
    }

    public int[][] findBlocks(String json, char openSeparator, char closeSeparator) {
        ArrayList<int[]> blocksArray = new ArrayList<>();

        ArrayList<Integer> openSeparatorIndexes = utils.allIndexesOf(json, openSeparator);
        ArrayList<Integer> closeSeparatorIndexes = utils.allIndexesOf(json, closeSeparator);

        if(openSeparatorIndexes.isEmpty() || closeSeparatorIndexes.isEmpty()) {
            return utils.listToArray(blocksArray);
        }

        int first = openSeparatorIndexes.get(0);
        int last = closeSeparatorIndexes.get(closeSeparatorIndexes.size() - 1);

        ArrayList<Integer> bufferOpenSeparators = new ArrayList<>();

        for(int i = first; i <= last; i++) {
            boolean isOpen = openSeparatorIndexes.contains(i);
            boolean isClose = closeSeparatorIndexes.contains(i);

            if((isOpen || isClose) && !isString(i)) {
                if(isOpen) {
                    bufferOpenSeparators.add(i);
                } else {
                    int lastIndex = bufferOpenSeparators.size() - 1;

                    int[] block = {bufferOpenSeparators.get(lastIndex) + 1, i};
                    blocksArray.add(block);

                    bufferOpenSeparators.remove(lastIndex);
                }
            }
        }
        return utils.listToArray(blocksArray);
    }

    public int[][] findStrings(String json) {
        ArrayList<int[]> stringsList =  new ArrayList<>();
        ArrayList<Integer> quotes = utils.allIndexesOf(json, '\"');

        while(!quotes.isEmpty()) {
            int quote1 = quotes.get(0);
            int quote2 = quotes.get(1);

            String str = json.substring(quote1 + 1, quote2);
            if(new StringHandler().is("\"" + str + "\"")) {
                int[] strBounds = {quote1 + 1, quote2};
                stringsList.add(strBounds);
                quotes.remove(0);
            }
            quotes.remove(0);
        }
        return utils.listToArray(stringsList);
    }
}
