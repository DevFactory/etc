package cn.wymo.etc.producerUI.view.admin.vendors;

import cn.wymo.etc.common.model.Vendor;

import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

@SuppressWarnings("serial")
public class VendorSimpleView extends HorizontalLayout {
	private Vendor vendor;
	
	public VendorSimpleView(Vendor v) {
		this.vendor = v;
		this.addComponents(new Label(this.vendor.getName()));
	}
}
