package com.philhanna.dtdgen.modelbuilder;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.philhanna.dtdgen.ChildModel;

/**
 * Unit tests for ChildModelImpl
 */
public class TestChildModelmpl {

   @Test
   public void testConstructor() {
      final ChildModel cd = new ChildModel("Larry");
      assertEquals("Larry", cd.getName());
   }

}
