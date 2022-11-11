package com.dataStructureStreamTests.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Stack;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertSame;

public class StackTest {

    private Stack<String> source;

    @BeforeEach
    void setUp() {
        source = new Stack<>();
        source.add("Tallinn");
        source.add("Rome");
        source.add("Paris");
        source.add("Vienna");}

        @Test
        public void canAddElementToStack(){
        String target = Stream.concat(source.stream(), Stream.of("Berlin")).reduce((first, second) -> second).orElse("Element not found");
        String pushString  = source.push("Berlin");
        assertSame(pushString,target);
        }

    @Test
    public void canRemoveElementFromQueue() {
        String target = source.stream().reduce((first, second) -> second).orElse("Element not found");
        List<String> targetQueue = source.stream().filter(s->!s.equals(target)).toList();
        String removedElement = source.pop();
        assertSame(removedElement,target);
        assertSame(targetQueue.size(),source.size());
    }

    @Test
    public void canPeekTheHeadElementOfQueue(){
        String target = source.stream().reduce((first, second) -> second).orElse("Element not found");
        String headElement = source.peek();
        assertSame(headElement,target);
    }


}
