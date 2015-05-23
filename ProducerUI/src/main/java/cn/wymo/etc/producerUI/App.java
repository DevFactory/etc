package cn.wymo.etc.producerUI;

import com.vaadin.server.UIClassSelectionEvent;
import com.vaadin.server.UIProvider;
import com.vaadin.ui.UI;

/**
 * Hello world!
 *
 */
@SuppressWarnings("serial")
public class App extends UIProvider {
    @Override
    public Class<? extends UI>
              getUIClass(UIClassSelectionEvent event) {
        String ua = event.getRequest()
                .getHeader("user-agent").toLowerCase();
        if (   ua.toLowerCase().contains("webkit")
            || ua.toLowerCase().contains("windows phone 8")
            || ua.toLowerCase().contains("windows phone 9")) {
            return AppUI.class;
        } else {
            return AppFallbackUI.class;
        }
    }
}
