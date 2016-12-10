package util;

import java.awt.Image;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Blob;

import org.hibernate.Hibernate;

import po.strategies.StrategyRule;

public class SerializeUtil {
	public static Blob objectToBlob(Object obj){
	       try {  
	            ByteArrayOutputStream out = new ByteArrayOutputStream();  
	            ObjectOutputStream outputStream = new ObjectOutputStream(out);  
	            outputStream.writeObject(obj);  
	            byte[] bytes = out.toByteArray();  
	            outputStream.close();  
	            return Hibernate.createBlob(bytes);  
	        } catch (Exception e) {  
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
	public static Image blobToImage(Blob blob){
		try {  
            Object obj = null;  
            ObjectInputStream in = new ObjectInputStream(  
                    blob.getBinaryStream());  
            obj = in.readObject();  
            in.close();  
            return (Image)obj;  
        } catch (Exception e) {   
            e.printStackTrace();  
        }  
        return null;  
	}
}
