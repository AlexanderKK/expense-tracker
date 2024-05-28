const expenseInput = document.querySelector(".add-expenses #expense");
const expenseCategory = document.querySelector(".add-expenses #category");
const expenseDescription = document.querySelector(".add-expenses #expenseDescription");
const expenseDate = document.querySelector(".add-expenses #transactionDate");
const expenseControls = document.querySelectorAll(".add-expenses__control");
const expensesTableBody = document.querySelector(".expenses-table-content");
const addExpensesBtn = document.querySelector(".add-expenses__actions button");
const tab3 = document.getElementById("tab-3");

let isCreateTransactionCancelled = false;

expenseInput.addEventListener("keyup", function(evt) {
	const expenseError = expenseInput.nextElementSibling;
	const expenseRegex = /^(?!0\.00|0\.0$)(?!0\d)(?:[1-9]\d{0,6}(?:\.\d{0,2})?|\d\.\d{1,2})$/;

	if(!expenseRegex.test(expenseInput.value)) {
		expenseInput.classList.add("is-invalid");
		expenseError.innerText = "Please enter a valid positive expense amount (i.e. 15.99)\nMaximum length is considered to be 7 digits";

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

window.addEventListener("load", loadTransactions);
tab3.addEventListener("click", loadTransactions);

function loadTransactions() {
	const accessToken = localStorage.getItem("accessToken");

	const requestOptions = {
		headers: jsonAuthHeaders(accessToken)
	};

	const getTransactionsURL = `${location.origin}/transactions`;

	fetch(getTransactionsURL, requestOptions)
		.then(response => response.json())
		.then(transactions => {
			expensesTableBody.innerHTML = "";

			transactions.forEach((transaction, index) => {
				const tableRow =
					`<tr>
						<td>${index + 1}</td>
						<td>${transaction.category}</td>
						<td>${transaction.expense}</td>
						<td>${transaction.description}</td>
						<td>${transaction.date}</td>
<!--						<td>May 15, 2015</td>-->
						<td>
							<button class="badge badge-gradient-success">Edit <i
									class="mdi mdi-pencil mdi-32px"></i></button>
							<button class="badge badge-gradient-danger ms-4">Delete <i
									class="mdi mdi-delete-empty mdi-32px"></i></button>
						</td>
					</tr>`;

				expensesTableBody.innerHTML += tableRow;
			});
		})
		.catch(error => console.log(error));
}

function createTransaction() {
	if(isCreateTransactionCancelled) {
		return;
	}

	expenseInput.value = expenseInput.value.trim();
	expenseDescription.value = expenseDescription.value.trim();

	const accessToken = localStorage.getItem("accessToken");

	const requestOptions = {
		method: "POST",
		headers: jsonAuthHeaders(accessToken),
		body: JSON.stringify({
			expense: expenseInput.value,
			category: categorySelect.value,
			description: expenseDescription.value,
			date: transformDate(expenseDate.value)
		})
	};

	const addTransactionURL = `${location.origin}/transactions/add`;

	fetch(addTransactionURL, requestOptions)
		.then(response => {
			if(response.ok) {
				clearExpenseControls();

				toastify(transactionAddSuccessOptions);
				lowLag.play("success");

				loadBalance();
				loadTransactions();

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
	expenseDescription.value = "";
	setCurrentDatePickerDate();

	expenseControls.forEach(expenseControl => expenseControl.classList.remove("is-invalid"));
}
