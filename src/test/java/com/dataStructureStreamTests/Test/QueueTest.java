package com.dataStructureStreamTests.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toCollection;
import static org.junit.jupiter.api.Assertions.assertSame;

public class QueueTest {
    private Queue<String> source;

    @BeforeEach
    void setUp() {
        source = new PriorityQueue<>();
        source.add("Tallinn");
        source.add("Rome");
        source.add("Paris");
        source.add("Vienna");
    }

    @Test
    public void canAddElementToQueue(){
        Queue<String> target = Stream.concat(source.stream(), Stream.of("Berlin")).collect(toCollection(PriorityQueue::new));
        source.add("Berlin");
        assertSame(source.size(),target.size());
    }

    @Test
    public void canRemoveElementFromQueue() {
        Stream<String> target = Stream.generate(source::poll).takeWhile(Objects::nonNull);
        source.poll();
        assertSame(source.size(),target.toList().size());
    }

    @Test
    public void canPeekTheHeadElementFromQueue(){
        String target = source.stream().findFirst().orElse("Element not found");
        String headElement = source.peek();
        assertSame(headElement,target);
    }






    }
