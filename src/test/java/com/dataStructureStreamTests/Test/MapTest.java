package com.dataStructureStreamTests.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertSame;

public class MapTest {

    private Map<Integer, String> source;

    @BeforeEach
    void setUp() {
        source = new HashMap<>();
        source.put(1, "Tallinn");
        source.put(2, "Rome");
        source.put(3, "Paris");
        source.put(4, "Vienna");
    }

    @Test
    public void canPutToMap() {
        Map<Integer, String> map = new HashMap<>();
        map.put(5, "Madrid");
        Map<Integer, String> target = Stream.of(source, map).flatMap(s -> s.entrySet().stream()).collect(toMap(Map.Entry::getKey, Map.Entry::getValue));
        source.put(5, "Madrid");
        assertThat(target).hasSameSizeAs(source);
        assertThat(target).containsAllEntriesOf(source);
    }

    @Test
    public void canGetValueFromMapByKey() {
        String target = source.entrySet().stream().filter(a -> a.getKey().equals(2)).map(Map.Entry::getValue).findFirst().orElse("Not found");
        String getValue = source.get(2);
        assertSame(target, getValue);
    }

    @Test
    public void checkIfContainValueInMap() {
        boolean target = source.entrySet().stream().anyMatch(a -> a.getValue().contains("Rome"));
        boolean valueCheck = source.containsValue("Rome");
        assertSame(target, valueCheck);
    }

    @Test
    public void checkIfContainKeyInMap() {
        boolean target = source.entrySet().stream().anyMatch(a -> a.getKey().equals(1));
        boolean keyCheck = source.containsKey(1);
        assertSame(target, keyCheck);
    }

    @Test
    public void canMergeTwoMapsInOne() {
        Map<Integer, String> map = new HashMap<>();
        map.put(5, "Madrid");
        Map<Integer, String> target = Stream.concat(source.entrySet().stream(), map.entrySet().stream()).collect(toMap(Map.Entry::getKey, Map.Entry::getValue));
        map.forEach((k, v) -> source.merge(k, v, String::concat));
        assertThat(target).hasSameSizeAs(source);
        assertThat(target).containsAllEntriesOf(source);
    }

    @Test
    public void canRemoveAllFromMap() {
        Map<Integer, String> target = source.entrySet().stream().filter(s -> !s.getValue().equals("Rome")).collect(toMap(Map.Entry::getKey, Map.Entry::getValue));
        source.values().removeAll(Collections.singleton("Rome"));
        assertThat(target).hasSameSizeAs(source);
        assertThat(target).containsAllEntriesOf(source);
    }

/*    @Test
    public void canReplaceValueInMap() {
        Map<Integer,String> target = Stream.concat(source.entrySet().stream(), Stream.of(1,"Milan")).flatMap(s -> s.entrySet().stream()).collect(toMap(Map.Entry::getKey, Map.Entry::getValue));
        source.replace(1,"Milan");
        System.out.println(source);
        System.out.println(target);
    }*/

    @Test
    public void canClearMap(){
        Map<Integer,String> target =source.entrySet().stream().filter(s->s.getValue().isEmpty()).collect(toMap(Map.Entry::getKey, Map.Entry::getValue));
        source.clear();
        assertThat(target).hasSameSizeAs(source);
}
}