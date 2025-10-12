package com.path2prod.medium;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class MapIterable<T,R> implements Iterable<R> {

    private Iterable<T> source;
    private Function<T,R> fn;

    public MapIterable(Iterable<T> source, Function<T,R> fn){
        this.source = source;
        this.fn = fn;
    }


    @Override
    public Iterator<R> iterator() {
        return new TransformIterator();
    }

    private class TransformIterator implements Iterator<R>{
        Iterator<T> sourceIterator = source.iterator();

        @Override
        public boolean hasNext() {
            return sourceIterator.hasNext();
        }

        @Override
        public R next() {
            if (!hasNext()){
                throw new NoSuchElementException("end");
            }

            T input = sourceIterator.next(); 
            return fn.apply(input);
        }

    }

}
