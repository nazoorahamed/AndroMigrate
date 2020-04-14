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

    public void getSourceFiles(List<File> jFile,List<File> gradleFile,List<File> manifFile){

        SetupMigration setupMigration = new SetupMigration();
        setupMigration.preProcessCode(readManifestFile(manifFile),readGradleFile(gradleFile),jFile);
    }

    public ManifestDetails readManifestFile(List<File> file){
        ManifestDetails manifestDetails =null;
        //read manifest files
        for(int i =0;i<file.size();i++){
            ManifestFileReader mr = new ManifestFileReader();
            manifestDetails = mr.readDetails(file.get(i));
            for (int k =0;k<manifestDetails.getCodeDetails().size();k++){
                System.out.println("manifest : "+manifestDetails.getCodeDetails().get(k).getCodeLine() + ": "+ manifestDetails.getCodeDetails().get(k).getLineNumber());
            }
        }
        return manifestDetails;
    }

    public GradleDetails readGradleFile(List<File> file){
        GradleDetails gradleDetails = null;
        // Read gradle files

        for (int i=0;i<file.size();i++){
            GradleFIleReader gd = new GradleFIleReader();
            gradleDetails = gd.getGradleDetails(file.get(i));
        }
        return gradleDetails;
    }

    public List<JavaLineDetails> readJavaFile(File file){
        // read java files
            JavaFileReader jr = new JavaFileReader();
            List<JavaLineDetails> jrd =  jr.readDetails(file);

            try {
                long lines = Files.lines(file.toPath()).count();
                System.out.println("Number of Lines: " + lines);
            } catch (IOException e) {
                e.printStackTrace();
            }
        return jrd;
    }


}
