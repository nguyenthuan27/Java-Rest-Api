/**
 * 
 */

		function myFunction() {
			var dropdownContent = document.querySelector('.dropdown-content');
			dropdownContent.classList.toggle('dropdown-toggle');
		}
		const openPopups = document.getElementsByClassName("open-popup-btn");
	    for(let idx= 0; idx < openPopups.length; idx++) {
	        openPopups[idx].addEventListener("click", () => {
	            document.getElementsByClassName("popup")[0].classList.add("active");
	        })
	    }
	    document.getElementById("dismiss-popup-btn").addEventListener("click", function () {
	    	console.log("CANCEL")
	        document.getElementsByClassName("popup")[0].classList.remove("active");
	    });
