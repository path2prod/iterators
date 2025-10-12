package com.path2prod.medium;

import java.nio.Buffer;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IteratorUses {

    public static void main(String[] args){
       exercise1();
       exercise2();
       exercise3();
       exercise4();
       exercise5();
       exercise6();
       exercise7();
    }

    static void exercise1(){
    
        List<Integer> numbers = List.of(1,2,3,4,5);
        List<Character> letters = List.of('a','b','c','d','e');
        List<Integer> numbers_2 = List.of(6,7,8,9,10);
        List<Character> letters_2 = List.of('f','g','h','i','j');

        MultiList<Object> multiList = new MultiList<>();
        multiList.lists.add(numbers);
        multiList.lists.add(letters);
        multiList.lists.add(numbers_2);
        multiList.lists.add(letters_2);


        log.info("exercise1 - sequential iterator");
        multiList.getSequentialIterator().iterator().forEachRemaining( item -> log.info(item.toString()));

        log.info("exercise1 - odd iterator");
        multiList.getOddIterator().iterator().forEachRemaining( item -> log.info(item.toString()));


        log.info("exercise1 - even iterator");
        multiList.getEvenIterator().iterator().forEachRemaining( item -> log.info(item.toString()));

    }

    static void exercise2(){
        log.info("exercise 2 - students age > 18");
        FilteredIterable<Student> filtered = new FilteredIterable<>(Student.getStudents(),  student -> student.age() > 18);
        filtered.iterator().forEachRemaining( student -> log.info(student.toString()));

        log.info("exercise 2 - students age > 18 , score >= 5.0");
        filtered = new FilteredIterable<>(filtered,  student -> student.score() >= 5.0);
        filtered.iterator().forEachRemaining( student -> log.info(student.toString()));


        log.info("exercise 2 - students age > 18 OR score >= 5.0");
        FilteredIterable<Student> filtered_2 = new FilteredIterable<>(Student.getStudents(),  student -> student.age() > 18)
                                                .and(student -> student.score() >= 5.0);

        filtered_2.iterator().forEachRemaining( student -> log.info(student.toString()));

        log.info("exercise 2 - students score >= 9.0 OR score < 5.0");
        FilteredIterable<Student> filtered_3 = new FilteredIterable<>(Student.getStudents(),  student -> student.score() >= 9.0)
                                                .or(student -> student.score() <= 5.0);

        filtered_3.iterator().forEachRemaining( student -> log.info(student.toString()));

        log.info("exercise 2 - students age > 18  AND NOT students name <> Jane)");
        FilteredIterable<Student> filtered_4 = new FilteredIterable<>(Student.getStudents(),student -> student.age() > 18)
                                                .not();
        filtered_4.iterator().forEachRemaining( student -> log.info(student.toString()));
    }

    static void exercise3(){    
        log.info(" exercise 3");
        Supplier<Integer> generate10numbers = () ->  new Random().nextInt();

        Iterator<Integer> iterator = new LazyIterator<>(generate10numbers);
        for (int i=0; i<10; i++){
            log.info(iterator.next().toString());
        }
        
    }

    static void exercise4(){
        log.info("exercise 4");
        
        CicleIterable<Integer> cicleIterable = new CicleIterable<>(List.of(1,2,3,4,5), true, 3);
        cicleIterable.iterator().forEachRemaining(e -> log.info(e.toString()));

    }

    static void exercise5(){
        log.info("exercise 5");
        PeekableIterable.PeekableIterator<Integer> peekIterator = new PeekableIterable<Integer>(List.of(1,2,3,4,5)).iterator();

        while (peekIterator.hasNext()) {
            log.info(peekIterator.next().toString());
            log.info("peek value {}", peekIterator.peek().map(Object::toString).orElse("null"));
        }
    }

    static void exercise6(){
        log.info("exercise 6");
        BufferIterable.BufferIterator<Integer> bufferIterator = new BufferIterable<Integer>(List.of(1,2,3,4,5), 3).iterator();

        log.info("nothing in buffer " + bufferIterator.previous());
        bufferIterator.next();
        bufferIterator.next();
        log.info("buffer 2nd " + bufferIterator.previous());
        log.info("buffer 1st " + bufferIterator.previous());
        log.info("print 3rd " + bufferIterator.next());
        log.info("buffer 3rd " + bufferIterator.previous());
    }

    static void exercise7(){
        log.info("Exercise 7");
        MapIterable<Integer,Integer> transformIterable = new MapIterable<>(List.of(1,2,3,4,5), n -> n * n);
        MapIterable<Integer,Double> transformIterable_2 = new MapIterable<>(transformIterable, n -> Double.valueOf(n));
        MapIterable<Double,String> transformIterable_3 = new MapIterable<>(transformIterable_2, n -> n.toString());

        transformIterable_3.iterator().forEachRemaining( n -> log.info(n));
    }
}
