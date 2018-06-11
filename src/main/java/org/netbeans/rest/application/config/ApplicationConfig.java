 
package org.netbeans.rest.application.config;

import java.util.Set;
import javax.ws.rs.core.Application;
 
@javax.ws.rs.ApplicationPath("ws")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }
 
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(br.com.xptosystems.ws.AuthWS.class);
        resources.add(br.com.xptosystems.ws.CitiesWS.class);
        resources.add(br.com.xptosystems.ws.StatusWS.class);
    }
    
}
