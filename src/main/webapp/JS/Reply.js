function openComment(no){
	let writeBox = document.getElementById('writeBox'+no);
	let chkComment = document.getElementById('chkComment'+no);
	if(chkComment.value == 0){
		writeBox.style.display = "block"
		chkComment.value = 1;
	}else{
		writeBox.style.display = "none"
		chkComment.value = 0;		
	}
}

function replyBoxOpen(no){
	let textarea = document.getElementById('textarea'+no);
	let chkComment = document.getElementById('chkComment'+no);
	if(chkComment.value == 0){
		textarea.style.display = "block"
		chkComment.value = 1;
	}else{
		textarea.style.display = "none"
		chkComment.value = 0;		
	}
}

function replyOpen(no){
	let rereplyarea = document.getElementById('rereplyarea'+no);
	let reComment = document.getElementById('reComment'+no);
	if(reComment.value == 0){
		rereplyarea.style.display = "block"
		reComment.value = 1;
	}else{
		rereplyarea.style.display = "none"
		reComment.value = 0;	
	}
}

function rereplyBoxOpen(no){
	let rereTextarea = document.getElementById('rereTextarea'+no);
	let rereComment = document.getElementById('rereComment'+no);
	if(rereComment.value == 0){
		rereTextarea.style.display = "block"
		rereComment.value = 1;
	}else{
		rereTextarea.style.display = "none"
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


$('.write_btn').on('click', function(e){
	$.ajax({
		url:'board_replyShow.do',
		data: {"b_no_pk": $(e.target).data("no")},
		datatype: 'json',
		type: 'post',
		success: function(data){
			$("#writeBox"+$(e.target).data("no")).empty();
			$("#writeBox"+$(e.target).data("no")).append(data);
			console.log(data);
		}, error: function(e) {
			console.log("문제 발생");
		}
	})
})

function doDelete(no, boxNo){
	$.ajax({
		cache: false,
		url:'board_replyDelete.do',
		data: {"b_no_pk": boxNo,
				"b_no_pk_delete": no},
		datatype: 'json',
		type: 'post',
		success: function(data){
			$("#writeBox"+boxNo).empty();
			$("#writeBox"+boxNo).append(data);
			console.log(data);
		}, error: function(e) {
			console.log("문제 발생");
		}
	})
}

function doForm(no, boxNo){
	var formData = $("#form_textarea"+no).serialize();
	
	$.ajax({
		cache: false,
		url: 'board_replySend.do',
		type: 'POST',
		data: formData,
		datatype: 'json',
		success: function(data){
			$("#writeBox"+boxNo).empty();
			$("#writeBox"+boxNo).append(data);
		}, error: function(e) {
			console.log("doForm 문제 발생");
		}
	})
}

/*----------- 댓글&답글 기능 end ---------*/