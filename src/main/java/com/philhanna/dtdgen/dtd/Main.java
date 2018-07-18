package com.philhanna.dtdgen.dtd;

import java.io.*;
import java.util.*;

import org.xml.sax.SAXException;

import com.philhanna.dtdgen.DocumentModel;
import com.philhanna.dtdgen.Version;
import com.philhanna.dtdgen.modelbuilder.DocumentModelBuilder;

/**
 * Mainline class for invoking DTDGEN
 */
public class Main {

   static enum State {
      READING_OPTIONS, EXPECTING_OUTPUT_FILE, PROBLEM_FOUND
   }
   
   /**
    * Invokes DTDGEN with the specified arguments
    * @param args the command line arguments
    * @throws IOException if input file(s) cannot be read or output file cannot be written
    */
   public static void main(String[] args) throws IOException {

      // Check for help or version first
      
      if (args.length == 0) {
         showUsage();
         return;
      }
      
      for (final String arg : args) {
         if (arg.equals("-h") || arg.equals("--help")) {
            showUsage();
            return;
         }
         if (arg.equals("-v") || arg.equals("--version")) {
            showVersion();
            return;
         }
      }
      
      // Get the output file and a list of input files
      
      final List<File> inputFiles = new ArrayList<File>();
      File outputFile = null;
      State state = State.READING_OPTIONS;
      
      for (final String arg : args) {
         switch(state) {
            
            case READING_OPTIONS:
               if (arg.equals("-o") || arg.equals("--output"))
                  state = State.EXPECTING_OUTPUT_FILE;
               else if (arg.startsWith("-")) {
                  System.err.printf("Unrecognized option %s. Try -h for help\n", arg);
                  state = State.PROBLEM_FOUND;
               }
               else {
                  final File inputFile = new File(arg);
                  if (!inputFile.exists()) {
                     System.err.printf("Input file %s does not exist.\n", arg);
                     state = State.PROBLEM_FOUND;
                  }
                  inputFiles.add(inputFile);
               }
               break;
               
            case EXPECTING_OUTPUT_FILE:
               if (arg.startsWith("-")) {
                  System.err.printf("Expecting output file name, not %s. Try -h for help\n", arg);
                  state = State.PROBLEM_FOUND;
               }
               else {
                  outputFile = new File(arg);
                  state = State.READING_OPTIONS;
               }
               break;
               
            case PROBLEM_FOUND:
               break;
         }
      }
      
      // Verify that all options were found

      switch (state) {
         case READING_OPTIONS:
            break;
         case EXPECTING_OUTPUT_FILE:
            System.err.printf("No output file specified. Try -h for help\n");
            return;
         case PROBLEM_FOUND:
            return;
      }
      
      // Create a DocumentModelBuilder and run input files through it
      
      final DocumentModelBuilder modelBuilder = new DocumentModelBuilder();
      
      for (final File inputFile : inputFiles) {
         final InputStream in = new FileInputStream(inputFile);
         try {
            modelBuilder.run(in);
         }
         catch (SAXException e) {
            System.err.printf("XML error while processing %s\n", inputFile);
            e.printStackTrace();
            return;
         }
      }
      
      // Get the resulting document model
      
      final DocumentModel model = modelBuilder.getDocumentModel();
      
      // Write output DTD, either to stdout or to the specified file

      final DTDGenerator dtdgen = new DTDGenerator(model);
      if (outputFile == null) {
         final PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
         dtdgen.printDTD(out);
         out.flush();
      }
      else {
         final PrintWriter out = new PrintWriter(new FileWriter(outputFile));
         dtdgen.printDTD(out);
         out.flush();
         out.close();
      }
   }

   private static void showUsage() {
      final String[] lines = {
            "usage: dtdgen <inputFile> [, <inputFile>]* [-o | --output <outputFile>]",
            "       dtdgen -h | --help",
            "       dtdgen -v | --version",
            "",
            "where:",
            "",
            "<inputFile>      An XML file (of the same type as the others, if more than one)",
            "<outputFile>     The output .dtd file.  Defaults to stdout",
            "",
            "-h or --help     Displays this help text",
            "-v or --version  Displays the software version number",
      };
      for (final String line : lines)
         System.out.println(line);
   }

   private static void showVersion() {
      System.out.printf("dtdgen version \"%s\"\n", Version.getVersion());
   }

}
