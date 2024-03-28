const expenseInput = document.querySelector(".add-expenses #expenses");
// const categorySelect = document.querySelector(".add-expenses #category");
// const addExpensesBtn = document.querySelector(".add-expenses__actions button");

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
			categoryDTO: {
				id: categorySelect.value,
				name: categorySelect.options[categorySelect.selectedIndex].innerText
			}
		})
	};

	const addTransactionURL = `${window.location.origin}/transactions/add`;

	fetch(addTransactionURL, requestOptions)
		.then(response => {
			if(response.ok) {
				expenseInput.value = "";
				categorySelect.selectIndex = 0;
				categorySelect.options[0].selected = true;
			} else {
				alert("Error during transaction creation");
			}
		});
}
