package ru.job4j.it;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        int[] temp = new int[] {};
        while (Arrays.equals(data[row], temp)) {
            if (row + 1 < data.length) {
                row++;
            } else {
                break;
            }
        }
        return column < data[row].length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int rsl = data[row][column++];
        if (column == data[row].length) {
            row++;
            column = 0;
        }
        return rsl;
    }
}
