package com.philhanna.dtdgen.modelbuilder;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit tests for ChildModelImpl
 */
public class TestChildModelmpl {

   @Test
   public void testConstructor() {
      final ChildModelImpl cd = new ChildModelImpl("Larry");
      assertEquals("Larry", cd.getName());
   }

}
