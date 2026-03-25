<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/styles4.css"/>
<title>Transactions | Emirates NBD</title>
</head>
<body>  
  <jsp:include page="header.jsp"></jsp:include>
  <div class="tab" style="margin:5px 1px">
    <table>
      <tr>
        <td>
          <h3 style="color:#9FE2BF; padding:14px 5px">Process Debit or Credit Operations
            &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
            &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
            &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
            &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
             
            <a href="useraccountselection.jsp" class="button-class" style="width:80px;text-align:center;height:20px">Back</a>
            <a href="login.jsp" class="button-class" style="width:90px;text-align:center;height:20px">Log Out</a>      
          </h3>
        </td>          
      </tr>
    </table>      
  </div>  
  <% session = request.getSession();      
  %> 
  <table style="padding: 5px 15px">                    
    <tr>
      <td><label>Account Number</label></td>
      <td><input type="text" size="25px" id="acc_no" name="acc_no" required="required"/></td>            
    </tr>
    <tr>
      <td><label>Amount</label></td>
      <td><input type="text" size="25px" id="amount" name="amount" required="required"/></td>        
    </tr>
    <tr>
     <td><label>User Account Selected</label></td>
     <td><input type="text" size="25px" id="useracc" name="useracc" value="<%= request.getParameter("accounts")%>" readonly="readonly"/></td>
    </tr>          
    <tr>
      <td><button class="button-class" style="width:125px" id="debit" onmouseup="debitProcess(acc_no.value, amount.value, useracc.value)">Debit From</button></td>
      <td><button class="button-class" style="width:125px" id="credit" onmouseup="creditProcess(acc_no.value, amount.value, useracc.value)">Credit To</button></td>      
    </tr> 
    <tr>
      <td></td>
      <td><span id="trans" style="color:#033E3E;background-color: #9FE2BF"></span></td>      
    </tr>                
  </table>  
  <script src="js/transscript.js"></script>   
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