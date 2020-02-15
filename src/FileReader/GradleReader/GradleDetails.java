package FileReader.GradleReader;

import java.io.File;
import java.util.List;

public class GradleDetails {
    private File file;
    private String targetSdk;
    private String compileSdk;
    private String minSdk;
    private List<String> dependencies;
    private List<GradleLineDetails> codeDetails;

    public GradleDetails(File filep, String targetSdk, String compileSdk, String minSdk, List<String> dependencies, List<GradleLineDetails> details) {
        this.file = filep;
        this.targetSdk = targetSdk;
        this.compileSdk = compileSdk;
        this.minSdk = minSdk;
        this.dependencies = dependencies;
        this.codeDetails = details;
    }

    public String getTargetSdk() {
        return targetSdk;
    }

    public void setTargetSdk(String targetSdk) {
        this.targetSdk = targetSdk;
    }

    public String getCompileSdk() {
        return compileSdk;
    }

    public void setCompileSdk(String compileSdk) {
        this.compileSdk = compileSdk;
    }

    public String getMinSdk() {
        return minSdk;
    }

    public void setMinSdk(String minSdk) {
        this.minSdk = minSdk;
    }

    public List<String> getDependencies() {
        return dependencies;
    }

    public void setDependencies(List<String> dependencies) {
        this.dependencies = dependencies;
    }

    public List<GradleLineDetails> getCodeDetails() {
        return codeDetails;
    }

    public void setCodeDetails(List<GradleLineDetails> codeDetails) {
        this.codeDetails = codeDetails;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
