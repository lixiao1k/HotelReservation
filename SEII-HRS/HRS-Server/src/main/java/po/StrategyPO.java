package po;

public class StrategyPO {
	private long id;
	private String name;
	private String description;
	private long hotelId;
	private StrategyType type;
	public long getId(){
		return this.id;
	}
	public String getName(){
		return this.name;
	}
	public String getDescription(){
		return this.description;
	}
	public long getHotelId(){
		return this.hotelId;
	}
	public StrategyType getStrategyType(){
		return this.getStrategyType();
	}
	public void setStrategyType(StrategyType type){
		this.type = type;
	}
	public void setHotelId(long hotelId){
		this.hotelId = hotelId;
	}
	public void setId(long id){
		this.id = id;
	}
	public void setName(String name){
		this.name = name;
	}
}
