package logic.service.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import logic.service.BrowseLogicService;
import logic.service.CommentLogicService;
import logic.service.CreditLogicService;
import logic.service.HotelLogicService;
import logic.service.MemberLogicService;
import logic.service.OrderLogicService;
import logic.service.ServiceFactory;
import logic.service.StrategyLogicService;
import logic.service.UserLogicService;

public class ServiceFactoryImpl extends UnicastRemoteObject implements ServiceFactory{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7077664542687834453L;
	private BrowseLogicService browseService;
	private CommentLogicService commentService;
	private CreditLogicService creditService;
	private HotelLogicService hotelService;
	private MemberLogicService memberService;
	private OrderLogicService orderService;
	private StrategyLogicService strategyService;
	private UserLogicService userService;
	private static ServiceFactoryImpl instance;
	public static ServiceFactoryImpl getInstance() throws RemoteException{
		if(instance==null)
			instance = new ServiceFactoryImpl();
		return instance;
	}
	protected ServiceFactoryImpl() throws RemoteException {
		super();
	}

	@Override
	public BrowseLogicService getBrowseLogicService() throws RemoteException {
		if (browseService==null)
			browseService = new BrowseLogicServiceImpl();
		return browseService;
	}

	@Override
	public CommentLogicService getCommentLogicService() throws RemoteException {
		if (commentService==null)
			commentService = new CommentLogicServiceImpl();
		return commentService;
	}

	@Override
	public CreditLogicService getCreditLogicService() throws RemoteException {
		if (creditService==null)
			creditService = new CreditLogicServiceImpl();
		return creditService;
	}

	@Override
	public HotelLogicService getHotelLogicService() throws RemoteException {
		if (hotelService==null)
			hotelService = new HotelLogicServiceImpl();
		return hotelService;
	}

	@Override
	public MemberLogicService getMemberLogicService() throws RemoteException {
		if (memberService==null)
			memberService = new MemberLogicServiceImpl();
		return memberService;
	}

	@Override
	public OrderLogicService getOrderLogicService() throws RemoteException {
		if (orderService==null)
			orderService = new OrderLogicServiceImpl();
		return orderService;
	}

	@Override
	public StrategyLogicService getStrategyLogicService() throws RemoteException {
		if (strategyService==null)
			strategyService = new StrategyLogicServiceImpl();
		return strategyService;
	}

	@Override
	public UserLogicService getUserLogicService() throws RemoteException {
		if (userService==null)
			userService = new UserLogicServiceImpl();
		return userService;
	}

}
