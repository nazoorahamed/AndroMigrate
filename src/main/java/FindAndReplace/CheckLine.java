package FindAndReplace;

import FileReader.JavaReader.JavaLineDetails;

public class CheckLine {

    public void checkLine(JavaLineDetails javaLineDetails){


            AddNewLine newLine = new AddNewLine();

            try {
                newLine.removeLine(javaLineDetails.getFileP(),javaLineDetails.getLineNumber());
                newLine.insertStringInFile(javaLineDetails.getFileP(),javaLineDetails.getLineNumber(),"package com.example.nazoorahamed.myapplication;");
            } catch (Exception e) {
                e.printStackTrace();
            }
        
    }
}
