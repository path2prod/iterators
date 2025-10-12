package com.path2prod.medium;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class CicleIterable<T> implements Iterable<T>{
    private boolean reverseMode;
    private int limit;
    private Iterable<T> source;

    public CicleIterable(Iterable<T> source, boolean reverseMode, int limit){
        this.source = source;
        this.reverseMode = reverseMode;
        this.limit = limit;
    }

    @Override
    public Iterator<T> iterator() {
        return new CircleIterator();
    }


    private class CircleIterator implements Iterator<T>{
        int currentCycle = 0;
        int currentIndex = 0;
        List<T> sourceList = new ArrayList<>();

        public CircleIterator(){
            
            source.forEach(sourceList::add);

            if (reverseMode)
                sourceList = sourceList.reversed();
        }

        public boolean hasNext(){
           return currentCycle < limit;
        }

        
        public T next(){

            if (!hasNext()){
                throw new NoSuchElementException();
            }

            T element = sourceList.get(currentIndex);
            currentIndex++;

            if (currentIndex == sourceList.size()) {
                currentIndex = 0;
                currentCycle++;
            }

            return element;
         }

    }
}

