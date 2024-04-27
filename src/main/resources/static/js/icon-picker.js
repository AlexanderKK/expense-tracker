const dropdown = document.querySelector(".dropdown");
const input = document.getElementById("iconSelect");
const listOfOptions = document.querySelectorAll(".icon-option");

const body = document.body;
const toggleDropdown = (event) => {
	event.stopPropagation();
	dropdown.classList.toggle('opened');
};

const selectOption = (event) => {
	input.value = event.currentTarget.textContent;
};

const closeDropdownFromOutside = () => {
	if (dropdown.classList.contains('opened')) {
		dropdown.classList.remove('opened');
	}
};

body.addEventListener('click', closeDropdownFromOutside);

listOfOptions.forEach((option) => {
	option.addEventListener('click', selectOption);
});

dropdown.addEventListener('click', toggleDropdown);

document.querySelectorAll(".icon-option").forEach((e) => {
	if(e === undefined){}
	else{
	e.addEventListener("click", () => {
		// input.setAttribute("placeholder", e.firstChild.className.split(" ")[2])
		document.querySelector(".selected-icon").innerHTML = "Selected icon:" + e.outerHTML
		document.querySelector(".selected-icon").setAttribute("data-icon",e.classList)
		console.log(e)
	})
}
})