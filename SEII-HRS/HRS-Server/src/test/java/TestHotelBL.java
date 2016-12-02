import org.junit.Test;

import info.BusinessCircle;
import info.BusinessCity;
import info.ListWrapper;
import logic.service.HotelLogicService;
import logic.service.ServiceFactory;
import logic.service.impl.ServiceFactoryImpl;
import logic.service.impl.hotel.HotelLogicServiceImpl;

import java.rmi.RemoteException;
import java.util.Iterator;

import org.junit.Assert;
public class TestHotelBL {
	@Test
	public void testGetCity() throws RemoteException{
		HotelLogicService hotel = new HotelLogicServiceImpl();
		ListWrapper<BusinessCity> cities = hotel.getCity();
		Assert.assertNotEquals("wrong!", null,cities);
		Iterator<BusinessCity> it = cities.iterator();
		while(it.hasNext()){
			BusinessCity bc = it.next();
			Iterator<BusinessCircle> itt = bc.getCircleIterator();
			System.out.println("----------------------------");
			System.out.println(bc.getName());
			while(itt.hasNext()){
				System.out.print(itt.next().getName()+"  ");
			}
			System.out.println("----------------------------");
		}
	}
}
