package com.path2prod.basic;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class NumberRange implements Iterable<Integer> {
    int start;
    int end;

    public NumberRange(int start, int end){
        if (start > end) {
            throw new IllegalArgumentException("initial number cannot be greater than final number");
        }

        this.start = start;
        this.end = end;
    }

    @Override
    public Iterator<Integer> iterator() {
      return new NumberRangeIterator(start,end);
    }

}

class NumberRangeIterator implements Iterator<Integer> {
    int current;
    int end;

    public NumberRangeIterator(int current, int end){
        this.current = current;
        this.end = end;
    }

    @Override
    public boolean hasNext() {
        return current <= end;
    }

    @Override
    public Integer next() {
        if (!hasNext()){
            throw new NoSuchElementException("no more numbers");
        }
        
        return current++;
    }

}

