let idchk = document.getElementById("idchk");
let idchkM = document.getElementById("idchkM");
idchk.addEventListener('keyup',idValueCheck);
function idValueCheck(){
	let idchkVal = idchk.value;
	idchkVal = idchkVal.trim();
	
	var patternEn = /[a-zA-Z]/;
	var patternKr = /[ㄱ-ㅎㅏ-ㅣ가-힣]/;
	var patternNu = /[0-9]/;
	var patternSp = /[`~!@#$%^&*()_+;'",.\|<>?:{}]/;
	
	if(idchkVal.length<5){
		idchkM.innerHTML = "5글자 이상 입력하세요.";
		idchkM.style.color = "red";
		return false;
	}else if(patternSp.test(idchkVal)||patternKr.test(idchkVal)){
		idchkM.innerHTML = "영문자, 숫자만 입력가능합니다.";
		idchkM.style.color = "red";
		return false;		
	}else{
		idchkM.innerHTML = "";
		return true;
	}
}