package FileReader.ManifestReader;

import java.util.List;

public class ManifestDetails {
    private String usesSdk;
    private List<String> usesPermission;
    private List<String> services;
    private List<ManifestLineDetails> codeDetails;

    public ManifestDetails(String usesSdk, List<String> usesPermission, List<String> services, List<ManifestLineDetails> details) {
        this.usesSdk = usesSdk;
        this.usesPermission = usesPermission;
        this.services = services;
        this.codeDetails = details;
    }

    public String getUsesSdk() {
        return usesSdk;
    }

    public void setUsesSdk(String usesSdk) {
        this.usesSdk = usesSdk;
    }

    public List<String> getUsesPermission() {
        return usesPermission;
    }

    public void setUsesPermission(List<String> usesPermission) {
        this.usesPermission = usesPermission;
    }

    public List<String> getServices() {
        return services;
    }

    public void setServices(List<String> services) {
        this.services = services;
    }

    public List<ManifestLineDetails> getCodeDetails() {
        return codeDetails;
    }

    public void setCodeDetails(List<ManifestLineDetails> codeDetails) {
        this.codeDetails = codeDetails;
    }
}
