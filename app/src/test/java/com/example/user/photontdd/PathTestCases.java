package com.example.user.photontdd;


import com.example.user.photontdd.utils.FindPath;
import com.example.user.photontdd.utils.GridMatrix;
import com.example.user.photontdd.utils.GridPath;
import com.example.user.photontdd.utils.RowCost;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by tSravani on 5/14/2017.
 */

public class PathTestCases {
    @Test
    public void testTrivial() {
        GridMatrix pathMatrix = new GridMatrix();

        List<Integer> firstColumn = new ArrayList<Integer>();
        firstColumn.add(1);
        pathMatrix.addColumn(firstColumn);
        FindPath tester = new FindPath(pathMatrix);
        GridPath derivedPath = tester.findPath();
        RowCost testStep = new RowCost();
        testStep.setCost(1);
        testStep.setRow(1);
        GridPath testPath = new GridPath(pathMatrix);
        testPath.add(testStep);
        assertTrue(tester.terminates(derivedPath));
        assertTrue(derivedPath.equals(testPath));
        assertTrue(derivedPath.getCost() == 1);
    }

    @Test
    public void testRow() {
        GridMatrix pathMatrix = new GridMatrix();

        for (int i = 0; i < 5; i++) {
            List<Integer> column = new ArrayList<Integer>();
            column.add(1);
            pathMatrix.addColumn(column);
        }

        FindPath tester = new FindPath(pathMatrix);
        GridPath derivedPath = tester.findPath();

        GridPath testPath = new GridPath(pathMatrix);
        for (int i = 0; i < 5; i++) {
            RowCost step = new RowCost();
            step.setRow(1);
            step.setCost(1);
            testPath.add(step);
        }

        assertTrue(derivedPath.getCost() == testPath.getCost());

        for (int i = 0; i < 5; i++) {
            assertTrue(derivedPath.get(i).getRow() == testPath.get(i).getRow());
        }

    }





    @Test
    public void testColumn() {
        GridMatrix pathMatrix = new GridMatrix();

        List<Integer> listStep = new ArrayList<Integer>();

        listStep.add(5);
        listStep.add(8);
        listStep.add(5);
        listStep.add(3);
        listStep.add(1);

        pathMatrix.addColumn(listStep);

        FindPath finder = new FindPath(pathMatrix);

        GridPath derivedPath = finder.findPath();

        assertTrue(derivedPath.getCost() == 3);
    }

    @Test
    public void testOverrun() {
        GridMatrix pathMatrix = new GridMatrix();

        List<Integer> list1 = new ArrayList<Integer>();

        list1.add(69);
        list1.add(51);
        list1.add(60);

        pathMatrix.addColumn(list1);

        FindPath tester = new FindPath(pathMatrix);

        GridPath derivedPath = tester.findPath();

        assertTrue(derivedPath.getCost() == 0);
        assertTrue(derivedPath.size() == 0);

    }

    @Test
    public void testNoPath() {
        GridMatrix pathMatrix = new GridMatrix();

        List<Integer> list1 = new ArrayList<Integer>();

        list1.add(19);
        list1.add(21);
        list1.add(20);

        List<Integer> list2 = new ArrayList<Integer>();

        list2.add(10);
        list2.add(23);
        list2.add(12);

        List<Integer> list3 = new ArrayList<Integer>();

        list3.add(19);
        list3.add(20);
        list3.add(20);

        List<Integer> list4 = new ArrayList<Integer>();

        list4.add(10);
        list4.add(19);
        list4.add(11);

        pathMatrix.addColumn(list1);
        pathMatrix.addColumn(list2);
        pathMatrix.addColumn(list3);
        pathMatrix.addColumn(list4);

        FindPath tester = new FindPath(pathMatrix);

        GridPath derivedPath = tester.findPath();

        assertTrue(derivedPath.getCost() == 48);
        assertTrue(derivedPath.size() == 3);
    }

    @Test
    public void testNegativePath() {
        GridMatrix pathMatrix = new GridMatrix();

        List<Integer> list1 = new ArrayList<Integer>();

        list1.add(6);
        list1.add(-5);
        list1.add(3);
        list1.add(9);


        List<Integer> list2 = new ArrayList<Integer>();

        list2.add(3);
        list2.add(2);
        list2.add(-2);
        list2.add(-1);


        List<Integer> list3 = new ArrayList<Integer>();

        list3.add(-5);
        list3.add(4);
        list3.add(6);
        list3.add(-2);

        List<Integer> list4 = new ArrayList<Integer>();

        list4.add(9);
        list4.add(10);
        list4.add(10);
        list4.add(10);


        pathMatrix.addColumn(list1);
        pathMatrix.addColumn(list2);
        pathMatrix.addColumn(list3);
        pathMatrix.addColumn(list4);

        FindPath tester = new FindPath(pathMatrix);

        GridPath derivedPath = tester.findPath();

        assertTrue(derivedPath.getCost() == 0);
        assertTrue(derivedPath.size() == 4);
        assertTrue(derivedPath.get(0).getRow() == 2);
        assertTrue(derivedPath.get(1).getRow() == 3);
        assertTrue(derivedPath.get(2).getRow() == 4);
        assertTrue(derivedPath.get(3).getRow() == 1);
    }


}
