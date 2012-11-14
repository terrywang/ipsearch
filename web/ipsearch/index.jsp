<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>IP Search Struts Edition</title>
<style>
body,td,input {font-size:12px;font-family:verdana,"宋体"}
.btn { font-size: 9pt; color: #000000; background: buttonface; border: 1px solid; border-color: #F8F7F5 #A39A89 #A39A89 #F8F7F5; padding-top: 2px; }
.box { border: 1px solid; border-color: #7C7461 #E3E1DB #E3E1DB #7C7461; background: #FFFFFF}
</style>
</head>
<body>
<center>
  <table width="50%" height="50"  border="0" align="center" cellpadding="1" cellspacing="1"bordercolor="#C0C0C0" style="font-size: 9pt; line-height: 150%; border-collapse: collapse">
    <tr>
      <td>
	  <html:form action="/search.do" method="post">
	  <div align="center">
	  <label><span class="style1">Please input the IP address you want to locate: </span></label>
	  <br>
	  <html:text property="ip"><br />
	  </html:text>
	  <br>
	  <html:submit value="Submit">
	  </html:submit>
	  </div>
	  </html:form>
	  </td>
    </tr>
  </table>
  <br>
  &copy; <a href="http://imterry.googlepages.com/">Terry</a>No rights reserved;-)<br>
  <br>
<br>
</center>
</body>
</html>
