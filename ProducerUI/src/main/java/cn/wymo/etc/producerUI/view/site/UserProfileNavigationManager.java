package cn.wymo.etc.producerUI.view.site;

import cn.wymo.etc.producerUI.view.IRefreshable;

import com.vaadin.addon.touchkit.ui.NavigationManager;

@SuppressWarnings("serial")
public class UserProfileNavigationManager extends NavigationManager implements IRefreshable {
	public UserProfileNavigationManager() {
		navigateTo(new UserProfileOverview());
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}
}
