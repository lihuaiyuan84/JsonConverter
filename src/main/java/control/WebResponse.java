package control;

import java.io.InputStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;

import exception.ResponseDataErrorException;

public class WebResponse {

	String _str;
	JSONObject _body;
	public Object get() {
		// TODO Auto-generated method stub
		return _body;
	}

	public JSONObject getData() {
		// TODO Auto-generated method stub
		return _body;
	}

	public void receive(InputStream in) throws ResponseDataErrorException {
		// TODO Auto-generated method stub
		_str = parseInputStream(in);
		extractJsonBody();
	}

	private void extractJsonBody() throws ResponseDataErrorException {
		String pattern="^[^\\{]*(\\{.*\\})[^\\}]*$";
		Matcher m=Pattern.compile(pattern).matcher(_str);
		if(m.find()){
			System.out.println(m.group(1));
			_body=new JSONObject(m.group(1));
		}else{
			throw new ResponseDataErrorException("Response data is invalid!");
		}
	}

	private String parseInputStream(InputStream in) {
		Scanner scanner = wrapper(in).useDelimiter("\\A");
		String tmp=scanner.hasNext()? scanner.next():""; 
		System.out.println(tmp);
		scanner.close();
		return tmp;
	}

	private Scanner wrapper(InputStream in) {
		return new Scanner(in,"UTF-8");
	}



}
