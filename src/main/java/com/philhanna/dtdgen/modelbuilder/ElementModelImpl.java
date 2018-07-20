package com.philhanna.dtdgen.modelbuilder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.philhanna.dtdgen.AttributeModel;
import com.philhanna.dtdgen.ChildModel;
import com.philhanna.dtdgen.ElementModel;
import com.philhanna.dtdgen.dtd.DTDGenerator;

/**
 * Default implementation of {@link ElementModel}
 */
public class ElementModelImpl implements ElementModel {

   // ==================================================================
   // Class constants and variables
   // ==================================================================

   // ==================================================================
   // Class methods
   // ==================================================================

   // ==================================================================
   // Instance variables
   // ==================================================================

   private final String name;
   private int occurrences = 0;
   private boolean hasCharacterContent = false;
   private boolean sequenced = true;
   private final List<ChildModel> childseq = new ArrayList<ChildModel>();
   private final Map<String, AttributeModel> attributes = new LinkedHashMap<String, AttributeModel>();
   
   /**
    * Minimum number of attribute values that must appear for the
    * attribute to be regarded as an ID value
    */
   protected static final int MIN_ID_VALUES = 10;

   // ==================================================================
   // Constructors
   // ==================================================================

   public ElementModelImpl(String name) {
      this.name = name;
   }

   // ==================================================================
   // Implementation of ElementModel interface
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
   public ChildModel getChildModel(String name) {
      for (final ChildModel childDetails : childseq) {
         if (childDetails.getName().equals(name))
            return childDetails;
      }
      return null;
   }

   @Override
   public int getChildModelCount() {
      return childseq.size();
   }

   @Override
   public ChildModel getChildModel(int index) {
      return childseq.get(index);
   }

   @Override
   public boolean hasCharacterContent() {
      return hasCharacterContent;
   }

   @Override
   public boolean isSequenced() {
      return sequenced;
   }

   @Override
   public Iterator<String> attributeNameIterator() {
      return attributes.keySet().iterator();
   }

   @Override
   public AttributeModel getAttributeModel(String name) {
      return attributes.get(name);
   }

   @Override
   public String getIDAttributeName() {

      for (final String attrName : attributes.keySet()) {
         final AttributeModel attributeModel = attributes.get(attrName);

         /*
          * If every value of the attribute is distinct, and there are
          * at least MIN_ID_VALUES, treat it as an ID. ID values must be
          * Names. Only allowed one ID attribute per element type.
          */

         if (attributeModel.isAllNames()
               && (attributeModel.isUnique())
               && (attributeModel.getOccurrences() >= ElementModelImpl.MIN_ID_VALUES))
            return attrName;

         /*
          * TODO: This may give the wrong answer. We should check
          * whether the value sets of two candidate-ID attributes
          * overlap, in which case they can't both be IDs !!
          */

      }
      return null;
   }

   // ==================================================================
   // Instance methods (package protected)
   // ==================================================================

   void setHasCharacterContent(boolean hasCharacterContent) {
      this.hasCharacterContent = hasCharacterContent;
   }

   void setSequenced(boolean sequenced) {
      this.sequenced = sequenced;
   }

   void incrementOccurrences() {
      occurrences++;
   }

   AttributeModel getAttribute(String attributeName) {
      return attributes.get(attributeName);
   }

   void addAttribute(AttributeModel attributeModel) {
      attributes.put(attributeModel.getName(), attributeModel);
   }

   String[] getATTLISTs() {
      return DTDGenerator.getATTLISTs(this);
   }

   // ==================================================================
   // Instance methods (private)
   // ==================================================================

   public void addChild(ChildModel childModel) {
      childseq.add(childModel);
   }
}