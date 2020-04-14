package SystemOperate;

import FileReader.GradleReader.GradleDetails;
import FileReader.GradleReader.GradleFIleReader;
import FileReader.JavaReader.JavaFileReader;
import FileReader.JavaReader.JavaLineDetails;
import FileReader.ManifestReader.ManifestDetails;
import FileReader.ManifestReader.ManifestFileReader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class APICodeGenerator {
    GradleDetails gradleDetails;
    ManifestDetails manifestDetails;
    List<JavaLineDetails> javaLineDetails= new ArrayList<>();
    public void getSourceFiles(List<File> jFile,List<File> gradleFile,List<File> manifFile){



        // Read gradle files

        for (int i=0;i<gradleFile.size();i++){
            GradleFIleReader gd = new GradleFIleReader();
            gradleDetails = gd.getGradleDetails(gradleFile.get(i));
        }

        // read java files

        for(int i=0;i<jFile.size();i++){

            JavaFileReader jr = new JavaFileReader();
            List<JavaLineDetails> jrd =  jr.readDetails(jFile.get(i));
            javaLineDetails.addAll(jrd);

            try {
                long lines = Files.lines(jFile.get(i).toPath()).count();
                System.out.println("Number of Lines: " + lines);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Out :"+ javaLineDetails.size());

        //read manifest files
        for(int i =0;i<manifFile.size();i++){
            ManifestFileReader mr = new ManifestFileReader();
            manifestDetails = mr.readDetails(manifFile.get(i));
            for (int k =0;k<manifestDetails.getCodeDetails().size();k++){
                System.out.println("manifest : "+manifestDetails.getCodeDetails().get(k).getCodeLine() + ": "+ manifestDetails.getCodeDetails().get(k).getLineNumber());
            }
        }
        SetupMigration setupMigration = new SetupMigration();
        setupMigration.preProcessCode(manifestDetails,gradleDetails,javaLineDetails);
    }

}
