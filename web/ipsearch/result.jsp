<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>
Result
</title>
<style>
body,td,input {font-size:12px;font-family:verdana,"宋体"}
.btn { font-size: 9pt; color: #000000; background: buttonface; border: 1px solid; border-color: #F8F7F5 #A39A89 #A39A89 #F8F7F5; padding-top: 2px; }
.box { border: 1px solid; border-color: #7C7461 #E3E1DB #E3E1DB #7C7461; background: #FFFFFF}
</style>
</head>
<body bgcolor="#ffffff">
<table width="300"  border="1" align="center" cellpadding="1" cellspacing="1" style="font-size:9px border  line-height: 150%; border-collapse: collapse ">
  <tr>
    <td width="80"><div align="center">IP Address</div></td>
    <td width="207">
      <div align="center">
        <bean:write name="ip" property="ip"/>
        </div></td>
  </tr>
  <tr>
    <td><div align="center">Physical location</div></td>
    <td>
      <div align="center">
        <bean:write name="ip" property="country"/> <bean:write name="ip" property="area"/>
    </div></td>
  </tr>
  <tr>
    <td><div align="center">Number of records in the database</div></td>
    <td>
	  <div align="center">
       <bean:write name="ip" property="counts"/>
	    </div></td>
  </tr>
  <tr>
    <td><div align="center">Data version</div></td>
    <td><div align="center">
        <bean:write name="ip" property="edition"/>
    </div></td>
  </tr>
  <tr>
    <td><div align="center">Update timestamp</div></td>
    <td><div align="center">
        <bean:write name="ip" property="update"/>
    </div></td>
  </tr>
</table>
<div align="center"><br>
	Your browser is: <c:out value="${header['User-Agent']}" />
</div>
<div align="center">
  <br>
  <html:link action="/back.do">Return to home page</html:link>
  <br>
  &copy;2004 <a href="http://imterry.googlepages.com/">Terry</a>No rights reserved;-) <br>
  <br>
  <br>
</div>
</body>
</html>
