package ua.com.sourceit.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextInitializerListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("!!!!!!-----Context Inisialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        /*ThreadPoolExecutor tpe = (ThreadPoolExecutor)sce.getServletContext().getAttribute("ThreadPool");
        tpe.shutdownNow();*/

    }
}
