package Presentation.OrderUI;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Map.Entry;
import org.controlsfx.control.Notifications;
import datacontroller.DataController;
import info.ListWrapper;
import info.OrderStatus;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.service.CommentLogicService;
import logic.service.OrderLogicService;
import resultmessage.OrderResultMessage;
import rmi.RemoteHelper;
import vo.OrderVO;

public class ClientBrowseOrderListController implements Initializable{
	@FXML private ListView<OrderVO> orderListView;
	@FXML private ChoiceBox<String> orderType;
	@FXML private TextField searchText;
	private ObservableList<OrderVO> olist;
	private String[] otypes={"全部订单","未执行订单","已执行订单","异常订单","已撤销订单"};
	private Map<String,OrderStatus> otypeMap;
	private OrderLogicService orderLogic;
	private CommentLogicService commentLogic;
	private long userid;
	@FXML 
	protected void searchInText(ActionEvent e){
		if(olist==null)
			return;
		String text = searchText.getText();
		if(text.equals("")||text.equals("\\s")){
			orderListView.setItems(olist);
			return;
		}
		String[] keyWords = text.split(" ");
		ObservableList<OrderVO> result = FXCollections.observableArrayList();
		Map<OrderVO,Integer> map = new HashMap<>();
		for(OrderVO vo:olist){
			String[] sour = vo.toString().split(" ");
			int count = 0;
			for(int i=0;i<keyWords.length;i++)
				for(int j=0;j<sour.length;j++){
					if(sour[j].contains(keyWords[i]))
						count++;
				}
			if(count>0)
				map.put(vo, count);
		}
		map = sortMapByValue(map);
	    for (Map.Entry<OrderVO, Integer> entry : map.entrySet()) {
	    	result.add(entry.getKey());
	    }
		orderListView.setItems(result);
	}
	  private Map<OrderVO,Integer> sortMapByValue(Map<OrderVO, Integer> map) {
	        List<Map.Entry<OrderVO, Integer>> mapList = new ArrayList<Map.Entry<OrderVO, Integer>>(
	                map.entrySet());
	        Collections.sort(mapList, new Comparator<Map.Entry<OrderVO, Integer>>() {
	            @Override
	            public int compare(Entry<OrderVO, Integer> o1,
	                    Entry<OrderVO, Integer> o2) {
	                return -1*(o1.getValue()-o2.getValue());
	            }
	        });
	        Map<OrderVO,Integer> result = new LinkedHashMap<OrderVO,Integer>();
	        for(Map.Entry<OrderVO, Integer> entry:mapList){
	            result.put(entry.getKey(), entry.getValue());
	        }
	        return result;
	    }
	public void review(OrderVO vo,ActionEvent e) throws Exception{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Comment.fxml"));
		Parent root=loader.load();
		CommentController controller =loader.getController();
		controller.setOrderVO(vo);
		Scene scene=new Scene(root);
		Stage stage=new Stage();
//		stage.initModality(Modality.APPLICATION_MODAL);
//		stage.initStyle(StageStyle.TRANSPARENT);
		stage.setScene(scene);
		stage.show();
	}
	public void revoke(OrderVO vo,ActionEvent e){
		try {
			OrderResultMessage m = orderLogic.userRevoke(vo.getOrderId());
			if(m==OrderResultMessage.SUCCESS){
				Notifications.create().owner(orderListView.getScene().getWindow()).title("撤销订单").text("撤销成功！").showConfirm();
				vo.setStatus(OrderStatus.REVOKED);
				olist.remove(vo);
				olist.add(vo);
			}
			else if (m==OrderResultMessage.FAIL_WRONGID)
				Notifications.create().owner(orderListView.getScene().getWindow()).title("撤销订单").text("错误的订单号！").showWarning();
			else if (m==OrderResultMessage.FAIL_WRONGORDERINFO)
				Notifications.create().owner(orderListView.getScene().getWindow()).title("撤销订单").text("错误的订单信息！").showWarning();
			else if (m==OrderResultMessage.FAIL_WRONGSTATUS)
				Notifications.create().owner(orderListView.getScene().getWindow()).title("撤销订单").text("错误的订单状态！").showWarning();
		} catch (RemoteException e1) {
			Notifications.create().owner(orderListView.getScene().getWindow()).title("撤销订单").text("网络错误！").showWarning();
			e1.printStackTrace();
		}
	}
	//鍩烘湰淇℃伅
    public void setBaseInfo(){
    	Object o = DataController.getInstance().get("UserId");
    	if(o==null){
    		Notifications.create().owner(orderListView.getScene().getWindow()).title("鍒濆鍖�").text("鍒濆鍖栭敊璇紒").showError();
    		return;
    	}
    	this.userid=(long)o;
    	DataController.getInstance().put("ClientOrderController", this);
    	ObservableList<String> t = FXCollections.observableArrayList(otypes);
    	orderType.setItems(t);
    	orderType.setValue("全部订单");
    	otypeMap = new HashMap<>();
    	otypeMap.put("未执行订单", OrderStatus.UNEXECUTED);
    	otypeMap.put("异常订单", OrderStatus.ABNORMAL);
    	otypeMap.put("已执行订单", OrderStatus.EXECUTED);
    	otypeMap.put("已撤销订单", OrderStatus.REVOKED);
    	orderType.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				searchInChoice(newValue);
			}
		});
    }
	public void searchInChoice(String value){
		if(olist==null)
			return;
		if(value==null)
			return;
		else{
			if(value.equals("鍏ㄩ儴璁㈠崟")){
				orderListView.setItems(olist);
				return;
			}
			OrderStatus status = otypeMap.get(value);
			ObservableList<OrderVO> slist = FXCollections.observableArrayList();
			for(OrderVO vo:olist){
				if(vo.getStatus()==status)
					slist.add(vo);
			}
			orderListView.setItems(slist);
		}
	}

    //鍒濆鍖栧垪琛�
    public void initListView() throws RemoteException{
    	ListWrapper<OrderVO> volist=orderLogic.getUserOrderInfo(userid);
    	if(volist==null)
    		return;
    	olist = FXCollections.observableArrayList();
    	Iterator<OrderVO> it=volist.iterator();
    	while(it.hasNext()){
    		olist.add(it.next());
    	}
    	orderListView.setItems(olist);
    	orderListView.setCellFactory(e->new ClientOrderListCell());
    }
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			orderLogic=RemoteHelper.getInstance().getServiceFactory().getOrderLogicService();
			commentLogic = RemoteHelper.getInstance().getServiceFactory().getCommentLogicService();
			setBaseInfo();
			initListView();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
}
