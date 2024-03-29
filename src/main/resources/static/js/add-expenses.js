const addExpensesControls = document.querySelectorAll(".add-expenses__control");
const addExpensesBtn = document.querySelector(".add-expenses__actions button");
const categorySelect = document.querySelector("#category");

const audioControls = new Audio('/sounds/squiggle.mp3');
const audioActions = new Audio(`${window.location.origin}/sounds/splits.mp3`);

const dates = document.querySelectorAll(".date");

/**
 * Load current date
 */
dates.forEach(date => {
	const today = new Date();

	const day = today.getDate();
	const month = ("0" + (today.getMonth() + 1)).slice(-2);
	const year = today.getFullYear();

	date.innerText = `${day}.${month}.${year}`;
});

/**
 * Play music on input focus and submit
 */
addExpensesControls.forEach((element) => {
	element.addEventListener("focus", function(evt) {
		audioControls.play();
	});
});

addExpensesBtn.addEventListener("click", function(evt) {
	audioActions.play();
});

/**
 * Load categories
 */
window.addEventListener("load", loadCategories);
categorySelect.addEventListener("focus", loadCategories);

function loadCategories() {
	const requestOptions = {
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json',
		}
	};

	const loadCategoriesURL = `${window.location.origin}/categories`;

	fetch(loadCategoriesURL, requestOptions)
		.then(response => response.json())
		.then(categories => {
			categorySelect.innerHTML = `<option value="">Select a category</option>`;

			for (const category of categories) {
				categorySelect.innerHTML += `<option value="${category.id}">${category.name}</option>`;
			}
		});
}
