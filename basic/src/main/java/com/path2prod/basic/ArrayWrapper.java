package com.path2prod.basic;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayWrapper<T> implements Iterable<T> {

    private T[] array;

    public ArrayWrapper(T[] array){
        this.array = array;
    }


    @Override
    public Iterator<T> iterator() {
       return new Iterator<T>() {

        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
           return array.length - 1 >= this.currentIndex;
        }

        @Override
        public T next() {
            if( !hasNext() ){
                throw new NoSuchElementException("end of array");
            }
            return array[currentIndex++];
        }
        
       };
    }

    public T get(int position){
        if (array.length - 1 < position || position < 0){
            throw new NoSuchElementException("invalid position");
        }
        
        return array[position];
    }

}
