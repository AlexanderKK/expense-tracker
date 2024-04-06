const addExpensesControls = document.querySelectorAll(".add-expenses__control");
const addExpensesBtn = document.querySelector(".add-expenses__actions button");
const categorySelect = document.querySelector("#category");

const dates = document.querySelectorAll(".date");

/**
 * Load current date
 */
dates.forEach(date => {
	const today = new Date();

	const day = ("0" + today.getDate()).slice(-2);
	const month = ("0" + (today.getMonth() + 1)).slice(-2);
	const year = today.getFullYear();

	date.innerText = `${day}.${month}.${year}`;
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
	categorySelect.innerHTML = `<option value="">Select a category</option>`;

	fetch(loadCategoriesURL, requestOptions)
		.then(response => response.json())
		.then(categories => {
			for (const category of categories) {
				categorySelect.innerHTML += `<option value="${category.id}">${category.name}</option>`;
			}
		});
}
