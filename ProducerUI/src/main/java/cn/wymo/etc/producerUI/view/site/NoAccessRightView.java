package cn.wymo.etc.producerUI.view.site;


import cn.wymo.etc.producerUI.AppEventBus;
import cn.wymo.etc.producerUI.AppEvent.UserLoggedOutEvent;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickListener;

@SuppressWarnings("serial")
public class NoAccessRightView extends VerticalLayout implements ClickListener {
	private final Label message = new Label();
	private final Button ask = new Button("立刻申请", this);
	private boolean sent = false;
	
	public NoAccessRightView() {
		setSizeFull();
		
		setComponentAlignment(message, Alignment.MIDDLE_CENTER);
		message.setValue("您没有权限访问!");

		ask.addStyleName("button");
		ask.setWidth("100%");
		ask.setClickShortcut(KeyCode.ENTER);
		ask.focus();
        
        this.addComponents(ask);
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		if(!sent) {
			message.setValue("您的申请正在审核中,审核结果将通过短信通知您。");
			ask.setCaption("我知道了，重新登录");
		} else {
			AppEventBus.post(new UserLoggedOutEvent());
		}
	}
}
