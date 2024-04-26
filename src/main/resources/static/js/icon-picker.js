const dropdown = document.querySelector(".dropdown");
const input = document.querySelector(".dropdown input");
const listOfOptions = document.querySelectorAll(".option");

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

document.querySelectorAll(".option").forEach((e) => {
	e.addEventListener("click", () => {
		document.getElementById("iconSelect").setAttribute("placeholder", e.firstChild.className.split(" ")[2])
		document.getElementById("iconPicked").innerHTML = "Selected Icon:" + e.outerHTML
		console.log(e)
	})
})