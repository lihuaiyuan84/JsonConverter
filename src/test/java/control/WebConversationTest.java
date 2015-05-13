package control;
import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Test;

import control.WebConversation;
import control.WebRequest;
import control.WebResponse;


public class WebConversationTest {

	@Test
	public void test() {
		WebConversation conv= new WebConversation("127.0.0.1");
		WebRequest wrequest= new WebRequest();
		WebResponse wresponse=new WebResponse();
		conv.setPort("9001");
		wrequest.setHeader("header");
		JSONObject data=new JSONObject("{aaa:\"aaa\"}");
		wrequest.setBody(data);
		wrequest.setContentType("json");
		conv.handshake(wrequest,wresponse);
		JSONObject dataOutput=wresponse.getData();
		assertTrue(wresponse.get()==null);
		//fail("Not yet implemented");
	}

}
