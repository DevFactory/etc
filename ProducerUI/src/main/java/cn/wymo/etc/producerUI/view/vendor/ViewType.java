package cn.wymo.etc.producerUI.view.vendor;

import cn.wymo.etc.producerUI.view.admin.settings.SettingsNavigationManager;
import cn.wymo.etc.producerUI.view.admin.users.UsersAdminNavigationManager;
import cn.wymo.etc.producerUI.view.admin.vendors.VendorsAdminNavigationManager;

import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;
import com.vaadin.ui.Component;

public enum ViewType {
	USERS     ("users",    "用户管理", UsersAdminNavigationManager.class,   FontAwesome.USERS),
	VENDORS   ("vendors",  "供应商管理", VendorsAdminNavigationManager.class, FontAwesome.SITEMAP),
	SETTINGS  ("settings", "系统管理", SettingsNavigationManager.class,     FontAwesome.COGS);
	
	private final String name;
    private final String label;
    private final Class<? extends Component> klass;
    private final Resource icon;

    private ViewType(final String name, final String label,
            final Class<? extends Component> klass, final Resource icon) {
        this.name = name;
        this.label = label;
        this.klass = klass;
        this.icon = icon;
    }

    public String getViewName() {
        return name;
    }
    
    public String getViewLabel() {
        return label;
    }

    public Class<? extends Component> getViewClass() {
        return klass;
    }
 
    public Resource getIcon() {
        return icon;
    }

    public String getName() {
		return name;
	}

	public String getLabel() {
		return label;
	}

	public Class<? extends Component> getKlass() {
		return klass;
	}

	public static ViewType getByViewName(final String viewName) {
		ViewType result = null;
        for (ViewType type : values()) {
            if (type.getViewName().equals(viewName)) {
                result = type;
                break;
            }
        }
        return result;
    }
    
    public static ViewType getByViewClass(final Class<?> klass) {
    	ViewType result = null;
        for (ViewType type : values()) {
            if (type.getClass().equals(klass)) {
                result = type;
                break;
            }
        }
        return result;
    }
}
