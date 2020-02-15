package FileReader.GradleReader;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GradleFIleReader {

    public List<GradleLineDetails> readDetails(File file) {
        List<GradleLineDetails> grLineDetails = new ArrayList<>();

        try {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                int number=0;
                while ((line = br.readLine()) != null) {
                    number++;
                    GradleLineDetails jr = new GradleLineDetails(file,number,line);

                    grLineDetails.add(jr);
                    System.out.println("file name : "+file.getName());

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Line Details : "+grLineDetails.size());
        return grLineDetails;
    }

    public GradleDetails getGradleDetails(File file){
        List<String> depends;
        List<GradleLineDetails> detailsLine = readDetails(file);

        String targetSdk = null;
        String compileSdk = null;
        String minSdk = null;
        for (int i =0;i<detailsLine.size();i++){
            String line = detailsLine.get(i).getCodeLine();

            if (line.contains("targetSdkVersion")){
                System.out.println( detailsLine.get(i).getLineNumber()+" : True");
                targetSdk = findNumber(line);
            }else if (line.contains("compileSdkVersion")){
                System.out.println( detailsLine.get(i).getLineNumber()+" : True");
                compileSdk = findNumber(line);
            }else if (line.contains("minSdkVersion")){
                System.out.println( detailsLine.get(i).getLineNumber()+" : True");
                minSdk = findNumber(line);
            }
        }
         depends = findDependencies(detailsLine);

        System.out.println(targetSdk+" : "+compileSdk+" : "+minSdk);

        for (int i=0;i<depends.size();i++){
            System.out.println(depends.get(i));
        }
        return new GradleDetails(file,targetSdk,compileSdk,minSdk,depends,detailsLine);

    }

    public List<String> findDependencies(List<GradleLineDetails> lines){
        int linenumber = 0;
        List<String> depends = new ArrayList<>();
        for (int i=0;i<lines.size();i++){
            if(lines.get(i).getCodeLine().contains("dependencies")){
                linenumber = lines.get(i).getLineNumber();
                System.out.println("ves : "+linenumber);
            }
        }
        if (linenumber != 0){
            while (!lines.get(linenumber).getCodeLine().contains("}")){
                linenumber++;
                depends.add(lines.get(linenumber-1).getCodeLine());

            }
        }


        return depends;
    }

    public String findNumber(String string){
        string = string.replaceAll("[^0-9]+", " ");
        System.out.println(Arrays.asList(string.trim().split(" ")));
        String number = Arrays.asList(string.trim().split(" ")).get(0);
        return number;
    }
}
