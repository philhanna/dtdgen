package com.philhanna.dtdgen.modelbuilder;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import com.philhanna.dtdgen.DocumentModel;
import com.philhanna.dtdgen.ElementModel;

/**
 * Default implementation of {@link DocumentModel}
 */
public class DocumentModelImpl extends DocumentModel {

   // ====================================================================
   // Class constants and variables
   // ====================================================================

   // ====================================================================
   // Class methods
   // ====================================================================

   // ====================================================================
   // Instance variables
   // ====================================================================

   /**
    * Alphabetical list of element types appearing in the document; each
    * has the element name as a key and an ElementModelImpl object as
    * the value.
    */
   private final Map<String, ElementModel> elementMap = new TreeMap<String, ElementModel>();
   
   // ====================================================================
   // Constructors
   // ====================================================================

   // ====================================================================
   // Implementation of DocumentModel
   // ====================================================================
   
   @Override
   public ElementModel getElementModel(String elementName) {
      return elementMap.get(elementName);
   }

   @Override
   public Iterator<String> elementNameIterator() {
      return elementMap.keySet().iterator();
   }

   // ====================================================================
   // Instance methods
   // ====================================================================

   public ElementModel getElementModelImpl(String elementName) {
      return getElementModel(elementName);
   }
   
   public void addElementModelImpl(ElementModel elementModel) {
      final String elementName = elementModel.getName();
      elementMap.put(elementName, elementModel);
   }

}
