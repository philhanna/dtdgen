package com.philhanna.dtdgen;

import java.util.Iterator;

/**
 * Keeps track of the the possible contents of an element, based on all
 * instances of it found in the source XML.
 */
public interface ElementModel {

   /**
    * Returns the element tag name
    */
   public String getName();

   /**
    * Returns the number of times this element was found in the input XML
    */
   public int getOccurrences();
   
   /**
    * Returns the <code>ChildModel</code> for the child element with
    * the specified name
    * @param name the name of the child element
    * @return the <code>ChildModel</code> element, or
    *         <code>null</code>, if this element does not have a child
    *         with that name.
    */
   public ChildModel getChildModel(String name);
   
   /**
    * Returns the number of <code>ChildModel</code> elements this parent element has
    */
   public int getChildModelCount();
   
   /**
    * Returns the specified <code>ChildModel</code> element
    * @param index the index within the list of children (zero-based)
    */
   public ChildModel getChildModel(int index);
   
   /**
    * Returns <code>true</code> if this element can have character
    * content
    */
   public boolean hasCharacterContent();

   /**
    * Returns <code>true</code> if this element's children always occur
    * in a specific sequence
    */
   public boolean isSequenced();

   /**
    * Returns an iterator over the list of attribute names (in the order that they first appear in the DTD)
    */
   public Iterator<String> attributeNameIterator();
   
   /**
    * Returns the attribute with the specified name, or <code>null</code>, if no such attribute exists
    * @param name the attribute name
    */
   public AttributeModel getAttributeModel(String name);

   /**
    * Returns the name of the ID attribute, if one can be inferred, otherwise returns <code>null</code>.
    */
   public String getIDAttributeName();
}