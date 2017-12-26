package com.pku.codingma.client;


        import java.io.BufferedReader;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.io.UnsupportedEncodingException;
        import java.net.HttpURLConnection;
        import java.net.URL;
        import net.sf.json.*;

public class HttpUtil {
    //封装的发送请求函数
    public static void sendHttpRequest(final String address, final StaticUsr user, final HttpCallbackListener listener) {
        if (!HttpUtil.isNetworkAvailable()){
            //这里写相应的网络设置处理
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try{
                    URL url = new URL(address);
                    //使用HttpURLConnection
                    connection = (HttpURLConnection) url.openConnection();
                    //设置方法和参数
                    connection.setRequestMethod("POST");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    //获取返回结果
                    InputStream inputStream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null){
                        response.append(line);
                    }
                    //成功则回调onFinish
                    if (listener != null){
                        listener.onFinish(response.toString());
                    }
                    JSONObject js=net.sf.json.JSONObject.fromObject(response.toString());
                    String usrId= js.getString("usrId");
                    String usrPassword= js.getString("usrPassword");
                    String usrName= js.getString("usrName");
                    int usrSex = Integer.parseInt(js.getString("usrSex"));
                    int usrType = Integer.parseInt(js.getString("usrType"));
                    user.setUrsPassword(usrPassword);
                    user.setUrsType(usrType);
                    user.setUsrId(usrId);
                    user.setUsrSex(usrSex);
                    user.setUserName(usrName);
                } catch (Exception e) {
                    e.printStackTrace();
                    //出现异常则回调onError
                    if (listener != null){
                        listener.onError(e);
                    }
                }finally {
                    if (connection != null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
    //组装出带参数的完整URL
    public static String getURLWithParams(String address,User params) throws UnsupportedEncodingException {
        //设置编码
        final String encode = "UTF-8";
        StringBuilder url = new StringBuilder(address);
        url.append("?");
        //将map中的key，value构造进入URL中
        url.append("UsrId").append("=").append(params.getUsrId()).append("&");
        url.append("UsrPassword").append("=").append(params.getUrsPassword()).append("&");
        url.append("UsrType").append("=").append(params.getUrsType());
        return url.toString();
    }
    //判断当前网络是否可用
    public static boolean isNetworkAvailable(){
        return true;
    }

}
