<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/styles5.css"/>
    <title>Bank Statement | Emirates NBD</title>
  </head>
  <body>
    <jsp:include page="header.jsp"></jsp:include>
    <div class="tab" style="margin:5px 0px">
      <table>
        <tr>
          <td>
            <h3 style="color:#9FE2BF; padding:0px 6px">Bank Statement
              &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
              &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
              &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
              &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
              &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
              <a href="mainmenu.jsp" class="button-class" style="border-radius: 10px">Back</a>
              <a href="login.jsp" class="button-class" style="border-radius: 10px">Log Out</a>      
            </h3>
          </td>          
        </tr>
      </table>      
    </div> 
    <% session = request.getSession();      
    %>  
    
  <form action="displaystatement" method="post">
   <table style="text-align: left; padding:10px 20px">     
     <tr>
        <td></td>
        <td><input type="hidden" size="25px" name="username" id="username" value="<%= session.getAttribute("UserName").toString()%>"/></td>
      </tr>      
      <tr>
        <td><label>User Account</label></td>   
        <td>
          <select style="margin: 0px 10px" name="accounts" id="accounts" autofocus required onfocus="getAccountDetails(username.value)">            
            <option id="currentaccount" value=""></option>
            <option id="sbaccount" value=""></option>              
          </select>
        </td>            
      </tr> 
      <tr>
        <td></td>
      <tr>
        <td><label>Date Range:</label></td>
      </tr>  
      <tr>
        <td><label>From</label></td>
        <td><input type="date" name="fromdate" placeholder="DD/MM/YYYY" required="required"/></td>
        <td><label>To</label></td>
        <td><input type="date" name="todate" placeholder="DD/MM/YYYY" required="required"/></td>
        <td><input type="submit" value="Display"/></td>
      </tr>                              
    </table>
  </form> 
         
  <script src="js/script.js"></script>     
    
    
    
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