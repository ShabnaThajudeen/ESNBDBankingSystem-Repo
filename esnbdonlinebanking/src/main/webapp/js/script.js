var xhr;

function getAccountDetails(username){	
	xhr = new XMLHttpRequest();
	xhr.open("GET", "accountsearch?username="+username, true);
	xhr.onreadystatechange = stageChanged;
	xhr.send();	
}
function stageChanged(){	
	var accType;
	var accNo;
	var s;
	if(xhr.readyState == 4 && xhr.status == 200){
		var data = xhr.responseText;		
		if(data.length > 0){
			/*alert('Output' + data);*/
			var obj = JSON.parse(data);
			/*alert('Javascript Object' + obj);*/
			for(i = 0; i < obj.length; i++){
				accNo = obj[i].accountNo;
				accType = obj[i].accType;
				if(accType == 'Current'){
					document.getElementById("currentaccount").innerHTML = accNo + ' | ' + accType;
					s = document.getElementById("currentaccount");
					s.value = accNo;
					document.getElementById("currentaccount").nodeValue = accNo;										
				}	
				else{
					document.getElementById("sbaccount").innerHTML = accNo + ' | ' + accType;
					s = document.getElementById("sbaccount");
					s.value = accNo;
					document.getElementById("sbaccount").nodeValue = accNo;										
				}
			}		
		}		
	}	
}