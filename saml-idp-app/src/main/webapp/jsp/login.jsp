<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head><title>PicketLink IDP</title></head>
<body>
  <form method="post" action="j_security_check" enctype="application/x-www-form-urlencoded">
    <p>Welcome to the <b>PicketLink Identity Provider</b></p>
    Username <input type="text" name="j_username" /><br />
    Password <input type="password" name="j_password" />
    <input type="submit" name="submit" value="Login" />
  </form>
</body>
</html>
