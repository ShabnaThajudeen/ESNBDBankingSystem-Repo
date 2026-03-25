<%@page import="com.esnbd.esnbdonline.dto.CreditCardTransactions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.esnbd.esnbdonline.dto.AccountTransactions, java.util.*" %>
<jsp:include page="creditstatement.jsp"></jsp:include>

<html>
  <head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/styles5.css"/>
    <title>Credit Card Statement | Emirates NBD</title>
  </head>
  <body>
    <table border="1" style="width:800px;background-color:#AFE1AF;margin:15px 15px" >
      <tr style="text-align: center">
        <th><label>Sl. No</label></th>
        <th><label>Date</label></th>
        <th><label>Description</label></th>       
        <th><label>Debit</label></th>        
        <th><label>Balance Limit</label></th>
      </tr>
      <% List<CreditCardTransactions> transactions = new ArrayList<CreditCardTransactions>();         
         transactions = (List<CreditCardTransactions>)request.getAttribute("creditTransactions");
         int index = 1;
         for(CreditCardTransactions transaction: transactions){
      %>
      <tr style="text-align: center">
        <td style="text-align: center"><%= index %></td>
        <td><%= transaction.getDate()%></td>
        <td><%= transaction.getDescription()%></td>
        <td><%= transaction.getDebit()%></td>
        <td><%= transaction.getBalanceLimit()%></td>               
      </tr>
      <% index++; 	 
         }
      %>
    </table>
  </body>
</html>