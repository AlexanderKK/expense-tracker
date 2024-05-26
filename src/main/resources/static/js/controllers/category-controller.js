const categoryAction = document.querySelector(".add-category__actions button");
const categoryName = document.querySelector(".add-category #categoryName");
const categoryIcon = document.querySelector(".add-category #iconSelect");

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
		headers: jsonHeaders,
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
	document.getElementById("iconPicked").innerHTML = "Selected Icon:";
}
