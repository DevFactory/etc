package cn.wymo.etc.producerUI.data;

import com.vaadin.ui.Image;

public interface IStorageProvider {
	Image getImage(String caption, String url, String style);
}
