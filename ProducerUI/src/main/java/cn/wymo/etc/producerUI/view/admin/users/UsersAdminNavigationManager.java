package cn.wymo.etc.producerUI.view.admin.users;

import cn.wymo.etc.producerUI.view.IRefreshable;

import com.vaadin.addon.touchkit.ui.NavigationManager;

@SuppressWarnings("serial")
public class UsersAdminNavigationManager extends NavigationManager implements IRefreshable {
	public UsersAdminNavigationManager() {
		navigateTo(new UserListView());
	}
	
	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}

}
