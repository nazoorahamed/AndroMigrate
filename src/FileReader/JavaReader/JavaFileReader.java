package FileReader.JavaReader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JavaFileReader {

    public List<JavaLineDetails> readDetails(File file) {

        JavaLineDetails jr = new JavaLineDetails(file,1,"ser");

        List<JavaLineDetails> lineDetails = new ArrayList<>();

        lineDetails.add(jr);

        return lineDetails;

    }
}
