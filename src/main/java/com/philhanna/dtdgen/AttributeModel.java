package com.philhanna.dtdgen;

import java.util.Iterator;

/**
 * Keeps track of what is known about the type and value of an element
 * attribute, based on how it is used in all instances found in the
 * source XML.
 */
public interface AttributeModel {

   /**
    * Returns the attribute name
    */
   public String getName();

   /**
    * Returns the number of times this attribute was found in the source
    * XML associated with this element
    */
   public int getOccurrences();

   /**
    * Returns the number of distinct values this attribute was found to
    * have in the source XML associated with this element
    */
   public int getValueCount();
   
   /**
    * Returns an iterator over the values for this attribute
    */
   public Iterator<String> valueIterator();

   /**
    * Returns <code>true</code> if every occurrence of this attribute in
    * the element had a value that was a valid <code>NMTOKEN</code>
    */
   public boolean isAllNMTOKENs();

   /**
    * Returns the first value this attribute has in this element
    */
   public String getFirstValue();

}