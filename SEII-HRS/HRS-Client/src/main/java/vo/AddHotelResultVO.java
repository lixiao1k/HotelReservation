package vo;

import java.io.Serializable;

import resultmessage.HotelResultMessage;

public class AddHotelResultVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7477583905528412059L;
	private HotelResultMessage resultMessage;
	private HotelWorkerVO hotelworker;
	private long hotelId;
	public void setHotelId(long hotelId){
		this.hotelId = hotelId;
	}
	public void setHotelResultMessage(HotelResultMessage resultmessage){
		this.resultMessage = resultmessage;
	}
	public void setHotelWorker(HotelWorkerVO hotelworker){
		this.hotelworker = hotelworker;
	}
	public long getHotelId(){
		return hotelId;
	}
	public HotelWorkerVO getHotelWorker(){
		return hotelworker;
	}
	public HotelResultMessage getResultMessage(){
		return resultMessage;
	}
}
