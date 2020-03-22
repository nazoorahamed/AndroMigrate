package SystemOperate;

import FileReader.GradleReader.GradleDetails;
import FileReader.GradleReader.GradleFIleReader;
import FileReader.JavaReader.JavaFileReader;
import FileReader.JavaReader.JavaLineDetails;
import FileReader.ManifestReader.ManifestDetails;
import FileReader.ManifestReader.ManifestFileReader;
import FindAndReplace.ReplaceLine;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.sql.*;
import java.util.Properties;
public class MainBoard {
    public static List<File> JFiles;
    public static List<File> GradleFile;
    public static List<File> ManifFile;

    public static void main(String[] args) throws Exception {

        // Connect to database
        String hostName = "andromigratedb.database.windows.net"; // update me
        String dbName = "AndroMigrateDB"; // update me
        String user = "user"; // update me
        String password = "Donznasoor2"; // update me
        String url = String.format("jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;"
                + "hostNameInCertificate=*.database.windows.net;loginTimeout=30;", hostName, dbName, user, password);
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url);
            String schema = connection.getSchema();
            System.out.println("Successful connection - Schema: " + schema);

            System.out.println("Query data example:");
            System.out.println("=========================================");

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT * FROM GradleDetails";

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(selectSql)) {

                // Print results from select statement.
                System.out.println("Top 20 categories:");
                while (resultSet.next()) {
                    System.out.println(resultSet.getString(1) + " "
                            + resultSet.getString(2) + " " + resultSet.getString(3) + " " + resultSet.getString(4) + " " + resultSet.getString(5));

                    String latestSdkVersion = resultSet.getString(1);
                    String latestBuildToolVersion  = resultSet.getString(2);
                    String latestAppCompactSupport = resultSet.getString(3);
                    String latestDesignSupport = resultSet.getString(4);
                    String latestConstrainSupport = resultSet.getString(5);

                }
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

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
        JFiles = new ArrayList<>();
        GradleFile = new ArrayList<>();
        ManifFile = new ArrayList<>();
        listf(fname);

        System.out.println("Gradle files : "+GradleFile.size());
        System.out.println("Java Files : "+JFiles.size());
        System.out.println("Manifest files : "+ManifFile.size());

        // Read gradle files

        for (int i=0;i<GradleFile.size();i++){
            GradleFIleReader gd = new GradleFIleReader();
            GradleDetails gds = gd.getGradleDetails(GradleFile.get(i));
        }

        GradleFIleReader grd = new GradleFIleReader();
        ReplaceLine replaceLine = new ReplaceLine();
        File fl = new File(fname);
        replaceLine.replaceSelected(fl,"ss","ss");

        List<JavaLineDetails> alllines = new ArrayList<>();
        for(int i=0;i<JFiles.size();i++){

            JavaFileReader jr = new JavaFileReader();
            List<JavaLineDetails> jrd =  jr.readDetails(JFiles.get(i));
            alllines.addAll(jrd);
            try {
                long lines = Files.lines(JFiles.get(i).toPath()).count();
                System.out.println(lines);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        for (int k=0;k<alllines.size();k++){
//            System.out.println(alllines.get(k).getFileP()+" : "+alllines.get(k).getLineNumber()+" : "+alllines.get(k).getCodeLine());
//        }
   //     System.out.println("all lines: "+alllines.size());


        for(int i =0;i<ManifFile.size();i++){
            ManifestFileReader mr = new ManifestFileReader();
            ManifestDetails mfd = mr.readDetails(ManifFile.get(i));
        }
    }

    public static List<File> listf(String directoryName) {

        File directory = new File(directoryName);
        List<File> resultList = new ArrayList<>();

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
                if(type.equals("gradle") && file.getAbsolutePath().contains("build.gradle") && file.getAbsolutePath().contains("app")){
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