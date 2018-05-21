package com.tmobile.dto;

import org.junit.Test;
import pl.pojo.tester.api.assertion.Method;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

public class ContractInfoDTOTest {

    @Test
    public void testPojo() {
        final Class<?> classUnderTest = ContractInfoDTO.class;
        assertPojoMethodsFor(classUnderTest).testing(Method.GETTER, Method.SETTER)
                .testing(Method.CONSTRUCTOR)
                .areWellImplemented();
    }
}
