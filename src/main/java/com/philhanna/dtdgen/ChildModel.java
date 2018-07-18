package com.philhanna.dtdgen;

/**
 * Records information about the presence of a child element within its
 * parent element. If the parent element is sequenced, then the child
 * elements always occur in sequence with the given frequency.
 */
public interface ChildModel {

   /**
    * Returns the child element name
    */
   public String getName();

   /**
    * Returns <code>true</code> if this child can be repeated in its
    * parent element
    */
   public boolean isRepeatable();

   /**
    * Returns true if this child element is not required to be present
    * under its parent element
    */
   public boolean isOptional();

}