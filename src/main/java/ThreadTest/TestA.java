package ThreadTest;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

/**
 * @Author candy-wind
 * @Data: 2020-04-09 10:54
 * @Version 1.0
 */
public class TestA {
    ExecutorService exc = Executors.newFixedThreadPool(2);

    @Test
    public void current(){
        Future<JSONObject> future = exc.submit(new Callable<JSONObject>() {
            @Override
            public JSONObject call() throws Exception {
//                result = junYuH5FaceService.execute(param);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("232","a");
                System.out.println("wewew"+Thread.currentThread().getName());
                return jsonObject;
            }
        });


        Future<JSONObject> future1 = exc.submit(new Callable<JSONObject>() {
            @Override
            public JSONObject call() throws Exception {
//                result = junYuH5FaceService.execute(param);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("232","ga");
                System.out.println("wewewadfa"+Thread.currentThread().getName());
                return jsonObject;
            }
        });

        try {
//            System.out.println(future.get());
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        try {
//            System.out.println(future1.get());
            future1.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }




}
