package util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Blob;

import javax.imageio.ImageIO;

import org.hibernate.Hibernate;

import po.strategies.StrategyRule;

/**
 * 序列化与反序列化处理的工具类
 * @author Whk
 *
 */
public class SerializeUtil {
	public static Image byteToImage(byte[] data){
		InputStream in = new ByteArrayInputStream(data);
		try {
			BufferedImage img = ImageIO.read(in);
			return img;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static byte[] imageToByte(Image image){
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		try {
			ImageIO.write((BufferedImage)image, "png", out);
			byte[] bytes = out.toByteArray();
			return bytes;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public static Blob objectToBlob(Object obj){
	       try {
	    	   	
	            ByteArrayOutputStream out = new ByteArrayOutputStream();  
	            ObjectOutputStream outputStream = new ObjectOutputStream(out);  
	            outputStream.writeObject(obj);  
	            byte[] bytes = out.toByteArray();  
	            outputStream.close();  
	            return Hibernate.createBlob(bytes);  
	        } catch (Exception e) {  
	        	e.printStackTrace();
	            return null;  
	        }
	}
	public static StrategyRule blobToStrategyRule(Blob blob){
        try {  
            Object obj = null;  
            ObjectInputStream in = new ObjectInputStream(  
                    blob.getBinaryStream());  
            obj = in.readObject();  
            in.close();  
            return (StrategyRule)obj;  
        } catch (Exception e) {   
            e.printStackTrace();  
        }  
        return null;  
    } 
}
