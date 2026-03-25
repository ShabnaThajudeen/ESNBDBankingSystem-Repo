<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/styles7.css"/>
<title>Credit Card Transaction | Emirates NBD</title>
</head>
<body>  
  <jsp:include page="header.jsp"></jsp:include>
  <div class="tab" style="margin:5px 1px">
    <table>
      <tr>
        <td>
          <h3 style="color:#9FE2BF;padding:15px 10px">Authorize Credit Card Transaction
            &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
            &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
            &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
            &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
             
            <a href="mainmenu.jsp" class="button-class" style="width:75px;height:20px">Back</a>
            <a href="login.jsp" class="button-class" style="width:75px;height:20px">Log Out</a>      
          </h3>
        </td>          
      </tr>
    </table>      
  </div>  
  <form action="creditcardtransaction" style="padding: 10px 30px">
   <table>                    
    <tr>
      <td><label>Name</label></td>
      <td><input type="text" size="25px" id="name" name="name" required="required" autofocus required/></td>            
    </tr>
    <tr>
      <td><label>Card Number</label></td>
      <td><input type="text" size="25px" id="cardno" name="cardno" required="required" placeholder="****************"/></td>        
    </tr>
    <tr>
     <td><label>CVV2/CVC2</label></td>
     <td><input type="text" size="25px" id="cvv" name="cvv" required="required" placeholder="***"/></td>
    </tr>  
    <tr>
     <td><label>Amount</label></td>
     <td><input type="text" size="25px" id="amount" name="amount" required="required"/></td>
    </tr>         
    <tr>
      <td></td>
      <td><input type="submit" value="Authorize"/></td>      
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