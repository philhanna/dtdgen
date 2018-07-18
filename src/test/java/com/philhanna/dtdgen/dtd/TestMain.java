package com.philhanna.dtdgen.dtd;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

// Declared abstract to prevent JUnit from running it
public abstract class TestMain {

   @Before
   public void setUp() throws Exception {
   }

   @After
   public void tearDown() throws Exception {
   }

   @Test
   public void testGetVersion() throws IOException {
      Main.main(new String[] {"-v"});
   }

   @Test
   public void testGetVersionLong() throws IOException {
      Main.main(new String[] {"file1", "file2", "--version"});
   }

   @Test
   public void testGetHelp() throws IOException {
      Main.main(new String[] {"-h"});
   }

   @Test
   public void testGetHelpLong() throws IOException {
      Main.main(new String[] {"file1", "file2", "--help"});
   }

}
