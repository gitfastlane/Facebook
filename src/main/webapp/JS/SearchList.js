
function requestSend(id){
	var httpRequest = new XMLHttpRequest();
	httpRequest.open("POST", "member_friendAdd.do?friendID="+id, true);
	httpRequest.setRequestHeader("asdasd","asdasd");
	httpRequest.send();
	
	let inputButton = document.getElementById(id);
	inputButton.style.display = "none";
}
