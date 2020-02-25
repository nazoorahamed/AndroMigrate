package FileReader.ManifestReader;


import FileReader.GradleReader.GradleLineDetails;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ManifestFileReader {

    public ManifestDetails readDetails(File file){


        List<String> usesPermission = new ArrayList<>();
        List<String> services = new ArrayList<>();
        List<ManifestLineDetails> details = new ArrayList<>();

        try {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                int number=0;
                while ((line = br.readLine()) != null) {
                    number++;
                    ManifestLineDetails jr = new ManifestLineDetails(file,number,line);

                    details.add(jr);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ManifestDetails mfd = new ManifestDetails("",usesPermission,services,details);

        return  mfd;
    }
}
