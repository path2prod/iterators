package com.path2prod.basic;

import java.util.Iterator;
import java.util.NoSuchElementException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StringIterable implements Iterable<Character> {
    private String content;

    public StringIterable(String content){
        this.content = content.trim();
    }

    @Override
    public Iterator<Character> iterator() {
      
        return new Iterator<Character>() {
            int currentPosition = 0;

            @Override
            public boolean hasNext() {
               return currentPosition < content.length(); 
            }

            @Override
            public Character next() {

               if (!hasNext()){
                throw new NoSuchElementException("end of content");
               }

            while(hasNext() && Character.isWhitespace(content.charAt(currentPosition))){
                currentPosition++;
            }

            char currentChar = content.charAt(currentPosition);
            currentPosition++;
            return currentChar;
            }

        };
    }

}
