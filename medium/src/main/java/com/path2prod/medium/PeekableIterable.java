package com.path2prod.medium;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class PeekableIterable<T> implements Iterable<T> {

    Iterable<T> source;

    public PeekableIterable(Iterable<T> source){
        this.source = source;
    }


    @Override
    public PeekableIterator iterator(){
        return new PeekableIteratorImpl(source);
    }

    public interface PeekableIterator<T> extends Iterator<T>{
        Optional<T> peek();
    }

    private class PeekableIteratorImpl implements PeekableIterator<T> {
        Iterator<T> sourceIterator;
        List<T> peekElements = new ArrayList<>();

        public PeekableIteratorImpl(Iterable<T> source){
            sourceIterator = source.iterator();
        }



        @Override
        public boolean hasNext() {
            return sourceIterator.hasNext() || !peekElements.isEmpty();
        }

        @Override
        public T next() {
            if (!hasNext()){
                throw new NoSuchElementException("end");
            }

            if (!peekElements.isEmpty()){
                return peekElements.remove(0);
            }

            return sourceIterator.next();
        }

        public Optional<T> peek(){
            if (!sourceIterator.hasNext()){
                return Optional.empty();
            }
            
            T current = sourceIterator.next();
            peekElements.add(current);
            return Optional.of(current);

        }
    }
}


