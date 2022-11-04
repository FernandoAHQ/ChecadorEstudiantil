import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import controllers.MainLogic;
import services.HttpService;
import services.QRGenerator;

public class Main {

	public static void main(String[] args) {
		
		
		//String title = new JSONObject(HttpService.getArticles());

		//System.out.println(HttpService.getArticles());
	
		//	System.out.print(HttpService.getArticles());
		
		
		new MainLogic();
		
		
	}

}
