<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.esnbd.esnbdonline.dto.AccountTransactions, java.util.*" %>
<jsp:include page="bankstatement.jsp"></jsp:include>

<html>
  <head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/styles5.css"/>
    <title>Bank Statement | Emirates NBD</title>
  </head>
  <body>
    <table border="1" style="width:800px;background-color:#AFE1AF;margin:15px 15px" >
      <tr style="text-align: center">
        <th><label>Sl. No</label></th>
        <th><label>Date</label></th>
        <th><label>Description</label></th>
        <th><label>Cheque No</label></th>
        <th><label>Withdraw</label></th>
        <th><label>Deposit</label></th>
        <th><label>Available Balance</label></th>
      </tr>
      <% List<AccountTransactions> transactions = new ArrayList<AccountTransactions>();         
         transactions = (List<AccountTransactions>)request.getAttribute("transactionsList");
         int index = 1;
         for(AccountTransactions transaction: transactions){
      %>
      <tr style="text-align: center">
        <td style="text-align: center"><%= index %></td>
        <td><%= transaction.getDate()%></td>
        <td><%= transaction.getDescription()%></td>
        <td><%= transaction.getChequeNo()%></td>
        <td><%= transaction.getWithdraw()%></td>
        <td><%= transaction.getDeposit()%></td>
        <td><%= transaction.getAccBalance()%></td>        
      </tr>
      <% index++; 	 
         }
      %>
    </table>
  </body>
</html>