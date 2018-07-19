package com.philhanna.dtdgen.dtd;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for Main
 */
public class TestMain {

   private ByteArrayOutputStream baos;
   private PrintStream save_stdout;

   @Before
   public void setUp() throws Exception {

      // Save the current stdout so that it can be restored after each
      // test

      save_stdout = System.out;

      // Use an in-memory stdout so that output can be compared in tests

      baos = new ByteArrayOutputStream();
      PrintStream stdout = new PrintStream(baos);
      System.setOut(stdout);
   }

   @After
   public void tearDown() throws Exception {
      if (save_stdout != null) {
         System.setOut(save_stdout);
      }
   }

   @Test
   public void testNoArgs() throws Exception {
      Main.main(new String[] {});
      System.out.flush();
      final String actual = new String(baos.toByteArray());
      assertTrue(actual.contains("<inputFile>"));
   }
   
   @Test
   public void testGetVersion() throws IOException {
      Main.main(new String[] { "-v" });
      System.out.flush();
      final String actual = new String(baos.toByteArray());
      assertTrue(actual.contains("dtdgen version"));
   }

   @Test
   public void testGetVersionLong() throws IOException {
      Main.main(new String[] { "file1", "file2", "--version" });
   }

   @Test
   public void testGetHelp() throws IOException {
      Main.main(new String[] { "-h" });
   }

   @Test
   public void testGetHelpLong() throws IOException {
      Main.main(new String[] { "file1", "file2", "--help" });
   }

}
