package FindAndReplace;

import java.io.*;

public class AddNewLine {

    public void insertStringInFile
            (File inFile, int lineno, String lineToBeInserted)
            throws Exception {
        // temp file
        File outFile = new File("/Users/nazoorahamed/Desktop/4th Year/1st Semester/Concurrent Programing/Tutorials/Thread States/src/tempt.java");

        // input
        FileInputStream fis = new FileInputStream(inFile);
        BufferedReader in = new BufferedReader
                (new InputStreamReader(fis));


        // output
        FileOutputStream fos = new FileOutputStream(outFile);
        PrintWriter out = new PrintWriter(fos);

        String thisLine = "";
        int i = 1;
        while ((thisLine = in.readLine()) != null) {
            if (i == lineno) out.println(lineToBeInserted);
            out.println(thisLine);
            i++;
        }

        String lineToRemove = "  //          Thread.wait(1500);";
        String currentLine;

        while((currentLine = in.readLine()) != null) {
            // trim newline when comparing with lineToRemove
            String trimmedLine = currentLine.trim();
            if(trimmedLine.equals(lineToRemove)) continue;
            out.write(currentLine + System.getProperty("line.separator"));
        }
        out.flush();
        out.close();
        in.close();

        inFile.delete();

        outFile.renameTo(inFile);
    }
}
