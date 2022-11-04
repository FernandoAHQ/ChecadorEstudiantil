package services;

import java.io.File;
import java.io.IOException;  
import java.util.HashMap;  
import java.util.Map;  
import com.google.zxing.BarcodeFormat;  
import com.google.zxing.EncodeHintType;  
import com.google.zxing.MultiFormatWriter;  
import com.google.zxing.NotFoundException;  
import com.google.zxing.WriterException;  
import com.google.zxing.client.j2se.MatrixToImageWriter;  
import com.google.zxing.common.BitMatrix;  
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;  
public class QRGenerator  
{  
//static function that creates QR Code  
public static void generateQRcode(String data) throws WriterException, IOException  
{  
	data = "ENT" + data;
	System.out.println(data);
	String path = "src/assets/code/qr.png";
//the BitMatrix class represents the 2D matrix of bits  
//MultiFormatWriter is a factory class that finds the appropriate Writer subclass for the BarcodeFormat requested and encodes the barcode with the supplied contents.  
BitMatrix matrix = new MultiFormatWriter().encode(new String( data.getBytes("UTF-8"), "UTF-8"), BarcodeFormat.QR_CODE, 400, 400);  
MatrixToImageWriter.writeToFile(matrix, path.substring(path.lastIndexOf('.') + 1), new File(path));  
}  

}  