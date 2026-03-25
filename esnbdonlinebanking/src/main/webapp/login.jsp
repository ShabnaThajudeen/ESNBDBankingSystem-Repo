<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/styles.css"/>
    <title>Emirates NBD | Login</title>
  </head>

  <body style="background-image: url('images/login-screen-image.jpg'); background-repeat: no-repeat; background-size: 100% 100%; background-attachment: fixed;">
    <br><br><br><br>
    <form action="mainmenudisplay" method="post">
      <h1 style="color:#FFF">Welcome to Emirates NBD Banking System</h1>
      
      <table>
        <tr>
          <td>
            <h2 style="color:#FFF;text-align: left"><label>User Name</label></h2>            
          </td>
          <td>
            <input type="text" size="30" id="name" name="username" placeholder="USER NAME" autofocus required/>
          </td>
        </tr>
        <tr>
          <td>
            <h2 style="color:#FFF;text-align: left"><label>Password</label></h2>            
          </td>
          <td>
            <input type="password" size="30" id="pwd" name="pwd" placeholder="PASSWORD"/>
          </td>
        </tr>
        <tr>
          <td>                      
          </td> 
          <td>
            <input type="submit" value="LOGIN"/>           
          </td>          
        </tr>
      </table>             
    </form>       
  </body>
</html>