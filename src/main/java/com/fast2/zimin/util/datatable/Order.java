package com.fast2.zimin.util.datatable;

/**
 * Created by ez2sarang on 2014. 11. 11..
 */
public class Order {
    /**
     * column index
     * start:0
     * endstart:-1
     */
    private int column;
    private String dir;

    public Order() {
    }

    public Order(int column, String dir) {
        this.column = column;
        this.dir = dir;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    @Override
    public String toString() {
        return "Order{" +
                "column=" + column +
                ", dir='" + dir + '\'' +
                '}';
    }
}
