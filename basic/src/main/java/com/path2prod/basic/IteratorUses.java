package com.path2prod.basic;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.PrimitiveIterator.OfInt;
import java.util.stream.IntStream;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class IteratorUses {

    public static void main(String[] args){
       exercise1();
       exercise2();
       exercise3();
       exercise4();
       exercise5();
       exercise6();
       exercise7();
       exercise8();
       exercise9();
    }

    static void exercise1(){
       log.info("Exercise 1"); 

       OfInt it = IntStream.rangeClosed(0, 10).iterator();
       it.forEachRemaining( (int n) -> log.info(String.valueOf(n)));
    }

    static void exercise2(){
       log.info("Exercise 2"); 

       int[] numbers = IntStream.rangeClosed(0, 10).toArray();
       for(int n : numbers){
        log.info(String.valueOf(n));
       }
    }

    static void exercise3(){
       log.info("Exercise 3");
       
       List<String> fruits = new ArrayList<>(List.of("Apple","Banana","Orange","Avocado","Strawberry"));
       Iterator<String> it = fruits.iterator();

       while (it.hasNext()) {
        if (it.next().toLowerCase().startsWith("b")){
            it.remove();
        }
       }
       
       it = fruits.iterator();
       it.forEachRemaining( s -> log.info(s));
    }

    static void exercise4(){
       log.info("Exercise 4");
       
       List<String> fruits = new ArrayList<>(List.of("Apple","Banana","Orange","Avocado","Strawberry"));

       try {
        for (String s : fruits) {
            if (s.toLowerCase().startsWith("b")){
                fruits.remove(s);
            }
       }
       } catch (ConcurrentModificationException e) {
            log.error("error",e);
       }
    }

    static void exercise5(){
        log.info("Exercise 5 - for loop");
        NumberRange range = new NumberRange(3, 7);
        for( int n: range){
            log.info(String.valueOf(n));
        }

        log.info("Exercise 5 - iterator");
        range.iterator().forEachRemaining(n -> log.info(String.valueOf(n)));
    }

    static void exercise6(){
        ListIterator<Integer> it = List.of(1,2,3,4,5).listIterator();

        log.info("Exercise 6 - forward");
        it.forEachRemaining( n -> log.info(String.valueOf(n)));

       log.info("Exercise 6 - backward");
       while (it.hasPrevious()){
            log.info(String.valueOf(it.previous()));
       }
    }

    static void exercise7(){
        log.info("Exercise 7"); 
        ArrayWrapper<CharSequence> wrapped = new ArrayWrapper<>(new CharSequence[]{"a","b","c","d"});
        for (CharSequence letter: wrapped){
            log.info(String.valueOf(letter));
        }

        log.info(String.valueOf( wrapped.get(3)));
    }

    static void exercise8(){
        log.info("Exercise 8");
        EvenNumbersIterable evenNumbers = new EvenNumbersIterable(1, 11);
        evenNumbers.iterator().forEachRemaining(n -> log.info(String.valueOf(n)));
    }

    static void exercise9(){
        log.info("Exercise 9");
        StringIterable content=new StringIterable(" Hi , my  name is   Iterator! ");
        content.iterator().forEachRemaining(c -> log.info(c.toString()));

    }

}
