const categoryAction = document.querySelector(".add-category .add-category__actions button");
const categoryName = document.querySelector(".add-category #categoryName");

categoryAction.addEventListener("click", createCategory);

categoryName.addEventListener("keypress", function(evt) {
	if(evt.key === "Enter" && evt.keyCode === 13) {
		createCategory();
	}
});

function createCategory() {
	const requestOptions = {
		method: "POST",
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json',
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

				return;
			}

			return response.json();
		})
		.then(json => {
			const inputErrors = document.querySelectorAll(".invalid-feedback");
			inputErrors.forEach(inputError => inputError.innerText = "");

			const fieldErrors = json.subErrors;
			for (const fieldError of fieldErrors) {
				const fieldName = fieldError.field;
				const message = fieldError.message;

				const errorDiv = document.querySelector(`.${fieldName}-error`);
				errorDiv.innerText = message;

				errorDiv.previousElementSibling.classList.add("is-invalid");
			}
		});
}
