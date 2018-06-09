package br.com.xptosystems.security.interceptor;

//package br.com.agendafacil.security.interceptor;
//
//import br.com.agendafacil.security.access.AccessControlMB;
//import br.com.agendafacil.security.ui.Login;
//import java.io.Serializable;
//import javax.faces.context.FacesContext;
//import javax.inject.Inject;
//import javax.interceptor.AroundInvoke;
//import javax.interceptor.Interceptor;
//import javax.interceptor.InvocationContext;
//
//@Interceptor
//@Login
//public class LoginInterceptor implements Serializable {
//
//    @Inject
//    private AccessControlMB accessControlMB;
//
//    @AroundInvoke
//    public Object test(InvocationContext ic) {
//        FacesContext context = FacesContext.getCurrentInstance();
//        if (AccessControlMB.getStaticSessionUsers().getId() != -1) {
//            context.getApplication().getNavigationHandler().handleNavigation(context, null, "login");
//        }
//        try {
//            return ic.proceed();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
