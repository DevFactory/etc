package cn.wymo.etc.producerUI.view.site;

import cn.wymo.etc.producerUI.AppUI;
import cn.wymo.etc.producerUI.AppEventBus;
import cn.wymo.etc.producerUI.AppEvent.UserLoginRequestedEvent;

import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public class LoginView extends VerticalLayout {
	private TextField username;
	private PasswordField password;
	
	public LoginView() {
        setSizeFull();
        Component loginForm = buildLoginForm();
        addComponent(loginForm);
        setComponentAlignment(loginForm, Alignment.TOP_CENTER);
    }
	
	private Component buildLoginForm() {
		final VerticalLayout loginPanel = new VerticalLayout();
        loginPanel.setSizeUndefined();
        loginPanel.setSpacing(true);
        Responsive.makeResponsive(loginPanel);
        loginPanel.addStyleName("login-panel");
        
        final Button signin = new Button("登录");
        signin.setWidth("100%");
        
        signin.setClickShortcut(KeyCode.ENTER);
        signin.focus();
        signin.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(final ClickEvent event) {
            	AppEventBus.post(new UserLoginRequestedEvent(username
                        .getValue(), password.getValue()));
            }
        });
        
        loginPanel.addComponents(buildFields(), buildActions(), signin);
        return loginPanel;
    }
	
	private Component buildActions() {
		HorizontalLayout actions = new HorizontalLayout();
		actions.setWidth("100%");
		actions.setSpacing(true);
		actions.addStyleName("actions");
		final Button signup = new Button("立即注册");
        signup.addStyleName(ValoTheme.BUTTON_SMALL);
        signup.addStyleName(ValoTheme.BUTTON_LINK);
        signup.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(final ClickEvent event) {
            	AppEventBus.post(new UserLoginRequestedEvent(username
                        .getValue(), password.getValue()));
            }
        });
        final Button reset = new Button("忘记密码?");
        reset.addStyleName(ValoTheme.BUTTON_SMALL);
        reset.addStyleName(ValoTheme.BUTTON_LINK);
        reset.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(final ClickEvent event) {
            	AppEventBus.post(new UserLoginRequestedEvent(username
                        .getValue(), password.getValue()));
            }
        });
        actions.addComponents(signup, reset);
        return actions;
    }

    private Component buildFields() {
    	VerticalLayout fields = new VerticalLayout();
        fields.setSpacing(true);
        fields.addStyleName("fields");
        
        final Image photo = AppUI.getStorageProvider().getImage("", "http://oss.1lai2qu.com/users/default.jpg", "80h_80w_100Q.jpg");
        photo.setWidth("80px");
        photo.setHeight("80px");
        final CssLayout p = new CssLayout(photo);
        p.addStyleName("photo");
        
        username = new TextField();
        username.setIcon(FontAwesome.USER);
        username.setInputPrompt("请使用手机号");
        username.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);

        password = new PasswordField();
        password.setIcon(FontAwesome.LOCK);
        password.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);

        fields.addComponents(p, username, password);
        
        return fields;
    }
}
