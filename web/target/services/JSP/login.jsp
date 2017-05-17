<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>


<html>
<head>
    <title>Login Page</title>
</head>
<body>
<c:choose>
  <c:when test="${user == null || user.name.equals('anonymous')}">
    <form action="/services/login" method="post">
      <table border="0" align="right">
        <tr>
          <td>Login:</td>
          <td> <input type="text" name="login"/></td>
        </tr>
        <tr>
          <td>Password:</td>
          <td> <input type="password" name="password"/></td>
        </tr>
        <tr>
          <td>
            <c:if test="${loginFailed}">
              <div style="display: ruby-text; color: crimson">Login failed</div>
            </c:if>
          </td>
        </tr>
        <tr>
        <tr>
          <td colspan="1" align="center">
            <input type="submit" value="login" name="login" />
          </td>
          <td colspan="1" align="center">
            <input type="submit" value="register" name="register" />
          </td>
        </tr>
      </table>
    </form>
  </c:when>
  <c:otherwise>
    <c:out value="Welcome, ${user.name} ${user.lastName}!"/>
    <form action="/services/login" method="post">
      <table border="0" align="right">
        <tr>
          <td>
            <button type="submit" value="logout" name="logout"
                    style="background-color:transparent; border-color:transparent;">
              <img src="./resources/images/logout_button_0.png" height="20" width="80"/>
            </button>
          </td>
        </tr>
      </table>
    </form>
  </c:otherwise>
</c:choose>
</body>
</html>

<%--<form action="/services/login" method="post">
  <table border="0" align="right">
    <tr>
      <td>Login:</td>
      <td>
        <input type="text" name="login"/>
      </td>
    </tr>
    <tr>
      <td>Password:</td>
      <td>
        <input type="password" name="password"/>
      </td>
    </tr>
    <tr>
      <td colspan="1" align="center">
        <input type="submit" value="ok" name="ok" />
      </td>
      <td colspan="1" align="center">
        <input type="submit" value="register" name="register" />
      </td>
    </tr>
  </table>
</form>--%>
</body>
</html>
