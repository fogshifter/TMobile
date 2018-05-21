package com.tmobile.entity;

import org.junit.Test;
import pl.pojo.tester.api.assertion.Method;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

public class TariffTest {

    @Test
    public void testPojo() {
        final Class<?> classUnderTest = Tariff.class;
        assertPojoMethodsFor(classUnderTest).testing(Method.GETTER, Method.SETTER)
                .testing(Method.CONSTRUCTOR)
                .areWellImplemented();
    }
}
