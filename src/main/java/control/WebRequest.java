package control;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;

import exception.RequestDataErrorException;

public class WebRequest {
	private HttpURLConnection _con;
	private String _header,_method,_subUrl;
	private boolean _doInput=true,_doOutput=false;
	private JSONObject _body=null;
	private HashMap<String,String> _paramList = new HashMap<String, String>();
	
	public WebRequest(){
		_header=null;
		set_method(null);
		set_subUrl(null);
	}
	public void setBody(JSONObject data) {
		// TODO Auto-generated method stub
		_body=data;
	}

	public void setHeader(String string) {
		// TODO Auto-generated method stub
		_header=string;
		try {
			parseHeader();
		} catch (RequestDataErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void parseHeader() throws RequestDataErrorException {
		// TODO Auto-generated method stub
		String pattern="^(.*) (.*) (HTTP.*)$";
		Matcher m=Pattern.compile(pattern).matcher(_header);
		if(m.find()){
			set_method(m.group(1));
			set_subUrl(m.group(2));
			parseMethod();
			
		}else{
			throw new RequestDataErrorException("Please check your header content.  It's invalid!");
		}
	}
	private void parseMethod() {
		// TODO Auto-generated method stub
		if(_method.contains("POST")){
			_doOutput=true;
		} 
	}
	public void setContentType(String string) {
		// TODO Auto-generated method stub
		_paramList.put("Content-type", string);
	}

	public HttpURLConnection wrapper(HttpURLConnection urlConn) throws Exception{
		// TODO Auto-generated method stub
		
			URL oldURL=urlConn.getURL();
			URL newURL=new URL(oldURL.toString()+_subUrl);
			_con=(HttpURLConnection) newURL.openConnection();
			for(Entry<String, String> entry : _paramList.entrySet()) {
				_con.setRequestProperty(entry.getKey(), entry.getValue());
			}
			_con.setDoInput(_doInput);
			_con.setDoOutput(_doOutput);
			return _con;
	}
	public String get_subUrl() {
		return _subUrl;
	}
	public void set_subUrl(String _subUrl) {
		this._subUrl = _subUrl;
	}
	public String get_method() {
		return _method;
	}
	public void set_method(String _method) {
		this._method = _method;
	}
	public JSONObject getBody() {
		// TODO Auto-generated method stub
		return _body;
	}
}
