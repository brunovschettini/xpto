package br.com.xptosystems.security.access;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class AuthorizationListener implements PhaseListener {

    @Override
    public void afterPhase(PhaseEvent event) {
//        FacesContext facesContext = event.getFacesContext();
//        NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
//        String currentPage = convertToPage(facesContext.getViewRoot().getViewId());
////        if (Sessions.exists("currentPage")) {
////            if (Sessions.getString("currentPage").equals(currentPage)) {
////                return;
////            } else {
////                Sessions.put("currentPage", currentPage);
////            }
////        } else {
////            Sessions.put("currentPage", currentPage);
////        }
//        try {
//            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
//            Object currentUser = session.getAttribute("sessionUsers");
//            if (currentUser == null) {
//                if (!currentPage.equals("login") && !currentPage.equals("index")) {
//                    if(currentPage.equals("error_401") || currentPage.equals("error_402") || currentPage.equals("error_403") || currentPage.equals("error_404") || currentPage.equals("error_500")) {
//                       return; 
//                    }
//                    nh.handleNavigation(facesContext, null, "error_401");
//                }
//            }
//        } catch (Exception e) {
//        }
    }

    @Override
    public void beforePhase(PhaseEvent event) {
    }

    private String convertToPage(String currentPage) {
        currentPage = currentPage.substring(currentPage.lastIndexOf("/") + 1, currentPage.lastIndexOf("."));
        return currentPage;
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

}
