<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/styles6.css"/>
<title>Transactions | Emirates NBD</title>
</head>
<body>  
  <jsp:include page="header.jsp"></jsp:include>
  <div class="tab">
      <table>
        <tr>
          <td>
            <h3 style="color:#9FE2BF;padding:18px 8px">User Account Selection
              &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
              &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
              &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
              &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
              &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
              <a href="mainmenu.jsp" class="button-class" style="border-radius: 10px">Back</a>
              <a href="login.jsp" class="button-class" style="border-radius: 10px">Log Out</a>      
            </h3>
          </td>          
        </tr>
      </table>      
    </div> 
  <% session = request.getSession();      
  %>  
  <form action="perform_transactions.jsp" method="post"> 
  <table>     
     <tr>
        <td></td>
        <td><input type="hidden" size="25px" name="username" id="username" value="<%= session.getAttribute("UserName").toString()%>"/></td>
      </tr>            
      <tr>
        <td><label style="color:#9FE2BF">User Accounts</label></td>   
        <td>
          <select name="accounts" id="accounts" style="width: 200px" autofocus required onfocus="getAccountDetails(username.value)">            
            <option id="currentaccount" value=""></option>
            <option id="sbaccount" value=""></option>              
          </select>
        </td> 
        <td><input type="submit" value="Next"></td>    
      </tr>                           
  </table>     
  <script src="js/script.js"></script>  
  </form>      
</body>
</html>