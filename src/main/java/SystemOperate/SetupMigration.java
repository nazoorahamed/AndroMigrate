package SystemOperate;

import FileReader.GradleReader.GradleDetails;
import FileReader.JavaReader.JavaLineDetails;
import FileReader.ManifestReader.ManifestDetails;

import java.util.List;

public class SetupMigration {

    public void preProcessCode(ManifestDetails manifestDetails, GradleDetails gradleDetails, List<JavaLineDetails> javaLineDetails){
        Gradlesetup(gradleDetails);


    }
    public void Gradlesetup(GradleDetails gradleDetails){
        System.out.println("Pre processing :"+gradleDetails.getTargetsdkline().headMap("29"));
        System.out.println(" line line "+gradleDetails.getTargetsdkline());
        for (int i=0;i<gradleDetails.getDependencies().size();i++){
            System.out.println("depends"+gradleDetails.getDependencies().get(i).getCodeLine()+gradleDetails.getDependencies().get(i).getLineNumber());
        }
    }
    public void ManifestSetup(){

    }
    public void JavaCodeLineSetup(){

    }

}
