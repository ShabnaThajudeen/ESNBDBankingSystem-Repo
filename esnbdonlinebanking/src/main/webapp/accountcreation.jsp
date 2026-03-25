<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/styles3.css"/>
    <title>Account Creation | Emirates NBD</title>
  </head>
  <body>
    <jsp:include page="header.jsp"></jsp:include>
    <div class="tab" style="margin:5px 5px">
      <table>
        <tr>
          <td>
            <h3 style="color:#9FE2BF;padding:0px 8px">Account Creation
              &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
              &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
              &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
              &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
              &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
              <a href="mainmenu.jsp" class="button-class" style="border-radius: 15px">Back</a>
              <a href="login.jsp" class="button-class" style="border-radius: 15px">Log Out</a>      
            </h3>
          </td>          
        </tr>
      </table>      
    </div>
    <form action="createaccount" method="post">
      <table style="padding:20px 10px">
        <tr>
          <td><Label>Name</Label></td>
          <td><input type="text" name="name" size="30px" autofocus required required="required"/></td>
        </tr>
        <tr>
          <td><Label>DOB</Label></td>
          <td><input type="date" name="dob" placeholder="DD/MM/YYYY" required="required"/></td>
        </tr>
        <tr>
          <td><Label>Address</Label></td>
          <td><input type="text" name="addr" style="width:300px; height:70px" required="required"/></td>
        </tr>
        <tr>
          <td><Label>Email ID</Label></td>
          <td><input type="email" name="mail_id" size="30px" required="required"/></td>
        </tr>
        <tr>
          <td><Label>Type of Account</Label></td>
          <td>
            <select name="acc_type">
              <option value="SB">SB Account</option>
              <option value="Current">Current Account</option>
            </select>
          </td>
        </tr>
        <tr>
          <td></td>
          <td><input type="submit" value="Create"/></td>          
        </tr>
      </table>
    </form>
    
    
    
    <script>
      const form = document.querySelector('form');
      const input = document.querySelector('input');

      form.addEventListener('submit', function(event) {
    	  event.preventDefault();    	  
    	  
    	  if(input.value.trim() === '') {
    		  alert('Please fill out the input field!');
          } 
    	  else {
    		  //Form submission logic goes here
    		  form.submit();
    	  }    
      });
     </script>
  </body>
</html>