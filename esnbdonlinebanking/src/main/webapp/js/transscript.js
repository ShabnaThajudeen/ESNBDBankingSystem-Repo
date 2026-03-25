var xh;
function debitProcess(accno, amount, useracc){
	xh = new XMLHttpRequest();
	xh.open("GET", "debit?accno="+accno+"&amt="+amount+"&useracc="+useracc, true);	 
	xh.onreadystatechange = function(){			
		if(xh.readyState == 4 && xh.status == 200){
			var data = xh.responseText;
			if(data.length > 0){
				var obj = JSON.parse(data);
				var msgg = obj.message;		
				alert(msgg);	
				document.getElementById("trans").innerHTML = msgg;		
			}					
		}		
							
	};
	xh.send();
}

var xmhr;
function creditProcess(accno, amount, useracc){	
	xmhr = new XMLHttpRequest();
	xmhr.open("GET", "credit?accno="+accno+"&amt="+amount+"&useracc="+useracc, true);	 
	xmhr.onreadystatechange = function(){			
		if(xmhr.readyState == 4 && xmhr.status == 200){
			var data = xmhr.responseText;
			if(data.length > 0){
				var obj = JSON.parse(data);
				var msgg = obj.message;		
				alert(msgg);
				document.getElementById("trans").innerHTML = msgg;
			}					
		}
							
	};
	xmhr.send();
}
