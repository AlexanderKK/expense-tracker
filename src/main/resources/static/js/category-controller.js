const categoryAction = document.querySelector(".add-category .add-category__actions button");
const categoryName = document.querySelector(".add-category #categoryName");
let isCreateCategoryCancelled = false;

categoryAction.addEventListener("click", createCategory);

/**
 * Front-End Validation
 */
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

/**
 * Back-End Validation
 */
function createCategory() {
	if(isCreateCategoryCancelled) {
		return;
	}

	categoryName.value = categoryName.value.trim();

	const requestOptions = {
		method: "POST",
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json'
		},
		body: JSON.stringify({
			name: categoryName.value
		})
	};

	const addCategoryURL = `${window.location.origin}/categories/add`;

	fetch(addCategoryURL, requestOptions)
		.then(response => {
			if(response.ok) {
				categoryName.value = "";

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
