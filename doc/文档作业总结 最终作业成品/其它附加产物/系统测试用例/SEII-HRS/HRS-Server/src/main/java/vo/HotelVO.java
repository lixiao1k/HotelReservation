package vo;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.imageio.ImageIO;

import info.BusinessCircle;
import info.BusinessCity;

public class HotelVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long hid;
	private String name;
	private String description;
	private String facility;
	private String address;
	private String service;
	private BusinessCircle businessCircle;
	private BusinessCity businessCity;
	private transient Image image;
	public long getHid() {
		return hid;
	}
	public void setHid(long hid) {
		this.hid = hid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFacility() {
		return facility;
	}
	public void setFacility(String facility) {
		this.facility = facility;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public BusinessCircle getBusinessCircle() {
		return businessCircle;
	}
	public void setBusinessCircle(BusinessCircle businessCircle) {
		this.businessCircle = businessCircle;
	}
	public BusinessCity getBusinessCity() {
		return businessCity;
	}
	public void setBusinessCity(BusinessCity businessCity) {
		this.businessCity = businessCity;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        if(image!=null)
        	ImageIO.write((BufferedImage) image, "png", out);
    }
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
	    in.defaultReadObject();
	    image=ImageIO.read(in);    
	}
}
