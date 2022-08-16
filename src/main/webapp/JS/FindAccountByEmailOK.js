let emailKey = document.getElementById("emailKey");
let userEmailKey = document.getElementById("userEmailKey");
function checkKey(){
	if(emailKey.value != userEmailKey.value){
		alert("인증번호가 틀렸습니다. 다시 시도하세요.");
	}else{
		alert("인증번호가 확인되었습니다.");
	}
}