package ipsearch;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.Action;

public class IPSearchAction extends Action {
    public ActionForward execute(ActionMapping actionMapping,
                                 ActionForm actionForm,
                                 HttpServletRequest servletRequest,
                                 HttpServletResponse servletResponse) {
        InputForm p = (InputForm) actionForm;
        String i = p.getIp();
        if (i.equals("")) {
            return actionMapping.findForward("failure");
        }
        String path = getServlet().getServletContext().getRealPath("/");
        ip.IPSeeker seeker = new ip.IPSeeker(path + "\\WEB-INF\\lib\\QQWry.dat");
        p.setCountry(seeker.getCountry(i));
        p.setArea(seeker.getArea(i));
        p.setCounts("" + seeker.getRecords());
        p.setEdition(seeker.getDataEdition());
        p.setUpdate(seeker.getUpdateDate());
        javax.servlet.http.HttpSession ss = servletRequest.getSession(true);
        ss.setAttribute("ip", p);
        return actionMapping.findForward("success");
    }
}
