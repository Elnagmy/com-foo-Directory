function openTab(evt, TabName) {
    // Declare all variables
    var i, tabcontent, tablinks;

    // Get all elements with class="tabcontent" and hide them
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }

    // Get all elements with class="tablinks" and remove the class "active"
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }

    // Show the current tab, and add an "active" class to the link that opened the tab
    document.getElementById(TabName).style.display = "block";
    evt.currentTarget.className += " active";
    document.getElementById("defaultOpen").click();
}



//Get the modal
function openUpdate(evt, formName, index) {
	var modal = document.getElementById(formName);

	// Get the button that opens the modal
	var btn = document.getElementById(evt);

	// Get the <span> element that closes the modal
	var span = document.getElementsByClassName("close")[index];

	// When the user clicks on the button, open the modal 
	btn.onclick = function() {
	    modal.style.display = "block";
	}

	// When the user clicks on <span> (x), close the modal
	span.onclick = function() {
	    modal.style.display = "none";
	}

	// When the user clicks anywhere outside of the modal, close it
	window.onclick = function(event) {
	    if (event.target == modal) {
	        modal.style.display = "none";
	    }
	}
}


function openForm(evt, formName, index) {
	var modal = document.getElementById(formName);

	// Get the button that opens the modal
	var Link = document.getElementById(evt);

	// Get the <span> element that closes the modal
	var span = document.getElementsByClassName("closeForm")[index];

	// When the user clicks on the button, open the modal 
	Link.onclick = function() {
	    modal.style.display = "block";
	}

	// When the user clicks on <span> (x), close the modal
	span.onclick = function() {
	    modal.style.display = "none";
	}

	// When the user clicks anywhere outside of the modal, close it
	window.onclick = function(event) {
	    if (event.target == modal) {
	        modal.style.display = "none";
	    }
	}
}

function addMore(TableBodyId ){
	
	var TableBody =document.getElementById(TableBodyId);
	
	var tablerow =document.createElement("tr");
	var tablecell=document.createElement("td");
	var tablecell2=document.createElement("td");
	var TextNode1 = document.createTextNode("Direct Report StaffID");
	var input  =document.createElement("input");
	input.type = "text";
	input.setAttribute("name", "StaffID");
	tablecell.appendChild(TextNode1);
	tablecell2.appendChild(input);
	tablerow.appendChild(tablecell);
	tablerow.appendChild(tablecell2);
	TableBody.appendChild(tablerow);
	
}




function addMortoDelete (TableBodyID , btnId){
var TableBody =document.getElementById("DeleteTableID");
	
	var tablerow =document.createElement("tr");
	var tablecell=document.createElement("td");
	var tablecell2=document.createElement("td");
	var TextNode1 = document.createTextNode("StaffID");
	var input  =document.createElement("input");
	input.type = "text";
	input.setAttribute("name", "StaffID");
	tablecell.appendChild(TextNode1);
	tablecell2.appendChild(input);
	tablerow.appendChild(tablecell);
	tablerow.appendChild(tablecell2);
	TableBody.appendChild(tablerow);
	
}


function confirmDelte(){
	if ((confirm("Are you sure you wants to delete this staff permanently?"))){
		return false;
	};
	
}