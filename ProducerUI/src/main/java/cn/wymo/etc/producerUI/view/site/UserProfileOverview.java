package cn.wymo.etc.producerUI.view.site;

import cn.wymo.etc.common.model.User;
import cn.wymo.etc.producerUI.AppEventBus;
import cn.wymo.etc.producerUI.AppUI;
import cn.wymo.etc.producerUI.AppEvent.UserLoggedOutEvent;

import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Image;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class UserProfileOverview extends NavigationView implements ClickListener {
	CssLayout root = new CssLayout();
	VerticalComponentGroup businessGroup = new VerticalComponentGroup("我的");
	VerticalComponentGroup settingsGroup = new VerticalComponentGroup("设置&更新");
	Button signout = new Button("退出登录", this);
	
	public UserProfileOverview() {
		addStyleName("nav-no-title");
		root.addStyleName("profileview");
		root.setSizeFull();
		User user = (User) VaadinSession.getCurrent().getAttribute(
    			User.class.getName());
		user.getPhoto();
		
		final Image photo = AppUI.getStorageProvider().getImage("", "http://oss.1lai2qu.com/users/default.jpg", "80h_80w_100Q.jpg");
        photo.setWidth("80px");
        photo.setHeight("80px");
		
        CssLayout header = new CssLayout();
        header.addStyleName("header");
		VerticalLayout i = new VerticalLayout();
		CssLayout p = new CssLayout();
		i.addStyleName("info");
		i.addComponent(new Label("姓名:"+user.getLastName() + user.getFirstName()));
		i.addComponent(new Label("手机:"+user.getPhone()));
		i.addComponent(new Label("类型:"+"集团用户"));
		p.addStyleName("photo");
		p.addComponent(photo);
		header.addComponents(i, p);
		
		businessGroup.addComponents(new OutboxNavigationButton());
		businessGroup.addComponents(new TaskNavigationButton());
		businessGroup.addComponents(new FeedNavigationButton());
		businessGroup.addComponents(new InboxNavigationButton());
		
		settingsGroup.addComponents(new SettingsNavigationButton());
		settingsGroup.addComponents(new AboutNavigationButton());
		
		signout.setWidth("90%");
		
		root.addComponents(header, businessGroup, settingsGroup, signout);
		root.setSizeUndefined();
		setContent(root);
	}
    
    static class InboxNavigationButton extends NavigationButton {
        public InboxNavigationButton() {
        	setCaption("我的消息");
        	setIcon(FontAwesome.BELL);
        }
    }
    
    static class FeedNavigationButton extends NavigationButton {
        public FeedNavigationButton() {
        	setCaption("我的关注");
        	setIcon(FontAwesome.BOOKMARK);
        }
    }
    
    static class TaskNavigationButton extends NavigationButton {
        public TaskNavigationButton() {
        	setCaption("我的任务");
        	setIcon(FontAwesome.TASKS);
        }
    }
    
    static class OutboxNavigationButton extends NavigationButton {
        public OutboxNavigationButton() {
        	setCaption("我的发布");
        	setIcon(FontAwesome.EDIT);
        }
    }
    
    static class SettingsNavigationButton extends NavigationButton {
        public SettingsNavigationButton() {
        	setCaption("设置");
        	setIcon(FontAwesome.WRENCH);
        }
    }
    
    static class AboutNavigationButton extends NavigationButton {
        public AboutNavigationButton() {
        	setCaption("版本号2.0");
        	setIcon(FontAwesome.INFO);
        }
    }

	@Override
	public void buttonClick(ClickEvent event) {
		AppEventBus.post(new UserLoggedOutEvent());
	}
}
