package cn.wymo.etc.producerUI.view.corp;

import java.util.HashMap;import java.util.Map;

import cn.wymo.etc.producerUI.view.IRefreshable;

import com.vaadin.addon.touchkit.ui.TabBarView;
import com.vaadin.server.Resource;
import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet.Tab;

/**
 * This is the main view for Parking application. It displays a tabbar via one
 * can choose one of the sub views.
 */
@SuppressWarnings("serial")
public class CorpMainTabsheet extends TabBarView {
	private Map<Tab, IRefreshable> refreshables = new HashMap<Tab, IRefreshable>();
    public CorpMainTabsheet() {
    	for (ViewType type : ViewType.values()) {
    		try {
    			Component tab = type.getViewClass().newInstance();
    			refreshables.put(addTab(tab, "", type.getIcon(),type.getViewLabel()), (IRefreshable) tab);

			} catch (Exception e) {
				e.printStackTrace();
			}
        }
    	addListener(new SelectedTabChangeListener() {

			@Override
			public void selectedTabChange(SelectedTabChangeEvent event) {
				((IRefreshable) refreshables.get(event.getTabSheet().getSelelectedTab())).refresh();
			}
    	});
    }

    private Tab addTab(Component component, final String styleName,
    		final Resource icon, final String caption) {
        Tab tab = addTab(component);
        tab.setIcon(icon);
        tab.setCaption(caption);
        return tab;
    }

}
