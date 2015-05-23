package cn.wymo.etc.producerUI.view.admin.users;

import cn.wymo.etc.producerUI.AppUI;
import cn.wymo.etc.common.model.User;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class UserSimpleView extends HorizontalLayout {
	private User user;
	
	private CheckBox cb;
	private VerticalLayout l;
	private VerticalLayout r;
	private Image photo;
	
	public UserSimpleView(User user) {
		this.user = user;
		
		cb = new CheckBox();
		
		l = new VerticalLayout();
		if(this.user.getPhoto() == null || this.user.getPhoto().isEmpty()) {
			photo = AppUI.getStorageProvider().getImage("", "http://oss.1lai2qu.com/users/default.jpg", "50h_50w_100Q.jpg");
		} else {
			photo = AppUI.getStorageProvider().getImage("", user.getPhoto(), "50h_50w_100Q.jpg");
		}
		photo.setWidth("50px");
		photo.setHeight("50px");
        photo.addStyleName("user-avater");
        Label ul = new Label(this.user.getUname());
        ul.setHeight("20px");
        l.addComponents(photo, ul);
        l.setHeight("90px");
        l.setComponentAlignment(photo, Alignment.TOP_CENTER);
        l.setComponentAlignment(ul, Alignment.BOTTOM_CENTER);
        
        r = new VerticalLayout();
        r.addComponent(new Label(this.user.getPhone()));
        r.addComponent(new Label(this.user.getLastName() + " " + this.user.getFirstName()));
        r.addComponent(new Label(this.user.getPhone()));
        r.setHeight("90px");
        
		this.addComponents(cb, l, r);
		this.setExpandRatio(cb, 1);
		this.setExpandRatio(l, 2);
		this.setExpandRatio(r, 3);
		this.setWidth("100%");
		this.setHeight("90px");
	}

	public void setCheckBoxVisible(boolean visible) {
		cb.setVisible(visible);
	}
}
