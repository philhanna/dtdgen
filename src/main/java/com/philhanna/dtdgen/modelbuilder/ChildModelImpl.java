package com.philhanna.dtdgen.modelbuilder;

import com.philhanna.dtdgen.ChildModel;

/**
 * Default implementation of {@link ChildModel}
 */
public class ChildModelImpl implements ChildModel {
   
   // ==================================================================
   // Instance variables
   // ==================================================================
   
   private final String name;
   private boolean repeatable;
   private boolean optional;

   // ==================================================================
   // Constructors
   // ==================================================================
   
   public ChildModelImpl(String name) {
      this.name = name;
   }

   // ==================================================================
   // Implementation of ChildModel interface
   // ==================================================================
   
   @Override
   public String getName() {
      return name;
   }

   @Override
   public boolean isRepeatable() {
      return repeatable;
   }

   @Override
   public boolean isOptional() {
      return optional;
   }

   // ==================================================================
   // Instance methods (only accessible from this package)
   // ==================================================================

   void setRepeatable(boolean repeatable) {
      this.repeatable = repeatable;
   }

   void setOptional(boolean optional) {
      this.optional = optional;
   }
}