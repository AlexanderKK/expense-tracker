const expenseInput = document.querySelector(".add-expenses #expense");
const expenseCategory = document.querySelector(".add-expenses #category");
const expenseDate = document.querySelector(".add-expenses #transactionDate");
const expenseControls = document.querySelectorAll(".add-expenses__control");
let isCreateTransactionCancelled = false;

expenseInput.addEventListener("keyup", function(evt) {
	const expenseError = expenseInput.nextElementSibling;
	const expenseRegex = /^[1-9]\d{0,6}$/;

	if(!expenseRegex.test(expenseInput.value)) {
		expenseInput.classList.add("is-invalid");
		expenseError.innerText = "Please enter a positive expense amount.\nMaximum length is considered to be 7 digits.";

		isCreateTransactionCancelled = true;
	} else {
		expenseInput.classList.remove("is-invalid");
		expenseError.innerText = "";
		isCreateTransactionCancelled = false;

		if(evt.key === "Enter" && evt.keyCode === 13) {
			createTransaction();
		}
	}
});

expenseCategory.addEventListener("change", function(evt) {
	if(this.options[this.selectedIndex].value === "" || this.selectedIndex === 0) {
		this.classList.add("is-invalid");
		this.nextElementSibling.innerText = "Please choose a category";
	} else {
		this.classList.remove("is-invalid");
		this.nextElementSibling.innerText = "";
	}
});

/**
 * Create transaction
 */
addExpensesBtn.addEventListener("click", createTransaction);

function createTransaction() {
	if(isCreateTransactionCancelled) {
		return;
	}

	expenseInput.value = expenseInput.value.trim();

	const requestOptions = {
		method: "POST",
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json',
		},
		body: JSON.stringify({
			expense: expenseInput.value,
			category: categorySelect.value,
			date: transformDate(expenseDate.value)
		})
	};

	const addTransactionURL = `${window.location.origin}/transactions/add`;

	fetch(addTransactionURL, requestOptions)
		.then(response => {
			if(response.ok) {
				clearExpenseControls();

				toastify(transactionAddSuccessOptions);
				lowLag.play("success");

				loadBalance();

				return;
			}

			return response.json();
		})
		.then(json => {
			handleValidationErrors(json);
		});
}

function clearExpenseControls() {
	expenseInput.value = "";
	categorySelect.selectIndex = 0;
	categorySelect.options[0].selected = true;
	setCurrentDatePickerDate();

	expenseControls.forEach(expenseControl => expenseControl.classList.remove("is-invalid"));
}
