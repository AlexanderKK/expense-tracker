const categoryAction = document.querySelector(".add-category__actions button");
const categoryName = document.querySelector(".add-category #categoryName");
const categoryIcon = document.querySelector(".add-category #iconSelect");
const categorySelect = document.querySelector("#category");

let isCreateCategoryCancelled = false;

window.addEventListener("load", loadCategories);
categorySelect.addEventListener("focus", loadCategories);

function loadCategories() {
	const accessToken = localStorage.getItem("accessToken");

	const requestOptions = {
		headers: jsonAuthHeaders(accessToken)
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

categoryName.addEventListener("keyup", function(evt) {
	const categoryNameError = categoryName.nextElementSibling;
	const categoryNameRegex = /^[A-Z](?:[a-zA-Z]\s?){1,19}$/;

	if(!categoryNameRegex.test(categoryName.value)) {
		categoryName.classList.add("is-invalid");
		categoryNameError.innerText = "Please enter a category containing from 2 to 20 letters.\nEach new word should start with a capital letter.";

		isCreateCategoryCancelled = true;
	} else {
		categoryName.classList.remove("is-invalid");
		categoryNameError.innerText = "";
		isCreateCategoryCancelled = false;

		if(evt.key === "Enter" && evt.keyCode === 13) {
			createCategory();
		}
	}
});

categoryAction.addEventListener("click", createCategory);

function createCategory() {
	if(isCreateCategoryCancelled) {
		return;
	}

	categoryName.value = categoryName.value.trim();

	const accessToken = localStorage.getItem("accessToken");

	const requestOptions = {
		method: "POST",
		headers: jsonAuthHeaders(accessToken),
		body: JSON.stringify({
			name: categoryName.value,
			icon: categoryIcon.placeholder
		})
	};

	const addCategoryURL = `${location.origin}/categories/add`;

	fetch(addCategoryURL, requestOptions)
		.then(response => {
			if(response.ok) {
				clearCategoryControls();

				toastify(categoryAddSuccessOptions);
				lowLag.play("success");

				return;
			}

			return response.json();
		})
		.then(json => {
			handleValidationErrors(json);
		});
}

function clearCategoryControls() {
	categoryName.value = "";
	categoryIcon.placeholder = "Pick an icon";
	document.querySelector(".selected-icon").innerHTML = "Selected Icon:";
}
