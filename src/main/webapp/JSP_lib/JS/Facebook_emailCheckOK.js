
let userEmailKey = document.getElementById("userEmailKey");
let emailKey = document.getElementById("emailKey");

function checkEmailKey(email){
	if(userEmailKey.value==emailKey.value){
		opener.document.member_signup.email.value = email;
		opener.document.member_signup.emailchk.value = 1;
		window.close();	
	}else{
		alert("잘못된 인증번호입니다. 다시 시도하세요.");
		window.close();	
	}
}