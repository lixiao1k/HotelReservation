package vo;

public class addHotelVO {
	String address;
	String company;
	String hotelname;
	String summary;
	String institution;
	String serve;
	String star;
	
	
	public addHotelVO(String address,String company,String hotelname,String summary,String institution,String serve,String star)
	{
		this.address=address;
		this.company=company;
		this.hotelname=hotelname;
		this.summary=summary;
		this.institution=institution;
		this.serve=serve;
		this.star=star;
	}
}