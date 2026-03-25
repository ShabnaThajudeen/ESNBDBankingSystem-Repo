<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
  <head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/styles2.css"/>
    <title>Emirates NBD | Home</title>
  </head>
  <body>    
    <jsp:include page="header.jsp"></jsp:include> 
     <div class="tab" style="height:50px">
       <table>
         <tr>
           <th style="font-size: 20px;color:#9FE2BF">Home</th>
           <td>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</td>
           <td>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</td>
           <td>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</td>  
           <td>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</td>  
           <td>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</td>
           <td style="text-align: right"><a href="login.jsp" class="button-class" style="width:60px;height:20px">Log Out</a></td>
         </tr>
       </table>
     </div> 
     <div class="tab" style="height:50px; margin:5px 0px">  
       <table>         
         <tr>
           <td><a href="accountcreation.jsp" class="button-class">Create Account</a></td>
           <td><a href="useraccountselection.jsp" class="button-class">Debit Credit Transactions</a></td>
           <td><a href="bankstatement.jsp" class="button-class">Display Bank Statement</a></td>
           <td><a href="issuenewcreditcard.jsp" class="button-class">New Credit Card</a></td>
           <td><a href="authorize_credit_card_transactions.jsp" class="button-class">Authorize Credit Card Transactions</a></td>
           <td><a href="creditstatement.jsp" class="button-class">Credit Card Statement</a></td>
           
                  
         </tr>
       </table>             
    </div>   
    
    <% session = request.getSession();
       String name = session.getAttribute("Name").toString();
       out.println("<p style=\"text-align:left; font-size=30px; color:black\"> Welcome " + name + ",</p>");
    %>    
  </body>
</html>