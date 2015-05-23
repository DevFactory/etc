package cn.wymo.etc.producerUI.view.components;

import cn.wymo.etc.common.model.Role;
import cn.wymo.etc.common.model.User;
import cn.wymo.etc.producerUI.AppUI;

import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class UserItem extends CssLayout {
	private User user;
	private Image photo;
	private Label assignment = new Label();
	
	public UserItem(User user) {
		this.user = user;
		addStyleName("user-item-300w");
		
		CssLayout hdr = new CssLayout();
		hdr.addStyleName("user-header");
		CssLayout ft = new CssLayout();
		ft.addStyleName("user-footer");

		if(this.user.getPhoto() == null || this.user.getPhoto().isEmpty()) {
			photo = AppUI.getStorageProvider().getImage("", "http://oss.1lai2qu.com/users/default.jpg", "80h_80w_100Q.jpg");
		} else {
			photo = AppUI.getStorageProvider().getImage("", user.getPhoto(), "80h_80w_100Q.jpg");
		}
		photo.setCaption(this.user.getUname());
        photo.addStyleName("user-avater");
        hdr.addComponent(photo);
        
        if(user.hasRole(Role.ADMINISTRATOR)) {
        	assignment.setCaption(Role.ADMINISTRATOR.getCaption());
        } else if(user.hasRole(Role.VENDOR)) {
        	assignment.setCaption(Role.VENDOR.getCaption());
        } else {
        	assignment.setCaption(Role.USER.getCaption());
        }
        assignment.addStyleName("user-assign");
        
        ft.addComponent(assignment);
		this.addComponents(hdr, ft);
	}
}
