package FileReader.ManifestReader;


import java.util.ArrayList;
import java.util.List;

public class ManifestFileReader {

    public ManifestDetails readDetails(){


        List<String> usesPermisiion = new ArrayList<>();
        List<String> services = new ArrayList<>();
        List<ManifestLineDetails> details = new ArrayList<>();

        ManifestDetails mfd = new ManifestDetails("",usesPermisiion,services,details);

        return  mfd;


    }
}
