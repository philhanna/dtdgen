package com.philhanna.dtdgen.modelbuilder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for AttributeModelImpl
 */
public class TestAttributeModelImpl {

   private AttributeModelImpl attributeModel;
   
   @Before
   public void setUp() throws Exception {
      attributeModel = new AttributeModelImpl("stooge");
      attributeModel.addValue("Larry");
      attributeModel.addValue("Curly");
      attributeModel.addValue("Moe");
      attributeModel.addValue("Shemp");
      attributeModel.addValue("Curly Joe");
   }
   
   @Test
   public void checkDefaults() {
      assertEquals(0, attributeModel.getOccurrences());
      assertTrue(attributeModel.isUnique());
      assertTrue(attributeModel.isAllNames());
      assertTrue(attributeModel.isAllNMTOKENs());
   }

   @Test
   public void testGetFirstValue() {
      // Make sure that the list is not alphabetical, but in arrival sequence
      assertEquals("Larry", attributeModel.getFirstValue());
   }

   @Test
   public void testGetFirstValueOnEmptyList() {
      final AttributeModelImpl adNull = new AttributeModelImpl("stooge");
      assertNull(adNull.getFirstValue());
      adNull.addValue("Max");
      assertEquals("Max", adNull.getFirstValue());
   }

   @Test
   public void testIncrementOccurrences() {
      assertEquals(0, attributeModel.getOccurrences());
      attributeModel.incrementOccurrences();
      attributeModel.incrementOccurrences();
      attributeModel.incrementOccurrences();
      assertEquals(3, attributeModel.getOccurrences());
   }
}