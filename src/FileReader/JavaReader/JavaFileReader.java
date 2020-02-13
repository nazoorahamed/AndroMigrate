package FileReader.JavaReader;

import java.util.ArrayList;
import java.util.List;

public class JavaFileReader {

    public List<JavaLineDetails> readDetails() {

        JavaLineDetails jr = new JavaLineDetails(1,"ser");

        List<JavaLineDetails> lineDetails = new ArrayList<>();

        lineDetails.add(jr);

        return lineDetails;

    }
}
