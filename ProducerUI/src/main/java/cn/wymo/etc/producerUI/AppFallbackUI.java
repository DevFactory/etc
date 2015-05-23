package cn.wymo.etc.producerUI;

import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
public class AppFallbackUI extends UI {

    // FIXME review message
    private static final String MSG = "<h1>出错啦！</h1> <p>仅支持移动设备浏览器。<p>";

    @Override
    protected void init(VaadinRequest request) {

        Label label = new Label(MSG, ContentMode.HTML);
        setContent(label);

    }

}