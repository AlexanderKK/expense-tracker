window.addEventListener("load", loadData);
tab1.addEventListener("click", loadData);

function loadData() {
	loadYearlyIncomeExpenses();
	loadCategoriesExpenses();
}

function loadYearlyIncomeExpenses() {
	const requestOptions = {
		headers: jsonHeaders
	};

	const yearlyIncomeExpensesURL = `${window.location.origin}/home/yearly-income-expenses`;

	fetch(yearlyIncomeExpensesURL, requestOptions)
		.then(response => response.json())
		.then(json => {
			const yearlyIncome = json.yearlyIncome;
			const yearlyExpenses = json.yearlyExpenses;

			loadYearlyStats(yearlyIncome, yearlyExpenses);
		})
		.catch(error => console.log(error));
}

function loadCategoriesExpenses() {
	const requestOptions = {
		headers: jsonHeaders
	};

	const categoriesExpensesURL = `${window.location.origin}/home/expenses-by-categories`;

	fetch(categoriesExpensesURL, requestOptions)
		.then(response => response.json())
		.then(categoryExpensesDTO => {
			loadMonthlyExpenses(categoryExpensesDTO);
		})
		.catch(error => console.log(error));
}
