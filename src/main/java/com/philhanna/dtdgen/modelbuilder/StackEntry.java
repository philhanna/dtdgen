package com.philhanna.dtdgen.modelbuilder;

/**
 * StackEntry is a data structure we put on the stack for each nested
 * element
 */
public class StackEntry {

   // ====================================================================
   // Class constants and variables
   // ====================================================================

   // ====================================================================
   // Class methods
   // ====================================================================

   // ====================================================================
   // Instance variables
   // ====================================================================

   private final ElementModelImpl elementModel;
   private int sequenceNumber = -1;
   private String latestChildName;

   // ====================================================================
   // Constructors
   // ====================================================================

   public StackEntry(ElementModelImpl elementModel) {
      this.elementModel = elementModel;
   }

   // ====================================================================
   // Instance methods
   // ====================================================================

   public ElementModelImpl getElementModel() {
      return elementModel;
   }

   public int getSequenceNumber() {
      return sequenceNumber;
   }

   public void incrementSequenceNumber() {
      sequenceNumber++;
   }

   public String getLatestChildName() {
      return latestChildName;
   }

   public void setLatestChildName(String latestChild) {
      this.latestChildName = latestChild;
   }
}