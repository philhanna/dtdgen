package com.philhanna.dtdgen.modelbuilder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit tests for ElementModelImpl
 */
public class TestElementModelImpl {

   @Test
   public void verifyDefaultsAreSet() {
      final ElementModelImpl ed = new ElementModelImpl("CubeQuery");
      assertEquals("CubeQuery", ed.getName());
      assertEquals(0, ed.getOccurrences());
      assertFalse(ed.hasCharacterContent());
      assertTrue(ed.isSequenced());
   }
}
