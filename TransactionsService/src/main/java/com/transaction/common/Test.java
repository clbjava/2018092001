package com.transaction.common;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

public class Test {
	
    public static final String GET_URL = " http://localhost:8080/welcome1 ";    
    
    public static final String POST_URL = " http://localhost:8080/welcome1 ";     
    
	/**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
    	PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }    


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			String uri="http://127.0.0.1:8070/transactionServer/query/"+URLEncoder.encode(" hello world", "UTF-8");
			URL url=new URL(uri);
			HttpURLConnection con=(HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setConnectTimeout(5000);
			InputStream input=con.getInputStream();
			byte[] btyes=new byte[1024];
			
			while(input.read(btyes)<0) {
				input.close();
			}
			System.out.println(new String(btyes,"UTF-8"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
    public static void readContentFromGet() throws IOException {    
        // 拼凑get请求的URL字串，使用URLEncoder.encode对特殊和不可见字符进行编码    
        String getURL = GET_URL + " ?username= "    
                + URLEncoder.encode(" fat man ", " utf-8 ");    
        URL getUrl = new URL(getURL);    
        // 根据拼凑的URL，打开连接，URL.openConnection函数会根据 URL的类型，    
        // 返回不同的URLConnection子类的对象，这里URL是一个http，因此实际返回的是HttpURLConnection    
        HttpURLConnection connection = (HttpURLConnection) getUrl    
                .openConnection();    
        // 进行连接，但是实际上get request要在下一句的 connection.getInputStream()函数中才会真正发到    
        // 服务器    
        connection.connect();    
        // 取得输入流，并使用Reader读取    
        BufferedReader reader = new BufferedReader(new InputStreamReader(    
                connection.getInputStream()));    
        System.out.println(" ============================= ");    
        System.out.println(" Contents of get request ");    
        System.out.println(" ============================= ");    
        String lines;    
        while ((lines = reader.readLine()) != null) {    
            System.out.println(lines);    
        }    
        reader.close();    
        // 断开连接    
        connection.disconnect();    
        System.out.println(" ============================= ");    
        System.out.println(" Contents of get request ends ");    
        System.out.println(" ============================= ");    
    }    
    
    public static void readContentFromPost() throws IOException {    
        // Post请求的url，与get不同的是不需要带参数    
        URL postUrl = new URL(POST_URL);    
        // 打开连接    
        HttpURLConnection connection = (HttpURLConnection) postUrl    
                .openConnection();    
        // Output to the connection. Default is    
        // false, set to true because post    
        // method must write something to the    
        // connection    
        // 设置是否向connection输出，因为这个是post请求，参数要放在    
        // http正文内，因此需要设为true    
        connection.setDoOutput(true);    
        // Read from the connection. Default is true.    
        connection.setDoInput(true);    
        // Set the post method. Default is GET    
        connection.setRequestMethod(" POST ");    
        // Post cannot use caches    
        // Post 请求不能使用缓存    
        connection.setUseCaches(false);    
        // This method takes effects to    
        // every instances of this class.    
        // URLConnection.setFollowRedirects是static 函数，作用于所有的URLConnection对象。    
        // connection.setFollowRedirects(true);    
    
        // This methods only    
        // takes effacts to this    
        // instance.    
        // URLConnection.setInstanceFollowRedirects 是成员函数，仅作用于当前函数    
        connection.setInstanceFollowRedirects(true);    
        // Set the content type to urlencoded,    
        // because we will write    
        // some URL-encoded content to the    
        // connection. Settings above must be set before connect!    
        // 配置本次连接的Content-type，配置为application/x- www-form-urlencoded的    
        // 意思是正文是urlencoded编码过的form参数，下面我们可以看到我们对正文内容使用URLEncoder.encode    
        // 进行编码    
        connection.setRequestProperty(" Content-Type ",    
                " application/x-www-form-urlencoded ");    
        // 连接，从postUrl.openConnection()至此的配置必须要在 connect之前完成，    
        // 要注意的是connection.getOutputStream会隐含的进行 connect。    
        connection.connect();    
        DataOutputStream out = new DataOutputStream(connection    
                .getOutputStream());    
        // The URL-encoded contend    
        // 正文，正文内容其实跟get的URL中'?'后的参数字符串一致    
        String content = " firstname= "    
                + URLEncoder.encode(" 一个大肥人 ", " utf-8 ");    
        // DataOutputStream.writeBytes将字符串中的16位的 unicode字符以8位的字符形式写道流里面    
        out.writeBytes(content);    
    
        out.flush();    
        out.close(); // flush and close    
        BufferedReader reader = new BufferedReader(new InputStreamReader(    
                connection.getInputStream()));    
        String line;    
        System.out.println(" ============================= ");    
        System.out.println(" Contents of post request ");    
        System.out.println(" ============================= ");    
        while ((line = reader.readLine()) != null) {    
            System.out.println(line);    
        }    
        System.out.println(" ============================= ");    
        System.out.println(" Contents of post request ends ");    
        System.out.println(" ============================= ");    
        reader.close();    
        connection.disconnect();    
    }  

}

