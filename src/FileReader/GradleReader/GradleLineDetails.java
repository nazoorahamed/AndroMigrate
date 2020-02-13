package FileReader.GradleReader;

public class GradleLineDetails {
    private int lineNumber;
    private String codeLine;

    public GradleLineDetails(int lineNumber, String codeLine) {
        this.lineNumber = lineNumber;
        this.codeLine = codeLine;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getCodeLine() {
        return codeLine;
    }

    public void setCodeLine(String codeLine) {
        this.codeLine = codeLine;
    }
}
