package com.example.user.photontdd.utils;

import java.util.ArrayList;

/**
 * Created by tSravani on 5/14/2017.
 */

public class GridPath extends ArrayList<RowCost>{
    private GridMatrix pathMatrix;

    public GridPath(GridMatrix pathMatrix) {
        this.pathMatrix = pathMatrix;
    }

    @Override
    public GridPath clone() {
        GridPath solutionPath = new GridPath(pathMatrix);
        for (RowCost step : this) {

                solutionPath.add(step.clone());

        }
        return solutionPath;
    }

    public int getCost() {
        //Creates a 0 value accumulator. If there are no elements a path value of 0 is returned
        int accumulator = 0;
        for (RowCost step: this) {
            accumulator += step.getCost();
        }

        return accumulator;
    }

    public boolean terminates() {
        return this.size() == pathMatrix.getWidth();
    }


}
