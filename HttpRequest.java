package me.zhengjie.modules.zstp.utils;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * 请求对象包装类
 * @author Zhao Xiaolei
 *
 */
public class HttpRequest {
	
	private Connection connection = null;
	
	public static HttpRequest create(){
		return new HttpRequest();
	}
	
	public HttpRequest url(String url){
		this.connection = Jsoup.connect(url);
		this.connection.method(Method.GET);
		this.connection.timeout(60000);
		return this;
	}
	
	public HttpRequest headers(Map<String,String> headers){
		this.connection.headers(headers);
		return this;
	}
	
	public HttpRequest header(String key,String value){
		this.connection.header(key, value);
		return this;
	}
	
	public HttpRequest header(String key,Object value){
		if(value != null){
			this.connection.header(key,value.toString());
		}
		return this;
	}

	public HttpRequest cookies(Map<String,String> cookies){
		this.connection.cookies(cookies);
		return this;
	}

	public HttpRequest cookie(String name, String value) {
		if (value != null) {
			this.connection.cookie(name, value);
		}
		return this;
	}
	
	public HttpRequest contentType(String contentType){
		this.connection.header("Content-Type", contentType);
		return this;
	}
	
	public HttpRequest data(String key,String value){
		this.connection.data(key, value);
		return this;
	}
	
	public HttpRequest data(String key,Object value){
		if(value != null){
			this.connection.data(key, value.toString());
		}
		return this;
	}
	
	public HttpRequest data(String key,String filename,InputStream is){
		this.connection.data(key, filename, is);
		return this;
	}
	
	public HttpRequest data(Object body){
		if(body != null){
			this.connection.requestBody(body.toString());	
		}
		return this;
	}
	
	public HttpRequest data(Map<String,String> data){
		this.connection.data(data);
		return this;
	}
	
	public HttpRequest method(String method){
		this.connection.method(Method.valueOf(method));
		return this;
	}
	
	public HttpRequest followRedirect(boolean followRedirects){
		this.connection.followRedirects(followRedirects);
		return this;
	}
	
	public HttpRequest timeout(int timeout){
		this.connection.timeout(timeout);
		return this;
	}
	
	public HttpRequest proxy(String host,int port){
		this.connection.proxy(host, port);
		return this;
	}
	
	@SuppressWarnings("deprecation")
	public HttpRequest validateTLSCertificates(boolean value){
		this.connection.validateTLSCertificates(value);
		return this;
	}
	
	public HttpResponse execute() throws IOException{
		this.connection.ignoreContentType(true);
		this.connection.ignoreHttpErrors(true);
		this.connection.maxBodySize(0);

		Response response = connection.execute();
		return new HttpResponse(response);
	}
	//组装url
	public String buildUrl(String url, Map<String, String> parameters) {
		if (null == parameters) {
			return url;
		}
		String newUrl = parameters.entrySet().stream().map(entry -> String.format("%s=%s",
				entry.getKey(), entry.getValue()))
				.reduce(url, (prev, curr) -> {
					if (prev.equals(url)) {
						return String.format("%s?%s", url, curr);
					}
					return String.format("%s&%s", prev, curr);
				});
		return newUrl;
	}
}
