package com.example.user.photontdd.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by tSravani on 5/14/2017.
 */
public class FindPath {

    private GridMatrix mGridMatrix;

    public FindPath(GridMatrix mGridMatrix) {
        this.mGridMatrix = mGridMatrix;
    }

    public FindPath(int col[][], int w, int h) {
        GridMatrix mMatrix = new GridMatrix();
        for (int i = 0; i < w; i++) {
            List<Integer> column = new ArrayList<Integer>();
            for (int j = 0; j < h; j++) {
                column.add(col[i][j]);
            }
            mMatrix.addColumn(column);
        }
        mGridMatrix = mMatrix;
     }

    public GridPath findPath() {

        List<GridPath> listOfPaths = new ArrayList<GridPath>();

        for (int i = 0; i < mGridMatrix.getHeight(); i++) {
            // goes down 1st row
            listOfPaths.add(iterate(new GridPath(mGridMatrix), 0, i));
        }

        return getBestPath(listOfPaths);

    }


    private GridPath iterate(GridPath path, int x, int y) {
        if (terminates(path))
            return path;

        //tries out current cell to be added. If it adds up to more than 50 then this path is returned.

        int test = path.getCost() + mGridMatrix.getValue(x, y);
        if (test > 50)
            return path;

        // If the current path isn't terminal, and doesn't add up to over 50, we add the current cell
        RowCost step = new RowCost();
        step.setRow(y + 1);
        step.setCost(mGridMatrix.getValue(x, y));
        path.add(step);

        if (terminates(path))
            return path;


        List<GridPath> listOfPaths = new ArrayList<GridPath>();

        // looks at the rows above, level, and below

        for (int i = -1; i < 2; i++) {
            listOfPaths.add(iterate(path.clone(), x + 1, normalizeIndex(y + i)));
        }

        return getBestPath(listOfPaths);
    }

    public boolean terminates(GridPath solutionPath) {
        return solutionPath.size() == mGridMatrix.getWidth();
    }

    private int normalizeIndex(int test) {
        if (test < 0)
            return mGridMatrix.getHeight() - 1;
        if (test > mGridMatrix.getHeight() - 1)
            return 0;
        return test;
    }

    private GridPath getBestPath(List<GridPath> solutionPathList) {
        if (solutionPathList != null && solutionPathList.size() > 0) {
            filterByLength(solutionPathList);
            filterByCost(solutionPathList);
            return solutionPathList.get(0);
        }

        return null;
    }

    private List<GridPath> filterByLength(List<GridPath> solutionPathList) {
        int longest = 0;

        for (GridPath solutionPath : solutionPathList) {
            if (solutionPath.size() > longest)
                longest = solutionPath.size();
        }

        Iterator it = solutionPathList.iterator();

        while (it.hasNext()) {
            if (((GridPath) it.next()).size() < longest)
                it.remove();
        }

        return solutionPathList;
    }

    private List<GridPath> filterByCost(List<GridPath> solutionPathList) {
        int lowest = 51;

        for (GridPath solutionPath : solutionPathList) {
            if (solutionPath.getCost() < lowest)
                lowest = solutionPath.getCost();
        }

        Iterator it = solutionPathList.iterator();

        while (it.hasNext()) {
            if (((GridPath) it.next()).getCost() > lowest)
                it.remove();
        }

        return solutionPathList;
    }

}
