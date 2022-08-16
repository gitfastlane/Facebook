
let photo_checkbox = document.getElementsByClassName("photo_checkbox");
let delete_submit = document.getElementById("delete_submit");
let manage_button = document.getElementById("manage_button");
let sw = 0;
let flag = false;
function managePhoto() {
	if (sw == 0) {
		for (let i = 0; i < photo_checkbox.length; i++) {
			photo_checkbox[i].style.float = "left";
			photo_checkbox[i].style.display = "block";
		}
		delete_submit.style.display = "inline";
		manage_button.value = "취소";
		sw = 1;
		flag = true;
	} else {
		for (let i = 0; i < photo_checkbox.length; i++) {
			photo_checkbox[i].style.display = "none";
			photo_checkbox[i].checked = false;
		}
		delete_submit.style.display = "none";
		manage_button.value = "사진관리";
		sw = 0;
		flag = false;
	}
}
function deleteCheck(){
	if(flag){
		if(confirm("사진을 삭제할 경우 게시물도 함께 삭제됩니다. 삭제를 원하시면 확인을 눌러주세요.")){
			return true;	
		}else{
			return false;
		}
	}
}

let album_cnt = document.getElementById("album_cnt").value;
for (let i = 1; i <= album_cnt; i++) {
	$(function() {
		$("#album_photo" + i).on("click", function() {
			$("#album_popup" + i).fadeIn();
		});
		$("#album_popup" + i).on("click", function() {
			$("#album_popup" + i).fadeOut();
		});
	});
}

