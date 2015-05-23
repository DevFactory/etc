package cn.wymo.etc.producerUI.view.admin.vendors;

import cn.wymo.etc.producerUI.view.IRefreshable;
import com.vaadin.addon.touchkit.ui.NavigationManager;

@SuppressWarnings("serial")
public class VendorsAdminNavigationManager extends NavigationManager implements IRefreshable {

	public VendorsAdminNavigationManager() {
		navigateTo(new VendorListView());
	}
	
	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}

}
