package cn.wymo.etc.producerUI.data;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Image;

public class OssResourceProvider implements IStorageProvider {

	@Override
	public Image getImage(String caption, String url, String style) {
		if(style != null && !style.isEmpty()) {
			url += "@" + style;
		}
		ExternalResource resource = new ExternalResource(url);
		return new Image(caption, resource);
	}

}
