package com.tinkoff.edu.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class HashSetTest {
    private HashSet<Object> sut = null;

    @BeforeEach // @AfterEach
    public void setUp() {
        this.sut = new HashSet<>();
//        this.sut.add("13123");
    }

    @Test
    public void shouldAddElementWhenElementNotExists () {
        //region Act |When
        sut.add("added string");
        //endregion

        // region Assert | Then
        assertEquals(1, sut.size());
        assertTrue(sut.contains("added string"));
        //endregion
    }


    @Test
    public void shouldNotAddElementWhenElementNotExists () {
       sut.add("added string twice");
        var added = sut.add("added string twice");

        assertEquals(1, sut.size());
        assertTrue(sut.contains("added string twice"));
        assertFalse(added);
    }
}
