package com.path2prod.medium;

import java.util.Iterator;
import java.util.function.Supplier;

public class LazyIterator<T> implements Iterator<T>{

    private final Supplier<T> supplier;

    public LazyIterator(Supplier<T> supplier){
        this.supplier = supplier;
    }


    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public T next() {
        return supplier.get();
    }

}
