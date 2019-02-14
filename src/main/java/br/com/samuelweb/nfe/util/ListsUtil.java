package br.com.samuelweb.nfe.util;

import java.util.ArrayList;
import java.util.List;

public class ListsUtil {

    public static <T> List<List<T>> partition(List<T> list, Integer size) {
        if (list == null || list.isEmpty()) {
            return null;
        }

        int listSize = list.size();
        int elements = size;
        List<List<T>> partitionList = new ArrayList<>();
        for (int i = 0; i < listSize; i = i + elements) {
            int toIndex = i + elements;
            if (toIndex > listSize) {
                toIndex = listSize;
            }
            partitionList.add(list.subList(i, toIndex));
        }
        return partitionList;
    }
}
