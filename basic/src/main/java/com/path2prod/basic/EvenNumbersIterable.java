package com.path2prod.basic;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterable implements Iterable<Integer> {

    private int start;
    private int end;

    public EvenNumbersIterable(int start, int end){
        this.start = start;
        this.end = end;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            private int current = (start % 2 == 0) ? start : start + 1;


            @Override
            public boolean hasNext() {
                return current <= end;
            }

            @Override
            public Integer next() {
                if (!hasNext()){
                    throw new NoSuchElementException("no more numbers");
                }

                    
                int even = current;
                current+=2;
                return even;
            }
            
        };
    }

}
