package crawler;

public enum Language {

    JavaScript("javascript","js/defines.js","");

    private String engineName;

    private String defineFile;

    private String gatherFile;

    Language(String engineName, String defineFile, String gatherFile) {
        this.engineName = engineName;
        this.defineFile = defineFile;
        this.gatherFile = gatherFile;
    }

    public String getEngineName() {
        return engineName;
    }

    public String getDefineFile() {
        return defineFile;
    }

    public String getGatherFile() {
        return gatherFile;
    }
}
