let add_location = document.getElementById("add_location");
let add_school = document.getElementById("add_school");
let add_work = document.getElementById("add_work");
let add_relation = document.getElementById("add_relation");
let add_save = document.getElementById("add_save");

let relation2 = document.getElementById("relation2");
let relation3 = document.getElementById("relation3");

let up_location = document.getElementsByClassName("up_location");
let up_location_input = document.getElementsByClassName("up_location_input");
let up_school = document.getElementsByClassName("up_school");
let up_school_input = document.getElementsByClassName("up_school_input");
let up_work = document.getElementsByClassName("up_work");
let up_work_input = document.getElementsByClassName("up_work_input");
let up_relation = document.getElementsByClassName("up_relation");
let up_relation_input = document.getElementsByClassName("up_relation_input");
let up_save = document.getElementById("up_save");

let form_id = document.getElementById("form_id");

function locationAdd(){
	add_location.style.display = "block";
	add_save.style.display = "block";	
}
function schoolAdd(){
	add_school.style.display = "block";		
	add_save.style.display = "block";	
}
function workAdd(){
	add_work.style.display = "block";		
	add_save.style.display = "block";	
}
function relationAdd(){
	add_relation.style.display = "block";		
	add_save.style.display = "block";	
}
function openRelation2(){
	relation2.style.display = "block";	
}
function openRelation3(){
	relation3.style.display = "block";	
}
function updateLocation(){
	for(let i=0;i<up_location.length;i++){
		up_location[i].style.display = "none";
	}
	for(let i=0;i<up_location_input.length;i++){
		up_location_input[i].style.display = "inline";		
	}
	up_save.style.display = "block";
}
function updateSchool(){
	for(let i=0;i<up_school.length;i++){
		up_school[i].style.display = "none";
	}
	for(let i=0;i<up_school_input.length;i++){
		up_school_input[i].style.display = "inline";		
	}
	up_save.style.display = "block";
}
function updateWork(){
	for(let i=0;i<up_work.length;i++){
		up_work[i].style.display = "none";
	}
	for(let i=0;i<up_work_input.length;i++){
		up_work_input[i].style.display = "inline";		
	}
	up_save.style.display = "block";
}
function updateRelation(){
	for(let i=0;i<up_relation.length;i++){
		up_relation[i].style.display = "none";
	}
	for(let i=0;i<up_relation_input.length;i++){
		up_relation_input[i].style.display = "inline";		
	}
	up_save.style.display = "block";
}

/* delete */
function deleteLocation(){
	for(let i=0;i<up_location_input.length;i++){
		up_location_input[i].value = "";		
	}
	form_id.submit();
}
function deleteSchool(){
	for(let i=0;i<up_school_input.length;i++){
		up_school_input[i].value = "";		
	}
	form_id.submit();
}
function deleteWork(){
	for(let i=0;i<up_work_input.length;i++){
		up_work_input[i].value = "";		
	}
	form_id.submit();
}
function deleteRelation(){
	for(let i=0;i<up_relation_input.length;i++){
		up_relation_input[i].value = "";	
	}
	form_id.submit();
}