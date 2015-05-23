package cn.wymo.etc.producerUI.view.admin.vendors;

import java.util.Iterator;
import java.util.List;

import cn.wymo.etc.producerUI.AppUI;
import cn.wymo.etc.common.model.Vendor;

import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

@SuppressWarnings("serial")
public class VendorListView extends NavigationView implements ClickListener {
	private VerticalComponentGroup list = new VerticalComponentGroup();
	private Button searchButton  = new Button(null, this);
	private Button newButton     = new Button(null, this);
	
	private HorizontalLayout topToolbar  = new HorizontalLayout();
	private List<Vendor> vendors;
	
	public VendorListView() {
		super("供应商列表");
		
		searchButton.setIcon(FontAwesome.SEARCH);
		newButton.setIcon(FontAwesome.PLUS_CIRCLE);
		topToolbar.addComponents(searchButton, newButton);
		setRightComponent(topToolbar);		
		
		this.vendors = AppUI.getDataProvider().getVendors();
		
		if(this.vendors.size() > 0) {
			Iterator<Vendor> it = this.vendors.iterator();
	    	while(it.hasNext()) {
	    		list.addComponent(new VendorSimpleView(it.next()));
	    	}
	    	setContent(list);
		} else {
			Label b = new Label("查询结果为空！");
			b.setSizeFull();
			setContent(b);
		}
		
		getNavigationBar().addStyleName("navigationbar");
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		if(event.getButton().equals(newButton)) {
			newButtonClick();
		} else if(event.getButton().equals(searchButton)) {
			searchButtonClick();
		}
	}
	
	private void newButtonClick() {
		
	}
	
	private void searchButtonClick() {
		
	}

}
