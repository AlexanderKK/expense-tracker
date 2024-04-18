const incomeTotal = document.querySelector(".income__total");
const lastIncomesDiv = document.querySelector(".income__last");

const balanceTotal = document.querySelector(".balance__total");

const expensesTotal = document.querySelector(".expenses__total");
const lastExpensesDiv = document.querySelector(".expenses__last");

const tab1 = document.getElementById("tab-1");

window.addEventListener("load", loadBalance);
tab1.addEventListener("click", loadBalance);

function loadBalance() {
	const requestOptions = {
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json',
		}
	};

	const getBalanceURL = `${window.location.origin}/balance`;

	fetch(getBalanceURL, requestOptions)
		.then(response => {
			return response.json();
		})
		.then(json => {
			if(json.balance > 0) {
				balanceTotal.style.color = "#27ba04";
			} else if(json.balance < 0) {
				balanceTotal.style.color = "#b60606";
			} else {
				balanceTotal.style.color = "inherit";
			}

			// Income
			const totalIncome = json.incomeDTO.totalIncome;
			incomeTotal.innerText = totalIncome + "$";

			const lastIncomes = json.incomeDTO.lastIncomes;
			lastIncomesDiv.innerHTML = "";

			lastIncomes.forEach(income => {
				lastIncomesDiv.innerHTML += `<p class="text-success fs-4">+${income}$</p>`;
			});

			// Balance
			balanceTotal.innerText = json.balance + "$";

			// Expenses
			const totalExpenses = json.transactionDTO.totalExpenses;
			expensesTotal.innerText = totalExpenses + "$";

			const lastExpenses = json.transactionDTO.lastExpenses;
			lastExpensesDiv.innerHTML = "";

			lastExpenses.forEach(expense => {
				lastExpensesDiv.innerHTML += `<p class="text-danger fs-4">-${expense}$</p>`;
			});
		});
}
