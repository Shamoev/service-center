<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
<form action="/services/login" method="post">
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
</form>
</body>
</html>
