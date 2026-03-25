var xhr;

function getCredirCardDetails(username){	
	xhr = new XMLHttpRequest();
	xhr.open("GET", "creditcardnosearch?username="+username, true);
	xhr.onreadystatechange = stageChanged;
	xhr.send();	
}
function stageChanged(){	
	var ccNo;	
	var s;
	if(xhr.readyState == 4 && xhr.status == 200){
		var data = xhr.responseText;		
		if(data.length > 0){
			/*alert('Output' + data);*/
			var obj = JSON.parse(data);
			/*alert('Javascript Object' + obj);*/
			ccNo = obj.cardNo;	
			/*alert(ccNo);*/
			document.getElementById("cardno").innerHTML = ccNo;		
			s = document.getElementById("cardno");
		    s.value = ccNo;
			document.getElementById("cardno").nodeValue = ccNo;
		}						
	}		
}