package com.dataStructureStreamTests.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class SetTest {

    private Set<String> source;

    @BeforeEach
    void setUp(){
        source = new HashSet<>();
        source.add("Tallinn");
        source.add("Rome");
        source.add("Paris");
        source.add("Vienna");
    }

    @Test
    public void canAddElementToSet(){
        Set<String> target = Stream.concat(source.stream(), Stream.of("Berlin")).collect(Collectors.toSet());
        source.add("Berlin");
        assertSame(source.size(),target.size());
    }

    @Test
    public void canRemoveElementFromSet() {
        Set<String> target = source.stream().filter(s -> !s.contains("Tallinn")).collect(Collectors.toSet());
        source.remove("Tallinn");
        assertSame(source.size(), target.size());
    }


    @Test
    public void getClearSet(){
        Set<String> target = source.stream().filter(Objects::isNull).collect(Collectors.toSet());
        source.clear();
        assertSame(source.isEmpty(),target.isEmpty());
    }

    @Test
    public void checkIfSetContainElement(){
        boolean target = source.stream().anyMatch(s->source.contains("Paris"));
        boolean foundElement = source.contains("Paris");
        assertSame(target,foundElement);
    }


}
