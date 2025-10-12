package com.path2prod.medium;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class MultiList<T>  {

    List<List<? extends T>> lists = new ArrayList<>();


    public Iterable<T> getSequentialIterator(){
        return () -> new SequentialIterator<T>(lists);
    }

    public Iterable<T> getOddIterator(){
        return () -> new OddIterator<T>(lists);
    }

    public Iterable<T> getEvenIterator(){
        return () -> new EvenIterator<T>(lists);
    }


}

class SequentialIterator<T> implements Iterator<T>{

    int currentListIndex=0;
    int currentElementIndex=0;
    List<List<? extends T>> lists;

    public SequentialIterator(List<List<? extends T>> lists){
        this.lists = lists;
    }


    @Override
    public boolean hasNext() {
        return this.lists.size() > currentListIndex && this.lists.get(currentListIndex).size() > currentElementIndex;
    }

    @Override
    public T next() {
       
        if (!hasNext()){
            throw new NoSuchElementException("no more elements");
        }

        T currentElement = this.lists.get(currentListIndex).get(currentElementIndex);


        if (this.lists.get(currentListIndex).size() -1 == currentElementIndex){
            currentListIndex++;
            currentElementIndex=0;
        }else{
            currentElementIndex++;
        }

        return currentElement;
    }

}


class OddIterator<T> implements Iterator<T>{

    int currentListIndex=1;
    int currentElementIndex=0;

    List<List<? extends T>> lists;

    public OddIterator(List<List<? extends T>> lists){
        this.lists = lists;
    }


    @Override
    public boolean hasNext() {
        return this.lists.size() > currentListIndex && this.lists.get(currentListIndex).size() > currentElementIndex;
    }

    @Override
    public T next() {
        if (!hasNext()){
            throw new NoSuchElementException("no more elements");
        }

        T currentElement = this.lists.get(currentListIndex).get(currentElementIndex);

        if (this.lists.get(currentListIndex).size() - 1 == currentElementIndex){
            currentListIndex+=2;
            currentElementIndex=0;
        }else{
            currentElementIndex++;
        
        }

        return currentElement;
    }

}


class EvenIterator<T> implements Iterator<T>{

    int currentListIndex=0;
    int currentElementIndex=0;
    List<List<? extends T>> lists;

    public EvenIterator(List<List<? extends T>> lists){
        this.lists = lists;
    }



    @Override
    public boolean hasNext() {
        return this.lists.size() > currentListIndex && this.lists.get(currentListIndex).size() > currentElementIndex;
    }

    @Override
    public T next() {
        if (!hasNext()){
            throw new NoSuchElementException("no more elements");
        }

        T currentElement = this.lists.get(currentListIndex).get(currentElementIndex);

        if (this.lists.get(currentListIndex).size() - 1 == currentElementIndex){
            currentListIndex+=2;
            currentElementIndex=0;
        }else{
            currentElementIndex++;
        
        }
        
        return currentElement;
    }

}
