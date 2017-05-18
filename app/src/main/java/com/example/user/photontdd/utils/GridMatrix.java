package com.example.user.photontdd.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tSravani on 5/14/2017.
 */

public class GridMatrix {


    private List<List<Integer>> list = new ArrayList<List<Integer>>();

    public void addColumn(List<Integer> li) {
        list.add(li);
    }

    public int getWidth() {
        return list.size();
    }

    public int getHeight() {
        return list.get(0).size();
    }


     public int getValue(int x, int y) {
        return list.get(x).get(y);

    }

}
