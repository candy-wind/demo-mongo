//package ThreadTest;
//
//import com.alibaba.fastjson.JSONObject;
//
//import java.util.concurrent.Callable;
//
///**
// * @Author candy-wind
// * @Data: 2020-04-08 13:42
// * @Version 1.0
// */
//public class SearchThread implements Callable<JSONObject> {
//    private JSONObject json;
//
//    public SearchThread(JSONObject json){
//        this.json = json;
//    }
//
//    @Override
//    public JSONObject call() throws Exception {
//        //构造一个StringBuilder对象
//        StringBuilder sb = new StringBuilder(json.getString("name"));
//        //在指定的位置，插入指定的字符串
//        sb.insert(0, "[");
//        sb.insert(2,"]");
//
//        try{
////            client = JSchSshUtilsFactory.createJschSession(json.getString("ip"), json.getString("username"),
////                    json.getString("password"));//执行
//            //查询当前连接数
//            String linkers = client.execCommand(ShellEnum.INSTANCE_LINKERS.getCode());
//            json.put("linkers",Integer.parseInt(linkers.replace("\n",""))-1);
//            //查询当前运行状态
//            String runStatus = client.execCommand("ps -ef|grep "+sb.toString()+" |wc -l");
//            json.put("runStatus",runStatus.replace("\n",""));
//            //查询实例用户数
////            String users = client.execCommand(ShellEnum.INSTANCE_USERS.getCode());
////            json.put("users",users.replace("\n",""));
////            json.put("result","success");
//
//        }catch (Exception e){
//            log.error(e.getMessage(),e);
//            json.put("result","fail");
//            json.put("msg",e.getMessage());
//        }finally {
//            JSchSshUtilsFactory.closeJschSession(client);
//            log.info("关闭SSH连接");
//        }
//        return json;
//    }
//
//}
