package com.example.user.photontdd.utils;

/**
 * Created by tSravani on 5/14/2017.
 */
public class RowCost {
    private int row;
    private int cost;

    @Override
    public RowCost clone(){
            RowCost step = new RowCost();
            step.setCost(this.getCost());
            step.setRow(this.getRow());
            return step;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RowCost)) return false;

        RowCost step = (RowCost) o;

        if (row != step.row) return false;
        return cost == step.cost;

    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + cost;
        return result;
    }
}
