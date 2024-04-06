const addExpensesBtn = document.querySelector(".add-expenses__actions button");
const categorySelect = document.querySelector("#category");

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
