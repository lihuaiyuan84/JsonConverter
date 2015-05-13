package control;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WebConversation {
	private static final String PROTOCOL = "http://";
	private URL _url;
	private String _ip,_port=null;
	private HttpURLConnection _urlConn;
	public WebConversation(String ip) {
		// TODO Auto-generated constructor stub
		
		try {
			_ip=ip;
			_port="8080";
			_url = new URL(PROTOCOL+_ip+":"+_port);
			this.setURL(_url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public WebConversation(String ip,String port) {
		// TODO Auto-generated constructor stub
		
		try {
			_ip=ip;
			_port=port;
			_url = new URL(PROTOCOL+_ip+":"+_port);
			this.setURL(_url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public void handshake(WebRequest wrequest, WebResponse wresponse) {
		// TODO Auto-generated
		
		try {
			
			this.openConnection();
			_urlConn=wrequest.wrapper(_urlConn);
			System.out.println(_urlConn.getURL().toString());
			_urlConn.connect();
			
			DataOutputStream out = new DataOutputStream(_urlConn.getOutputStream());
			out.writeBytes(wrequest.getBody().toString());
			out.flush();
			InputStream in = _urlConn.getInputStream();
			wresponse.receive(in);
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}


	private void openConnection() throws IOException {
		_urlConn = (HttpURLConnection) _url.openConnection();
		
	}

	public void setURL(URL url) {
		// TODO Auto-generated method stub
		
	}


	public void setPort(String port) {
		// TODO Auto-generated method stub
		_port=port;
		try {
			_url = new URL(PROTOCOL+_ip+":"+_port);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
