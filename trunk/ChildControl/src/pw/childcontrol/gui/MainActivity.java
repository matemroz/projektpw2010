package pw.childcontrol.gui;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

public class MainActivity extends Activity {
	
	private HttpParams params = new BasicHttpParams();
	private ClientConnectionManager conMgr;
	private Context context = this;
	private ProgressDialog progressDialog;
	private Thread watekStatystyk = null;
	private TextView tv;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        tv = (TextView)findViewById(R.id.textView_Main_Text);
        tv.setText("tekst dynamiczny");
        
        Button btn = (Button)findViewById(R.id.button_Main);
        btn.setText("Pobierz!");
        
        btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Date today = new Date();
				Random r = new Random(today.getTime());
				tv.setText(String.valueOf(r.nextLong()));
				
				List<NameValuePair> postParameters = new ArrayList<NameValuePair>();

				postParameters.add(new BasicNameValuePair("message", "przeslaneztelefonu"));
				
				HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
				HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);
				HttpProtocolParams.setHttpElementCharset(params, HTTP.UTF_8);
				HttpProtocolParams.setUseExpectContinue(params, true);
				HttpConnectionParams.setSoTimeout(params, 5000);
				
				SchemeRegistry schReg = new SchemeRegistry();
				schReg.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
				schReg.register(new Scheme("https", PlainSocketFactory.getSocketFactory(), 443));
				
				conMgr = new ThreadSafeClientConnManager(params, schReg);
				
				progressDialog = new ProgressDialog(context);
				
				String url = "http://10.0.2.2:7777/ChildControl/ParentInfo.do?id=1";
				//String url = "http://matemroz.dyndns.org:9999/About/About.do";
		    	AnswerFromServerThread watekPobierajacyNauke = new AnswerFromServerThread(params, conMgr, uiCallback, progressDialog, context, postParameters, url);
		    	watekStatystyk = new Thread(watekPobierajacyNauke);
		    	watekStatystyk.start();
		    	progressDialog.incrementProgressBy(5);
		    	Log.i("WATEK","Wątek uruchomiony");
			}
		});
    }
    
private Handler uiCallback = new Handler(){
    	
    	public void handleMessage(Message msg){
    		//tv.setText((String)msg.obj);
    		try{
	    		String pZ = null;
	    		String pN = null;
	    		String iZp = null;
	    		String iZpNj = null;
	    		
	    		String odpowiedz = new String((String)msg.obj);
	    		Log.i("ODCZYTANO:",odpowiedz);
	    		SAXParserParentInfo pi = new SAXParserParentInfo();
	    		HashMap<String, String> map = pi.parseXML(odpowiedz);
	    		if(map.containsKey(SAXParserParentInfo.CONST_PARENT_NAME)){
	    			tv.setText(map.get(SAXParserParentInfo.CONST_PARENT_NAME));
	    		}
    		} catch(Exception e){
    			e.toString();
    		}
    	}
    };
    
    
}