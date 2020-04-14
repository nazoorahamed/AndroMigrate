package SystemOperate;

import FileReader.GradleReader.GradleDependencies;
import FileReader.GradleReader.GradleDetails;
import FileReader.ManifestReader.ManifestDetails;
import FindAndReplace.LineEditor;

import java.io.File;
import java.util.List;
import java.util.TreeMap;

public class SetupMigration {
    APICodeGenerator codeGenerator = new APICodeGenerator();
    LineEditor addNewLine = new LineEditor();
    public void preProcessCode(ManifestDetails manifestDetails, GradleDetails gradleDetails, List<File> jFile){
        Gradlesetup(gradleDetails);
        ManifestSetup(manifestDetails);
        JavaCodeLineSetup(jFile);


    }
    public void Gradlesetup(GradleDetails gradleDetails){
        System.out.println("Pre processing :"+gradleDetails.getTargetsdkline().headMap("29"));
        System.out.println(" line line "+gradleDetails.getTargetsdkline());
        GradleMapping(gradleDetails,gradleDetails.getDependencies(),gradleDetails.getTargetsdkline());

        for (int i=0;i<gradleDetails.getDependencies().size();i++){
            System.out.println("depends"+gradleDetails.getDependencies().get(i).getCodeLine()+gradleDetails.getDependencies().get(i).getLineNumber());
        }
    }
    public void GradleMapping(GradleDetails gdDetails, List<GradleDependencies> dependencies, TreeMap<String,Integer> sdklinedetails){
        int targersdk = Integer.parseInt(gdDetails.getTargetSdk());
        int minSdk = Integer.parseInt(gdDetails.getMinSdk());;
        int compilesdk = Integer.parseInt(gdDetails.getCompileSdk());
        System.out.println(targersdk+"::"+minSdk+"::"+compilesdk);

        if (targersdk<29){
            try {
                //addNewLine.removeLine(gdDetails.getFile(),sdklinedetails.get("targetSdkVersion"));
                addNewLine.replaceLine(gdDetails.getFile(),sdklinedetails.get("targetSdkVersion"),"        targetSdkVersion 29");
                gdDetails =
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (compilesdk<29){
            try {
                //addNewLine.removeLine(gdDetails.getFile(),sdklinedetails.get("targetSdkVersion"));
                addNewLine.replaceLine(gdDetails.getFile(),sdklinedetails.get("compileSdkVersion"),"    compileSdkVersion 29");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void ManifestSetup(ManifestDetails mainf){

    }
    public void JavaCodeLineSetup(List<File> jFile){
        for (int i=0;i<jFile.size();i++){
            codeGenerator.readJavaFile(jFile.get(i));
            System.out.println(" ababba :"+jFile.get(i).getAbsolutePath());
        }
    }

}
