package com.philhanna.dtdgen.modelbuilder;

import java.util.*;

import com.philhanna.dtdgen.*;

/**
 * Default implementation of {@link AttributeModel}
 */
public class AttributeModelImpl implements AttributeModel {

   // ==========================================================
   // Instance variables
   // ==========================================================

   /**
    * Name of the attribute
    */
   private final String name;

   /**
    * Number of occurrences of the attribute
    */
   private int occurrences = 0;

   /**
    * <code>true</code> if no duplicate values encountered
    */
   private boolean unique = true;

   /**
    * set of all distinct values encountered for this attribute
    */
   private final Set<String> values = new LinkedHashSet<String>();

   /**
    * <code>true</code> if all the attribute values are valid names
    */
   private boolean allNames = true;

   /**
    * <code>true</code> if all the attribute values are valid
    * <code>NMTOKEN</code>s
    */
   private boolean allNMTOKENs = true;

   // ==================================================================
   // Constructors
   // ==================================================================

   /**
    * Creates a new attribute model with the specified name
    * @param name the attribute name
    */
   public AttributeModelImpl(String name) {
      this.name = name;
   }

   // ==================================================================
   // Implementation of AttributeModel
   // ==================================================================

   @Override
   public String getName() {
      return name;
   }

   @Override
   public int getOccurrences() {
      return occurrences;
   }

   @Override
   public int getValueCount() {
      return values.size();
   }

   @Override
   public String getFirstValue() {
      if (values.isEmpty())
         return null;
      final Iterator<String> it = valueIterator();
      final String firstValue = it.next();
      return firstValue;
   }

   @Override
   public Iterator<String> valueIterator() {
      return values.iterator();
   }

   // ==========================================================
   // Instance methods
   // ==========================================================

   public void incrementOccurrences() {
      occurrences++;
   }

   public boolean isAllNames() {
      return allNames;
   }

   @Override
   public boolean isAllNMTOKENs() {
      return allNMTOKENs;
   }

   public boolean isUnique() {
      return unique;
   }

   public void setAllNames(boolean allNames) {
      this.allNames = allNames;
   }

   public void setAllNMTOKENs(boolean allNMTOKENs) {
      this.allNMTOKENs = allNMTOKENs;
   }

   public void setUnique(boolean unique) {
      this.unique = unique;
   }

   public boolean contains(String value) {
      return values.contains(value);
   }

   public void addValue(String value) {
      values.add(value);
   }
   
   public void setOccurrences(int occurrences) {
      this.occurrences = occurrences;
   }

   @Override
   public String toString() {
      return "AttributeModelImpl [name="
            + name
            + ", occurrences="
            + occurrences
            + ", unique="
            + unique
            + ", values="
            + values
            + ", allNames="
            + allNames
            + ", allNMTOKENs="
            + allNMTOKENs
            + "]";
   }
   
}
