package cn.wymo.etc.producerUI;

import java.util.Locale;

import cn.wymo.etc.producerUI.AppEventBus;
import cn.wymo.etc.producerUI.AppEvent.BrowserResizeEvent;
import cn.wymo.etc.producerUI.AppEvent.CloseOpenWindowsEvent;
import cn.wymo.etc.producerUI.AppEvent.UserLoggedOutEvent;
import cn.wymo.etc.producerUI.AppEvent.UserLoginRequestedEvent;
import cn.wymo.etc.producerUI.data.IDataProvider;
import cn.wymo.etc.producerUI.data.IStorageProvider;
import cn.wymo.etc.producerUI.data.JpaDataProvider;
import cn.wymo.etc.producerUI.data.OssResourceProvider;
import cn.wymo.etc.producerUI.view.admin.AdminMainTabsheet;
import cn.wymo.etc.producerUI.view.corp.CorpMainTabsheet;
import cn.wymo.etc.producerUI.view.site.LoginView;
import cn.wymo.etc.producerUI.view.vendor.VendorMainTabsheet;
import cn.wymo.etc.common.model.Role;
import cn.wymo.etc.common.model.User;

import com.google.common.eventbus.Subscribe;
import com.vaadin.addon.touchkit.annotations.CacheManifestEnabled;
import com.vaadin.addon.touchkit.annotations.OfflineModeEnabled;
import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.Page;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.server.Page.BrowserWindowResizeEvent;
import com.vaadin.server.Page.BrowserWindowResizeListener;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

@Theme("lenovo")
@Title("溯源")
@PreserveOnRefresh
@OfflineModeEnabled
@CacheManifestEnabled
@SuppressWarnings("serial")
public class AppUI extends UI {
	private final AppEventBus eventbus = new AppEventBus();
	private final IDataProvider dataProvider = new JpaDataProvider();
	private final IStorageProvider storageProvider = new OssResourceProvider();
	
    @Override
    protected void init(VaadinRequest request) {
    	setLocale(Locale.CHINA);
    	Responsive.makeResponsive(this);
    	AppEventBus.register(this);
    	updateContent();
        // Some views need to be aware of browser resize events so a
        // BrowserResizeEvent gets fired to the event but on every occasion.
        Page.getCurrent().addBrowserWindowResizeListener(
                new BrowserWindowResizeListener() {
                    @Override
                    public void browserWindowResized(
                            final BrowserWindowResizeEvent event) {
                    	AppEventBus.post(new BrowserResizeEvent());
                    }
                });
    }
    
    private void updateContent() {
    	User user = (User) VaadinSession.getCurrent().getAttribute(
    			User.class.getName());
        if (user != null) {
        	if(user.hasRole(Role.ADMINISTRATOR)) {
        		// Authenticated user with admin role
        		setContent(new AdminMainTabsheet());
                removeStyleName("loginview");
        	} else if(user.hasRole(Role.VENDOR)) {
        		// Authenticated user with vendor role
        		setContent(new VendorMainTabsheet());
                removeStyleName("loginview");
        	} else {
        		// Authenticated user with producer role
        		setContent(new CorpMainTabsheet());
                removeStyleName("loginview");
        	} 
        	return;
        } 
        setContent(new LoginView());
        addStyleName("loginview");
    }
    
    @Subscribe
    public void userLoginRequested(final UserLoginRequestedEvent event) {
    	User user = getDataProvider().authenticate(event.getUserName(),
                event.getPassword());
        VaadinSession.getCurrent().setAttribute(User.class.getName(), user);
        updateContent();
    }
    
    @Subscribe
    public void userLoggedOut(final UserLoggedOutEvent event) {
        // When the user logs out, current VaadinSession gets closed and the
        // page gets reloaded on the login screen. Do notice the this doesn't
        // invalidate the current HttpSession.
        VaadinSession.getCurrent().close();
        Page.getCurrent().reload();
    }

    @Subscribe
    public void closeOpenWindows(final CloseOpenWindowsEvent event) {
        for (Window window : getWindows()) {
            window.close();
        }
    }
    
    /**
     * @return An instance for accessing the (dummy) services layer.
     */
    public static IDataProvider getDataProvider() {
        return ((AppUI) getCurrent()).dataProvider;
    }
    
    public static IStorageProvider getStorageProvider() {
        return ((AppUI) getCurrent()).storageProvider;
    }
    
    
    public static AppEventBus getEventbus() {
        return ((AppUI) getCurrent()).eventbus;
    }
}
