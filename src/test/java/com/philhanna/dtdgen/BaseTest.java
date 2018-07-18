package com.philhanna.dtdgen;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;

/**
 * Base class for all unit tests.  Provides access to the test directory and contains
 * utility methods for comparing files, etc.
 */
public abstract class BaseTest {

   protected File testDir;

   @Before
   public void setUp() throws Exception {
      testDir = new File("./Testware/Data").getCanonicalFile();
   }

   @After
   public void tearDown() throws Exception {
   }

   /**
    * Helper method to compare two files for equality
    * @param expectedFile
    * @param actualFile
    * @throws IOException
    */
   public void assertFilesEquals(File expectedFile, File actualFile)
         throws IOException {

      assertNotNull(expectedFile);
      assertNotNull(actualFile);

      assertTrue(
            "Expected file " + expectedFile + " does not exist",
            expectedFile.exists());
      assertTrue(
            "Actual file " + actualFile + " does not exist",
            actualFile.exists());

      final BufferedReader inE = new BufferedReader(
            new FileReader(expectedFile));
      final BufferedReader inA = new BufferedReader(new FileReader(actualFile));

      try {
         int lineno = 0;
         for (;;) {
            final String lineE = inE.readLine();
            final String lineA = inA.readLine();
            lineno++;
            if (lineE == null && lineA != null)
               fail("Actual file is longer: line " + lineno + ": " + lineA);
            if (lineE != null && lineA == null)
               fail("Expected file is longer: line " + lineno + ": " + lineE);
            if (lineE == null)
               break;
            if (!lineE.equals(lineA))
               assertEquals("Different line " + lineno, lineE, lineA);
         }
         // Files match
      }
      finally {
         inE.close();
         inA.close();
      }
   }

}
