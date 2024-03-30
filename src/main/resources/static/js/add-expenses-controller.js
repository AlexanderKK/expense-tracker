const expenseInput = document.querySelector(".add-expenses #expense");
const expenseControls = document.querySelectorAll(".add-expenses__control");

/**
 * Create transaction
 */
addExpensesBtn.addEventListener("click", createTransaction);

function createTransaction() {
	const requestOptions = {
		method: "POST",
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json',
		},
		body: JSON.stringify({
			expense: expenseInput.value,
			category: categorySelect.value,
		})
	};

	const addTransactionURL = `${window.location.origin}/transactions/add`;

	fetch(addTransactionURL, requestOptions)
		.then(response => {
			if(response.ok) {
				clearExpenseControls();

				return;
			}

			return response.json();
		})
		.then(json => {
			if(json === undefined) {
				return;
			}

			const inputErrors = document.querySelectorAll(".invalid-feedback");
			inputErrors.forEach(inputError => inputError.innerText = "");

			const fieldErrors = json.subErrors;
			for (const fieldError of fieldErrors) {
				const fieldName = fieldError.field;
				const message = fieldError.message;

				console.log(fieldName, message);

				const errorDiv = document.querySelector(`.${fieldName}-error`);

				console.log(errorDiv);
				errorDiv.innerText = message;

				errorDiv.previousElementSibling.classList.add("is-invalid");
			}
		})
}

function clearExpenseControls() {
	expenseInput.value = "";
	categorySelect.selectIndex = 0;
	categorySelect.options[0].selected = true;
	console.log(expenseControls);

	expenseControls.forEach(expenseControl => expenseControl.classList.remove("is-invalid"));
}
