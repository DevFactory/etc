package cn.wymo.etc.producerUI;

import javax.servlet.ServletException;

import com.vaadin.addon.touchkit.server.TouchKitServlet;
import com.vaadin.addon.touchkit.settings.TouchKitSettings;
import com.vaadin.addon.touchkit.settings.ViewPortSettings;

@SuppressWarnings("serial")
public class AppServlet extends TouchKitServlet {
	
    @Override
    protected void servletInitialized() throws ServletException {
        super.servletInitialized();
        
        TouchKitSettings s = getTouchKitSettings();

        String contextPath = getServletConfig().getServletContext()
                .getContextPath();
        s.getApplicationIcons().addApplicationIcon(
                contextPath + "/VAADIN/themes/lenovo/icon.png");
        s.getWebAppSettings().setStartupImage(
        	    contextPath + "VAADIN/themes/lenovo/startup.png");
        ViewPortSettings vp = s.getViewPortSettings();
        vp.setViewPortUserScalable(true);
        s.getApplicationCacheSettings().setCacheManifestEnabled(true);

    }
}
