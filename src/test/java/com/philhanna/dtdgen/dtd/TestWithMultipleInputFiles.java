package com.philhanna.dtdgen.dtd;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.philhanna.dtdgen.BaseTest;
import com.philhanna.dtdgen.DocumentModel;
import com.philhanna.dtdgen.modelbuilder.DocumentModelBuilder;

/**
 * Base test for multiple input files test case
 */
public class TestWithMultipleInputFiles extends BaseTest {

   private static final Logger log = Logger
         .getLogger(TestWithMultipleInputFiles.class);

   private DocumentModel model;

   public void setUp() throws Exception {

      super.setUp();

      // Create a document model builder that will be used with multiple
      // inputs

      final DocumentModelBuilder modelBuilder = new DocumentModelBuilder();

      // Loop over the list of all XML files in the test data directory
      // and run them through the model builder

      assertNotNull(testDir);

      model = null;
      for (final File inputFile : testDir.listFiles()) {
         if (inputFile.getName().endsWith(".xml")) {
            log.info("Starting file " + inputFile.getName());
            final InputStream in = new FileInputStream(inputFile);
            modelBuilder.run(in);
         }
      }

      model = modelBuilder.getDocumentModel();
      assertNotNull(model);
   }

   public void tearDown() throws Exception {
      super.tearDown();
   }

   @Test
   public void printCombinedDTD() throws IOException {
      final File outputFile = new File(testDir, "Analysis.dtd");
      final DTDGenerator dtdgen = new DTDGenerator(model);
      final PrintWriter out = new PrintWriter(new FileWriter(outputFile));
      dtdgen.printDTD(out);
      out.flush();
      out.close();
   }
}
