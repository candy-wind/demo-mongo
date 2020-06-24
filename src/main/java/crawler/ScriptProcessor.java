package crawler;


import org.apache.commons.io.IOUtils;

import javax.script.Invocable;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import java.io.IOException;
import java.io.InputStream;

public class ScriptProcessor{

    private ScriptEnginePool enginePool;

    private String defines;

    private String script;

    private final Language language;

    public ScriptProcessor(Language language, String script, int threadNum) {
        if (language == null || script == null) {
            throw new IllegalArgumentException("language and script must not be null!");
        }
        this.language = language;
        enginePool = new ScriptEnginePool(language, threadNum);
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(language.getDefineFile());
        try {
            defines = IOUtils.toString(resourceAsStream);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }finally {
            if(resourceAsStream!=null){
                try {
                    resourceAsStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        this.script = script;
    }

    public String process(String method, Object... params) {
        String result = null;
        ScriptEngine engine = enginePool.getEngine();
        try {
            ScriptContext context = engine.getContext();
            try {
                switch (language) {
                    case JavaScript:
                        // 转换为Invocable
                        Invocable invocableEngine = (Invocable) engine;
                        engine.eval(defines + "\n" + script, context);
                        // 带参数调用sayHello方法
                        result=  (String)invocableEngine.invokeFunction(method, params);
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            enginePool.release(engine);
        }
        return result;
    }

    public Object processObject(String method, Object... params) {
        Object result = null;
        ScriptEngine engine = enginePool.getEngine();
        try {
            ScriptContext context = engine.getContext();
            try {
                switch (language) {
                    case JavaScript:
                        // 转换为Invocable
                        Invocable invocableEngine = (Invocable) engine;
                        engine.eval(defines + "\n" + script, context);
                        // 带参数调用sayHello方法
                        result=  invocableEngine.invokeFunction(method, params);
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            enginePool.release(engine);
        }
        return result;
    }

}
