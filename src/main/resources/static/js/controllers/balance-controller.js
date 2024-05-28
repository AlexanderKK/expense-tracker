const incomeTotal = document.querySelector(".income__total");
const lastIncomesElement = document.querySelector(".income__last");
const incomeRateElement = document.querySelector(".income__rate");

const balanceTotal = document.querySelector(".balance__total");
const balanceRateElement = document.querySelector(".balance__rate");

const expensesTotal = document.querySelector(".expenses__total");
const lastExpensesElement = document.querySelector(".expenses__last");
const expenseRateElement = document.querySelector(".expenses__rate");

const tab1 = document.getElementById("tab-1");

window.addEventListener("load", loadBalance);
tab1.addEventListener("click", loadBalance);

function loadBalance() {
	const accessToken = localStorage.getItem("accessToken");

	const requestOptions = {
		headers: jsonAuthHeaders(accessToken)
	};

	const getBalanceURL = `${window.location.origin}/balance`;

	fetch(getBalanceURL, requestOptions)
		.then(response => {
			return response.json();
		})
		.then(json => {
			// Incomes
			const incomesDTO = json.incomesDTO;

			const totalIncome = incomesDTO.totalIncome;
			incomeTotal.innerText = "$ " + totalIncome;

			const lastIncomes = incomesDTO.lastIncomes;
			lastIncomesElement.innerHTML = "";

			lastIncomes.forEach(income => {
				lastIncomesElement.innerHTML += `<h4>+ $${income}</h4>`;
			});

			const incomeRate = incomesDTO.incomeRate;
			incomeRateElement.innerText = `Increased by ${incomeRate}%`;

			// Balance
			const balance = json.balance.toString();
			balanceTotal.innerText = balance < 0 ? balance.replace("-", "-$ ") : "$ " + balance;

			const balanceRate = json.balanceRate;
			balanceRateElement.innerText = `Increased by ${balanceRate}%`;

			// Expenses
			const transactionsDTO = json.transactionsDTO;

			const totalExpenses = transactionsDTO.totalExpenses;
			expensesTotal.innerText = "$ " + totalExpenses;

			const lastExpenses = transactionsDTO.lastExpenses;
			lastExpensesElement.innerHTML = "";

			lastExpenses.forEach(expense => {
				lastExpensesElement.innerHTML += `<h4>- $${expense}</h4>`;
			});

			const expenseRate = transactionsDTO.expenseRate;
			expenseRateElement.innerText = `Increased by ${expenseRate}%`;
		});
}
