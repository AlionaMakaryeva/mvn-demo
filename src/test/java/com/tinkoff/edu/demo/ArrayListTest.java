package com.tinkoff.edu.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class, test case, fixture
 */
public class ArrayListTest {
    private ArrayList<Object> sut;

    @BeforeEach
    public void setUp() {
        sut = new ArrayList<>();
    }
    /**
     * Test method
     */
    @Test
    public void shouldAddElement() {
        Object dummy = new Object();
        sut.add(dummy);

        assertEquals(1, sut.size());
        assertTrue(sut.contains(dummy));
    }

    @Test
    public void shouldGetElementWhenIndexOfBounds() {
        sut.get(0);
    }

}
