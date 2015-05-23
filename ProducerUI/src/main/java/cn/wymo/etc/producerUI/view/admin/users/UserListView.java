package cn.wymo.etc.producerUI.view.admin.users;

import java.util.Iterator;
import java.util.List;

import cn.wymo.etc.producerUI.AppUI;
import cn.wymo.etc.common.model.User;

import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.Toolbar;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

@SuppressWarnings("serial")
public class UserListView extends NavigationView implements ClickListener {
	private VerticalComponentGroup list = new VerticalComponentGroup();
	private Button searchButton  = new Button(null, this);
	private Button newButton     = new Button(null, this);
	private CheckBox selectAll   = new CheckBox("全选");
	private Button activateButton     = new Button("激活", this);
	private Button deactivateButton     = new Button("禁用", this);
	private Button resetButton     = new Button("重置", this);
	private Button deleteButton     = new Button("删除", this);
	
	private HorizontalLayout topToolbar  = new HorizontalLayout();
	private Toolbar bottomToolbar  = new Toolbar();
	private List<User> users;
	
	public UserListView() {
		super("用户列表");
		
		searchButton.setIcon(FontAwesome.SEARCH);
		newButton.setIcon(FontAwesome.PLUS_CIRCLE);
		topToolbar.addComponents(searchButton, newButton);
		setRightComponent(topToolbar);		
		
		this.users = AppUI.getDataProvider().getOtherUsers();
		
		if(this.users.size() > 0) {
			Iterator<User> it = AppUI.getDataProvider().getOtherUsers().iterator();
	    	while(it.hasNext()) {
	    		list.addComponent(new UserSimpleView(it.next()));
	    	}
	    	setContent(list);
	    	if(this.users.size() > 1) {
	    		selectAll.addStyleName("button");
	    		activateButton.addStyleName("button");
	    		deactivateButton.addStyleName("button");
	    		resetButton.addStyleName("button");
	    		deleteButton.addStyleName("button");
	    		activateButton.setIcon(FontAwesome.UNLOCK);
	    		deactivateButton.setIcon(FontAwesome.LOCK);
	    		resetButton.setIcon(FontAwesome.REFRESH);
	    		deleteButton.setIcon(FontAwesome.TRASH_O);
	    		bottomToolbar.addComponents(selectAll, activateButton, deactivateButton, resetButton, deleteButton);
	    		this.setToolbar(bottomToolbar);
	    	}
		} else {
			Label b = new Label("查询结果为空！");
			b.setSizeFull();
			setContent(b);
		}
		
		getNavigationBar().addStyleName("navigationbar");
		getToolbar().addStyleName("toolbar");
	}

	@Override
	public void buttonClick(ClickEvent event) {
		// TODO Auto-generated method stub
		
	}
}
