package Presentation.HotelUI;

import java.io.File;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Set;
import javax.imageio.ImageIO;

import org.controlsfx.control.Notifications;

import datacontroller.DataController;
import info.BusinessCircle;
import info.BusinessCity;
import info.ListWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import logic.service.ServiceFactory;
import resultmessage.HotelResultMessage;
import rmi.RemoteHelper;
import vo.HotelVO;
import vo.MaintainHotelInfoVO;

public class SetHotelInfoController implements Initializable{
	private ServiceFactory serviceFactory;
	private ListWrapper<BusinessCity> bc;
	private long hotelId;
	@FXML private ImageView image;
	@FXML private TextField hotelName;
	@FXML private TextField address;
	@FXML private ChoiceBox<String> businessCity;
	@FXML private ChoiceBox<String> businessCircle;
	@FXML private TextArea description;
	@FXML private TextArea facility;
	@FXML private TextArea service;
	@FXML
	protected void commit(ActionEvent e){
		MaintainHotelInfoVO vo = new MaintainHotelInfoVO();
		vo.setAddress(address.getText());
		vo.setHotelId(hotelId);
		Iterator<BusinessCity> it=null;
		try {
			it = bc.iterator();
		} catch (RemoteException e2) {
			e2.printStackTrace();
		}
		BusinessCircle res = null;
		if(it!=null)
		while(it.hasNext()){
			BusinessCity bcc = it.next();
			Iterator<BusinessCircle> bcir = bcc.getCircleIterator();
			boolean flag = false;
			while(bcir.hasNext()){
				BusinessCircle bcirr = bcir.next();
				if(bcirr.getName().equals(businessCircle.getValue())){
					flag = true;
					res = bcirr;
					break;
				}
			}
			if(flag)
				break;
		}
		vo.setBusinessCircle(res);
		vo.setDescription(description.getText());
		vo.setService(service.getText());
		vo.setFacility(facility.getText());
		BufferedImage img = null;
		vo.setImage(SwingFXUtils.fromFXImage(image.getImage(), img));
		HotelResultMessage result = null;
		try {
			result = serviceFactory.getHotelLogicService().setHotelInfo(vo);
			if(result==HotelResultMessage.SUCCESS){
				Notifications.create().owner(hotelName.getScene().getWindow()).title("维护酒店信息").text("更新成功！").showConfirm();
			}
			System.out.println(result);
		} catch (RemoteException e1) {
			Notifications.create().owner(hotelName.getScene().getWindow()).title("维护酒店信息").text("未知错误！").showError();
			e1.printStackTrace();
		}
	}
	public void setHotelId(long hotelId){
		this.hotelId = hotelId;
	}
	@FXML
	protected void changeImage(ActionEvent e){
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
            );
		File file = null;
		file = fileChooser.showOpenDialog(image.getScene().getWindow());
		if(file!=null){
			WritableImage pic = null;
			try {
				Image picc = ImageIO.read(file);
				image.setImage(SwingFXUtils.toFXImage((BufferedImage) picc, pic));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/*if(serviceFactory==null)
			serviceFactory = RemoteHelper.getInstance().getServiceFactory();
		try {
			hotelId = (long) DataController.getInstance().get("HotelId");
			HotelVO vo = serviceFactory.getHotelLogicService().getHotelInfo(hotelId);
			bc = serviceFactory.getHotelLogicService().getCity();
			if(vo==null){
				System.out.println("wrong");
				return;
			}
			address.setText(vo.getAddress());
			hotelName.setText(vo.getName());
			if(vo.getImage()!=null){
				System.out.println(1);
				WritableImage pic = null;
				image.setImage(SwingFXUtils.toFXImage((BufferedImage) vo.getImage(), pic));
			}
			else{
				WritableImage pic = null;
				File file = new File(getClass().getResource("DefaultHotelPic.jpg").toURI());
				Image picc = ImageIO.read(file);
				image.setImage(SwingFXUtils.toFXImage((BufferedImage) picc, pic));
			}
			description.setText(vo.getDescription());
			service.setText(vo.getService());
			facility.setText(vo.getFacility());
			Set<String> citySet = new HashSet<>();
			citySet.add(vo.getBusinessCity().getName());
			ObservableList<String> city = FXCollections.observableArrayList(citySet);
			businessCity.setItems(city);
			businessCity.setValue(vo.getBusinessCity().getName());
			Set<String> set = new HashSet<>();
			Iterator<BusinessCity> it = bc.iterator();
			while(it.hasNext()){
				BusinessCity bci = it.next();
				if(bci.getName().equals(vo.getBusinessCity().getName())){
					Set<BusinessCircle> bcirs = bci.getCircles();
					for(BusinessCircle bcir:bcirs){
						set.add(bcir.getName());
					}
					break;
				}
			}
			ObservableList<String> circles = FXCollections.observableArrayList(set);
			businessCircle.setItems(circles);
			businessCircle.setValue(vo.getBusinessCircle().getName());
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}*/
	}
}
