//package ThreadTest;
//
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONException;
//import com.alibaba.fastjson.JSONObject;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.Future;
//
///**
// * @Author candy-wind
// * @Data: 2020-04-08 10:45
// * @Version 1.0
// */
//public class AAAThreadHttpRequest {
//    //首页返回所需的参数和接口，电影接口
//    //热门电影
//    private String hotfilmUrl = "http://expand.video.iqiyi.com/api/top/list.json?apiKey=?&topType=1&categoryId=1&limit=30&sr=1";
//    //热门电视剧
//    private String hotdianshijuUrl = "http://expand.video.iqiyi.com/api/top/list.json?apiKey=?&topType=1&categoryId=2&limit=5&sr=2";
//    //热门动漫
//    private String hotanimationUrl = "http://expand.video.iqiyi.com/api/top/list.json?apiKey=?&topType=1&categoryId=4&limit=5&sr=3";
//    //第一个分段数据
//    private String segmentOneUrl ="http://expand.video.iqiyi.com/api/top/list.json?apiKey=?&topType=1&categoryId=7&limit=6&sr=5";
//
//    //淘宝客
//
//
//    public LinkedList<JSONObject> ShiPinThreadHandle() throws JSONException, IOException, InterruptedException, ExecutionException
//    {
//        //组合线程请求参数
//        JSONArray requestParameterArray=new JSONArray();
//        JSONObject  a=new JSONObject();
//        a.put("requestUrl", hotfilmUrl);
//        a.put("dataType", "hotFilm");
//        a.put("type", "shipin");
//
//        JSONObject  a1=new JSONObject();
//        a1.put("requestUrl", hotdianshijuUrl);
//        a1.put("dataType", "hotDianshiju");
//        a1.put("type", "shipin");
//
//        JSONObject  a2=new JSONObject();
//        a2.put("requestUrl", hotanimationUrl);
//        a2.put("dataType", "hotDongman");
//        a2.put("type", "shipin");
//
//
//        JSONObject  a3=new JSONObject();
//        a3.put("requestUrl", segmentOneUrl);
//        a3.put("dataType", "firstSegmentData");
//        a3.put("type", "shipin");
//
//
//
//        requestParameterArray.add(a);
//        requestParameterArray.add(a1);
//        requestParameterArray.add(a2);
//        requestParameterArray.add(a3);
//
//        //申明线程池
//        ExecutorService exc = Executors.newFixedThreadPool(requestParameterArray.size());
//        //申明数据回调处理类List<Future<JSONObject>>
//        List<Future<JSONObject>> futures = new ArrayList<Future< JSONObject>>();
//        for (int i =0; i < requestParameterArray.size(); i++) {
//
//            JSONObject singleobje=requestParameterArray.getJSONObject(i);
//            //申请单个线程执行类
//            ShiPinThreadHandleRequest call =new ShiPinThreadHandleRequest(singleobje);
//            //提交单个线程
//            Future< JSONObject> future = exc.submit(call);
//            //将每个线程放入线程集合， 这里如果任何一个线程的执行结果没有回调，线程都会自动堵塞
//            futures.add(future);
//
//        }
//        //所有线程执行完毕之后会执行下面的循环，然后通过循环每个个线程后执行线程的get()方法每个线程执行的结果
//        for (Future< JSONObject> future : futures) {
//
//            JSONObject json= future.get();
//
//            AAAANewAppShareSingleton.getInstance().homePageSessionDictionary.put(json.getString("dataType"), json.getJSONArray("returnData"));
//
//        }
//        AAAANewAppShareSingleton.getInstance().homeIsOrNoReturn=1;
//
//        //关闭线程池
//        exc.shutdown();
//
//
//
//        //这里由于我直接将返回结果放入到单利中缓存了，所有返回null
//        return null;
//
//}
