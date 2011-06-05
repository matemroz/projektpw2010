package pw.childcontrol.gui;

import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class AnswerFromServerThread  implements Runnable {

	HttpParams params = null;
	ClientConnectionManager conMgr = null;
	private String odpowiedz = null;
	private Handler uiCallback = null;
	private ProgressDialog progressDialog = null;
	private Context context = null;
	List<NameValuePair> postParameters = null;
	String url = null;
	
	public AnswerFromServerThread(HttpParams params, ClientConnectionManager conMgr, Handler uiCallback,
			ProgressDialog progressDialog, Context context, List<NameValuePair> postParameters, String url ){
		this.params = params;
		this.conMgr = conMgr;
		this.uiCallback = uiCallback;
		this.progressDialog = progressDialog;
		this.context = context;
		this.postParameters = postParameters;
		this.url = url;
	}
	
	@Override
	public void run() {
		try {
			Looper.prepare();	//

			/*
			HttpGet request = new HttpGet();
			request.setURI(new URI("http://www.onet.pl/"));		
			HttpClient client = new DefaultHttpClient(conMgr, params);
			HttpResponse response = client.execute(request);
			*/
			
			//http://188.116.3.68/serwer/logika/gettest.php
			HttpGet request = new HttpGet();
			request.setURI(new URI(url));
			/*
			if(postParameters.size() > 0){
				Log.i("POSTPARAMWATEK",postParameters.get(0).getValue());
			}
			*/
			
			UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(postParameters);
			//request.setEntity(formEntity);
			
			HttpClient client = new DefaultHttpClient();//conMgr, params);
			//HttpGet request2 = new HttpGet();
			
			/*
			//for (int i = 0; i < 255; i++) {
			//	for(int j = 0; j< 255; j++){
				//	try{
					request2.setURI(new URI("http://10.0.2.2:7777/About/About.do"));
					HttpResponse response2 = client.execute(request2);
					String odp2 = new String(EntityUtils.toString(response2.getEntity()));
				
					Log.d("GET", odp2);
					//} catch(Exception e){
					//	Log.d("Excep", e.getMessage());
					//}
			//	}
			//}
			*/
			HttpResponse response = client.execute(request);
			
			
			odpowiedz = new String(EntityUtils.toString(response.getEntity(), HTTP.UTF_8));
			Message m = Message.obtain(uiCallback);
			m.obj = odpowiedz;
			uiCallback.sendMessage(m);
			Log.i("WATEK","Wysłałem odpowiedź serwera.");
			
			
		} catch (ClientProtocolException e) {
			progressDialog.dismiss();
			//Alerts.showAlert("Błąd na poziomie protokołu transmisji. Uruchom ponownie aplikacje.", "Bład!", context);
			e.printStackTrace();
		} catch (URISyntaxException e) {
			progressDialog.dismiss();
			//Alerts.showAlert("Niepoprawne zapytanie http!", "Bład!", context);
			e.printStackTrace();
		} catch(SocketTimeoutException e) {
			progressDialog.dismiss();
			//Alerts.showAlert("Przekroczono limit czasu połączenia.", "Bład!", context);
			e.printStackTrace();
		} catch(SocketException e){
			progressDialog.dismiss();
			//Alerts.showAlert("Problemy z połączeniem sieciowym.", "Bład!", context);
			e.printStackTrace();
		} catch (IOException e) {
			progressDialog.dismiss();
			//Alerts.showAlert("Niepoprawne zapytanie http!", "Bład!", context);
			e.printStackTrace();
		} catch (IllegalStateException e) {
			progressDialog.dismiss();					
			e.printStackTrace();
			//Alerts.showAlert("Niepoprawne zapytanie http!", "Bład!", context);
		} catch(IllegalArgumentException e){
			progressDialog.dismiss();
			//Alerts.showAlert("Niepoprawne zapytanie http", "Bład!", context);
			e.printStackTrace();
		}  catch(Exception e){
			progressDialog.dismiss();
			//Alerts.showAlert("Nieoczekiwany błąd!", "Bład!", context);
			e.printStackTrace();
		}
		
		Looper.loop();
	}

}
