<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/styles7.css"/>
<title>New Credit Card | Emirates NBD</title>
</head>
<body>  
  <jsp:include page="header.jsp"></jsp:include>
  <div class="tab" style="margin:5px 1px">
    <table>
      <tr>
        <td>
          <h3 style="color:#9FE2BF;padding:15px 10px">New Credit Card
            &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
            &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
            &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
            &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
            &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
             
            <a href="mainmenu.jsp" class="button-class" style="width:75px;height:20px">Back</a>
            <a href="login.jsp" class="button-class" style="width:75px;height:20px">Log Out</a>      
          </h3>
        </td>          
      </tr>
    </table>      
  </div>  
  <form action="newcreditcard" method="post" style="padding:10px 20px">
   <table style="padding:0px 5px">                    
    <tr>
      <td><label>Name</label></td>
      <td><input type="text" size="25px" id="name" name="name" required="required" autofocus required/></td>            
    </tr> 
    <tr>
      <td><label>DOB</label></td>
      <td><input type="date" size="25px" id="dob" name="dob" required="required" placeholder="DD/MM/YYYY"/></td>            
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
      <td><Label>Credit Card Type</Label></td>
      <td>
          <select name="creditcard_type" id="creditcard_type">
            <option value="VISA">Visa</option>
            <option value="MASTER_CARD">Master Card</option>
            <option value="AMERICAN_EXPRESS">American Express</option>
            <option value="DISCOVER">Discover</option>
          </select>
      </td>
    </tr>          
    <tr>
      <td></td>
      <td><input type="submit" value="Issue"/></td>      
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