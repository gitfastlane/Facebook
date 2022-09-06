
function requestSend(id){
	var httpRequest = new XMLHttpRequest();
	httpRequest.open("POST", "member_friendAdd.do?friendID="+id, true);
	httpRequest.setRequestHeader("asdasd","asdasd");
	httpRequest.send();
	
	let inputButton = document.getElementById(id);
	inputButton.style.display = "none";
}

$('.like_btn').on('click', function(e) {
	$.ajax({
		url: 'board_pushLike.do',
		data: { "b_no_pk": $(e.target).data("no") },
		datatype: 'text',
		type: 'post',
		success: function(data) {
			document.getElementById('result_like' + $(e.target).data("no")).innerHTML = data;
			if((document.getElementById('img_like' + $(e.target).data("no")).src).includes("img/LikeOn.png")){
				document.getElementById('img_like' + $(e.target).data("no")).src = "img/LikeOff.png";
			}else{
				document.getElementById('img_like' + $(e.target).data("no")).src = "img/LikeOn.png";				
			}
		}, error: function(e) {

		}
	})
})

/*--------- 댓글&답글 기능 ------------*/
function openComment(no){
	let writeBox = document.getElementById('writeBox'+no);
	let chkComment = document.getElementById('chkComment'+no);
	if(chkComment.value == 0){
		writeBox.style.display = "block";
		chkComment.value = 1;
	}else{
		writeBox.style.display = "none";
		chkComment.value = 0;		
	}
}

function replyBoxOpen(no){
	let textarea = document.getElementById('textarea'+no);
	let chkComment = document.getElementById('chkComment'+no);
	if(chkComment.value == 0){
		textarea.style.display = "block";
		chkComment.value = 1;
	}else{
		textarea.style.display = "none";
		chkComment.value = 0;		
	}
}

function replyOpen(no){
	let rereplyarea = document.getElementById('rereplyarea'+no);
	let reComment = document.getElementById('reComment'+no);
	if(reComment.value == 0){
		rereplyarea.style.display = "block";
		reComment.value = 1;
	}else{
		rereplyarea.style.display = "none";
		reComment.value = 0;		
	}
}

function rereplyBoxOpen(no){
	let rereTextarea = document.getElementById('rereTextarea'+no);
	let rereComment = document.getElementById('rereComment'+no);
	if(rereComment.value == 0){
		rereTextarea.style.display = "block";
		rereComment.value = 1;
	}else{
		rereTextarea.style.display = "none";
		rereComment.value = 0;		
	}
}

function autoHeight(obj){
    if(obj){
        obj.style.height = "15px";
        let textHeight = obj.scrollHeight;
        obj.style.height = textHeight+5+'px';
    }
}

function sendReply(no){
	let content = document.getElementById('content'+no);
	if(content.value){
		return true;
	}else{
		return false;
	}
}
/*----------- 댓글&답글 기능 end ---------*/