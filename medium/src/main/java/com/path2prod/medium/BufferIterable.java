package com.path2prod.medium;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class BufferIterable<T> implements Iterable<T> {

    Collection<T> source;
    int maxBuffer;

    public BufferIterable(Collection<T> source, int maxBuffer) {
        this.source = source;
        this.maxBuffer = Math.min(maxBuffer, source.size());
    }

    @Override
    public BufferIterator<T> iterator() {
        return new BufferIteratorImpl();
    }

    public interface BufferIterator<T> extends Iterator<T>{
        T previous();
    }

    private class BufferIteratorImpl implements BufferIterator<T> {
        List<T> buffer = new ArrayList<>();
        int currentBufferIndex = -1;
        Iterator<T> sourceIterator = source.iterator();

        

        @Override
        public boolean hasNext() {
           return sourceIterator.hasNext();
        }

        @Override
        public T next() {
           if (!hasNext()){
            throw new NoSuchElementException("end");
           }

           T current = sourceIterator.next();
           
           if (buffer.size() < maxBuffer){
            buffer.add(current);
            currentBufferIndex = buffer.size()-1;
           }

           return current;
        }

        @Override
        public T previous() {
            if (currentBufferIndex < 0){
               return null;
            }
            
            return buffer.get(currentBufferIndex--);
        }
    
    }
}
