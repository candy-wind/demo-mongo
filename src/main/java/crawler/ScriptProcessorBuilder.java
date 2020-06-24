package crawler;

import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ScriptProcessorBuilder {

    private static final Language DefaultLanguage = Language.JavaScript;

    private Language language = DefaultLanguage;

    private String script;

    private int threadNum = 1;

    private ScriptProcessorBuilder() {
    }

    public static ScriptProcessorBuilder custom() {
        return new ScriptProcessorBuilder();
    }

    public ScriptProcessorBuilder language(Language language) {
        this.language = language;
        return this;
    }

    public ScriptProcessorBuilder scriptFromFile(String fileName) {
        InputStream resourceAsStream =null;
        try {
            resourceAsStream = new FileInputStream(fileName);
            this.script = IOUtils.toString(resourceAsStream);
        } catch (IOException e) {
            //wrap IOException because I prefer a runtime exception...
            throw new IllegalArgumentException(e);
        }finally {
            if(resourceAsStream!=null) {
                try {
                    resourceAsStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return this;
    }

    public ScriptProcessorBuilder scriptFromClassPathFile(String fileName) {
        InputStream resourceAsStream =null;
        try {
            resourceAsStream = ScriptProcessor.class.getClassLoader().getResourceAsStream(fileName);
            this.script = IOUtils.toString(resourceAsStream);
        } catch (IOException e) {
            //wrap IOException because I prefer a runtime exception...
            throw new IllegalArgumentException(e);
        }finally {
            if(resourceAsStream!=null) {
                try {
                    resourceAsStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return this;
    }

    public ScriptProcessorBuilder script(String script) {
        this.script = script;
        return this;
    }

    public ScriptProcessorBuilder thread(int threadNum) {
        this.threadNum = threadNum;
        return this;
    }

    public ScriptProcessor build(){
        return new ScriptProcessor(language,script,threadNum);
    }

}
