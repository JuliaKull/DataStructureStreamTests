package com.dataStructureStreamTests.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class ListTest {

    private List<String> source;

    @BeforeEach
    void setUp() {
        source = new ArrayList<>();
        source.add("Tallinn");
        source.add("Rome");
        source.add("Paris");
        source.add("Vienna");
    }

    @Test
    public void canAddElementToList(){
        List<String> target = Stream.concat(source.stream(), Stream.of("Berlin")).toList();
        source.add("Berlin");
        assertSame(source.size(),target.size());
    }

    @Test
    public void canRemoveElementFromList() {
        List<String> target = source.stream().filter(s -> !s.contains("Tallinn")).toList();
        source.removeIf(s -> s.contains("Tallinn"));
        assertSame(source.size(), target.size());
    }

    @Test
    public void sortElementInList() {
        List<String> target = source.stream().sorted().toList();
        source.sort(Comparator.naturalOrder());
        assertEquals(target, source);
    }

    @Test
    public void getElementFromList() {
        String target = source.stream().filter(s -> s.equals("Paris")).findFirst().orElse("Element not found");
        String foundElement = source.get(2);
        assertEquals(target, foundElement);
    }

    @Test
    public void getIndexOfElement(){
        int index = source.stream().toList().indexOf("Paris");
        int target = source.indexOf("Paris");
        assertEquals(index,target);
    }

    @Test
    public void checkIfListContainElement(){
        boolean target = source.stream().anyMatch(s->source.contains("Paris"));
        boolean foundElement = source.contains("Paris");
        assertSame(target,foundElement);
    }

}
