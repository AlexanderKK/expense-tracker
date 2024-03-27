const categoryAction = document.querySelector(".add-category .add-category__actions button");
const categoryName = document.querySelector(".add-category #categoryName");

if(categoryAction != null) {
	categoryAction.addEventListener("click", createCategory);
}

if(categoryName != null) {
	categoryName.addEventListener("keypress", function(evt) {
		if(evt.keyCode === "Enter") {
			createCategory();
		}
	});
}

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

	let addCategoryURL = `${window.location.origin}/categories/add`;

	fetch(addCategoryURL, requestOptions)
		.then(response => {
			if(response.ok) {
				categoryName.value = "";
			} else {
				alert("Error during post request");
			}
		});
}

