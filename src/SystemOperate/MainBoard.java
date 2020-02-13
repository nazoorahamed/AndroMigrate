package SystemOperate;

import FileReader.GradleReader.GradleDetails;
import FileReader.GradleReader.GradleFIleReader;
import FileReader.JavaReader.JavaFileReader;
import FileReader.JavaReader.JavaLineDetails;
import FileReader.ManifestReader.ManifestDetails;
import FileReader.ManifestReader.ManifestFileReader;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainBoard {
    public static List<File> JFiles;
    public static List<File> GradleFile;
    public static List<File> ManifFile;

    public static void main(String[] args) {

//        File folder = new File("/Users/nazoorahamed/Desktop/ProB");
//        File[] listOfFiles = folder.listFiles();
//
//        for (File file : listOfFiles) {
//            if (file.isFile()) {
//                System.out.println(file.getName());
//            }
//            if(file.isDirectory()){
//                System.out.println("Directory :"+file.getName());
//            }
//        }
        String fname = "/Users/nazoorahamed/Desktop/MyApplication";
        JFiles = new ArrayList<File>();
        GradleFile = new ArrayList<File>();
        ManifFile = new ArrayList<File>();
        listf(fname);

        System.out.println("Gradle files : "+GradleFile.size());
        System.out.println("Java Files : "+JFiles.size());
        System.out.println("Manifest files : "+ManifFile.size());


        for (int i=0;i<GradleFile.size();i++){
            GradleFIleReader gd = new GradleFIleReader();
            GradleDetails gds = gd.readDetails(GradleFile.get(i));
            System.out.println(gds.getCompileSdk());
            System.out.println(gds.getMinSdk());
            System.out.println(gds.getTargetSdk());
            System.out.println(gds.getDependencies().size());
            System.out.println(gds.getCodeDetails().size());

        }



        for(int i=0;i<JFiles.size();i++){
            JavaFileReader jr = new JavaFileReader();
            List<JavaLineDetails> jrd =  jr.readDetails(JFiles.get(i));
            for (int k=0;k<jrd.size();k++){
                System.out.println(jrd.get(k).getFileP()+" : "+jrd.get(k).getLineNumber()+" : "+jrd.get(k).getCodeLine());
            }

        }



        for(int i =0;i<ManifFile.size();i++){
            ManifestFileReader mr = new ManifestFileReader();
            ManifestDetails mfd = mr.readDetails(ManifFile.get(i));
            
        }
    }

    public static List<File> listf(String directoryName) {
        File directory = new File(directoryName);

        List<File> resultList = new ArrayList<File>();


        // get all the files from a directory
        File[] fList = directory.listFiles();
        resultList.addAll(Arrays.asList(fList));
        for (File file : fList) {
            if (file.isFile()) {
               String type =  getFileExtension(file.getAbsolutePath());
               String filename = file.getName();
                //System.out.println(type);
                if (filename.equals("AndroidManifest.xml") && file.getAbsolutePath().contains("src") ){
                    System.out.println("Manifest is out");
                    ManifFile.add(file);
                    System.out.println(file.getAbsolutePath());
                }
                if(type.equals("java") && file.getAbsolutePath().contains("src")){
                    System.out.println("java file");
                    JFiles.add(file);
                    System.out.println(file.getAbsolutePath());
                }
                if(type.equals("gradle") && file.getAbsolutePath().contains("build.gradle")){
                    System.out.println("gradle file");
                    System.out.println(file.getAbsolutePath());
                    GradleFile.add(file);

                }

            } else if (file.isDirectory()) {
                resultList.addAll(listf(file.getAbsolutePath()));
            }
        }
        return resultList;
    }

    public static String getFileExtension(String fullName) {
       // checkNotNull(fullName);
        String fileName = new File(fullName).getName();
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
    }
}
