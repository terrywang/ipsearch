package ipsearch;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;

public class InputForm extends ActionForm {
    private String area = "";
    private String country = "";
    private String counts = "";
    private String edition = "";
    private String ip = "";
    private String update = "";
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public void setCounts(String counts) {
        this.counts = counts;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public String getCounts() {
        return counts;
    }

    public String getEdition() {
        return edition;
    }

    public String getIp() {
        return ip;
    }

    public String getUpdate() {
        return update;
    }

    public ActionErrors validate(ActionMapping actionMapping,
                                 HttpServletRequest httpServletRequest) {
        /** @todo: finish this method, this is just the skeleton.*/
        return null;
    }

    public void reset(ActionMapping actionMapping,
                      HttpServletRequest servletRequest) {
    }
}
