function handleValidationErrors(json) {
	if(json === undefined) {
		return;
	}

	const inputErrors = document.querySelectorAll(".invalid-feedback");
	inputErrors.forEach(inputError => inputError.innerText = "");

	const fieldErrors = json.subErrors;
	const objectName = json.message;

	for (const fieldError of fieldErrors) {
		const fieldName = fieldError.field;
		const message = fieldError.message;

		const errorDiv = document.querySelector(`.${objectName}-${fieldName}-error`);
		errorDiv.innerText = message;

		errorDiv.previousElementSibling.classList.add("is-invalid");
	}
}
