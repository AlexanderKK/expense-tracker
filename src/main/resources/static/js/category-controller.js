const categoryAction = document.querySelector(".add-category .add-category__actions button");
const categoryName = document.querySelector(".add-category #categoryName");
const categoryIcon = document.querySelector(".add-category #iconSelect");
const monthlyExpensesTable = document.querySelector("#monthly-table");

let isCreateCategoryCancelled = false;

window.addEventListener("load", loadCategoriesExpenses);
tab1.addEventListener("click", loadCategoriesExpenses);

function loadCategoriesExpenses() {
	const requestOptions = {
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json'
		}
	};

	const categoriesExpensesURL = `${window.location.origin}/categories/expenses`;

	fetch(categoriesExpensesURL, requestOptions)
		.then(response => response.json())
		.then(json => {
			const tableHead = monthlyExpensesTable.firstElementChild.firstElementChild;
			const tableBody = monthlyExpensesTable.children[1];
			tableHead.innerHTML = "";
			tableBody.innerHTML = "";

			json.forEach(categoryDTO => {
				const categoryName = categoryDTO.categoryName;
				const totalExpenses = categoryDTO.totalExpenses;

				tableHead.innerHTML += `<th scope="col">${categoryName}</th>`;
				tableBody.innerHTML +=
					`<tr>
						<th scope="row">${categoryName}</th>
						<td>${totalExpenses}</td>
					</tr>`
			});

			monthlyExpensesLoad();
		});
}

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
			name: categoryName.value,
			icon: categoryIcon.placeholder
		})
	};

	const addCategoryURL = `${window.location.origin}/categories/add`;

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
