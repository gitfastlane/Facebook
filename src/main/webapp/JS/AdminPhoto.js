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