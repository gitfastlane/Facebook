let t_hashTag = document.getElementById("t_hashTag");
let tagMessage = document.getElementById("tagMessage");
t_hashTag.addEventListener('keyup',checkHash);
function checkHash(){
	let inputTag = t_hashTag.value;
	inputTag = inputTag.trim()
	let checkTag = inputTag.slice(0,1);
	if(checkTag == "#"){
		tagMessage.innerHTML = "#을 붙여서 여러개의 해시테그를 작성할 수 있습니다.";
		tagMessage.style.color = "rgb(56, 116, 214)";
		return true;
	}else if(!inputTag){
		tagMessage.innerHTML = "게시물을 해시테그로 표현해주세요.";
		tagMessage.style.color = "black";
		return true;		
	}else{
		tagMessage.innerHTML = "#을 문자 앞에 붙여주세요.";
		tagMessage.style.color = "rgb(56, 116, 214)";
		return false;		
	}
}