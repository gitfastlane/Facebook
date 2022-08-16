let pw = document.getElementById("pw");
let pwchk = document.getElementById("pwchk");
let pwchkMessage = document.getElementById("pwchkMessage");
let email = document.getElementById("email");
let emailchk = document.getElementById("emailchk");
let emailchkMessage = document.getElementById("emailchkMessage");

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

function idCheckForm(){
	window.open("JSP_lib/Facebook_idCheckForm.jsp","idCheckForm","width=400 height=250 top=300 left=800");
}

email.addEventListener('keyup',emailKeyup);
function emailKeyup(){
	emailchk.value=0;
}
function emailCheck(){
	let userEmail = email.value;
	if(!userEmail){
		alert("이메일 주소를 입력하세요.");
	}else{
		window.open("JSP_lib/Facebook_emailCheck.jsp?email="+userEmail,"emailCheck","width=400 height=250 top=300 left=800");	
	}
}

function allCheck(){
	if(passwordCheck()&&emailchk.value == 1){
		return true;
	}else if(emailchk.value != 1){
		emailchkMessage.innerHTML = "이메일 인증이 필요합니다.";
		emailchkMessage.style.color = "red";
		return false;
	}else{
		return false;		
	}
}