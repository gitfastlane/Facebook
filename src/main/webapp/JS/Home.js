
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

