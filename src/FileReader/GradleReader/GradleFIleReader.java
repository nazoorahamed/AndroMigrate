package FileReader.GradleReader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GradleFIleReader {

    public GradleDetails readDetails(File file) {
        List<String> depends = new ArrayList<>();
        List<GradleLineDetails> details = new ArrayList<>();
        GradleDetails gradleDetails = new GradleDetails("21","21","12",depends,details);

        return gradleDetails;
    }
}
