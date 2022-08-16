let pw = document.getElementById("pw");
let pwchk = document.getElementById("pwchk");
let pwchkMessage = document.getElementById("pwchkMessage");
pwchk.addEventListener('keyup',passwordCheck);
function passwordCheck(){
	if(pw.value==pwchk.value){
		pwchkMessage.innerHTML = "패스워드가 일치합니다.";
		pwchkMessage.style.color = "blue";
		return true;
	}else{
		pwchkMessage.innerHTML = "패스워드가 일치하지 않습니다.";
		pwchkMessage.style.color = "red";
		return false;	
	}
}