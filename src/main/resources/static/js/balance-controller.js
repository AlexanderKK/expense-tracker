const incomeTotal = document.querySelector(".income__total");
const lastIncomesDiv = document.querySelector(".income__last");
const incomeRateDiv = document.querySelector(".income__rate");

const balanceTotal = document.querySelector(".balance__total");

const expensesTotal = document.querySelector(".expenses__total");
const lastExpensesDiv = document.querySelector(".expenses__last");
const expenseRateDiv = document.querySelector(".expenses__rate");

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

			// Incomes
			const incomesDTO = json.incomesDTO;

			const totalIncome = incomesDTO.totalIncome;
			incomeTotal.innerText = totalIncome + "$";

			const lastIncomes = incomesDTO.lastIncomes;
			lastIncomesDiv.innerHTML = "";

			lastIncomes.forEach(income => {
				lastIncomesDiv.innerHTML += `<p class="text-success fs-4">+${income}$</p>`;
			});

			const incomeRate = incomesDTO.incomeRate;
			incomeRateDiv.innerText = incomeRate + "% increase rate";

			// Balance
			balanceTotal.innerText = json.balance + "$";

			// Expenses
			const transactionsDTO = json.transactionsDTO;

			const totalExpenses = transactionsDTO.totalExpenses;
			expensesTotal.innerText = totalExpenses + "$";

			const lastExpenses = transactionsDTO.lastExpenses;
			lastExpensesDiv.innerHTML = "";

			lastExpenses.forEach(expense => {
				lastExpensesDiv.innerHTML += `<p class="text-danger fs-4">-${expense}$</p>`;
			});

			const expenseRate = transactionsDTO.expenseRate;
			expenseRateDiv.innerText = expenseRate + "% increase rate";
		});
}
