package com.path2prod.medium;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class FilteredIterable<T> implements Iterable<T> {

    private Iterable<T> source;
    private Predicate<T> predicate;

    public FilteredIterable(Iterable<T> source, Predicate<T> predicate){
        this.source = source;
        this.predicate = predicate;
    }

    public FilteredIterable<T> and(Predicate<T> predicate){
        this.predicate = this.predicate.and(predicate);
        return this;
    }

    public FilteredIterable<T> or(Predicate<T> predicate){
        this.predicate = this.predicate.or(predicate);
        return this;
    }

    public FilteredIterable<T> not(){
        this.predicate = this.predicate.negate();
        return this;
    }



    @Override
    public Iterator<T> iterator() {
       return new FilteredIterator();
    }

    private class FilteredIterator implements Iterator<T>{
        T nextElement;
        boolean hasNext;
        private final Iterator<T> sourceIterator;


        public FilteredIterator(){ 
            this.sourceIterator = source.iterator();
            apply();
        }


        @Override
        public boolean hasNext() {
            return hasNext;

        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            T currentElement = nextElement;
            apply();
            return currentElement;
        }

        private void apply() {
            hasNext = false;
            while (sourceIterator.hasNext()) {
                T candidate = sourceIterator.next();
                if (predicate.test(candidate)) {
                    nextElement = candidate;
                    hasNext = true;
                    break;
                }
            }
        }
        
    }    
}
