const monthlyTableFinished = document.getElementById("monthly-expenses-diagram");
const yearlyTableFinished = document.getElementById("yearly-expenses-diagram");

const yearlyStats = {
	income: [],
	expenses: []
};

let chart1;
let chart2;

function loadYearlyStats(incomeArray, expensesArray) {
	if (incomeArray === null && expensesArray === null) {
		return;
	}

	if (chart1 !== undefined) {
		chart1.destroy();
	}

	yearlyStats.income.length = 0;
	yearlyStats.expenses.length = 0;

	if (incomeArray !== null) {
		incomeArray.forEach(income => {
			yearlyStats.income.push(income);
		});
	}

	if (expensesArray !== null) {
		expensesArray.forEach(expense => {
			yearlyStats.expenses.push(expense);
		});
	}

	const ctxB = yearlyTableFinished.getContext('2d');

	const expenseGradient = ctxB.createLinearGradient(0, 0, 0, 170);
	expenseGradient.addColorStop(0.5, "red");
	expenseGradient.addColorStop(1, "orange");

	const incomeGradient = ctxB.createLinearGradient(0, 0, 0, 170);
	incomeGradient.addColorStop(0.5, "limegreen");
	incomeGradient.addColorStop(1, "green");

	chart1 = new Chart(ctxB, {
		type: 'bar',

		data: {
			labels: ['JAN', 'FEB', 'MAR', 'APR', 'MAY', 'JUN', 'JUL', 'AUG', 'SEP', 'OCT', 'NOV', 'DEC'],
			datasets: [
				{
					label: "Income",
					data: yearlyStats.income,
					backgroundColor: incomeGradient
				},
				{
					label: "Expenses",
					data: yearlyStats.expenses,
					backgroundColor: expenseGradient
				}
			]
		},
		options: {
			aspectRatio: 2
		}
	});
}

const monthlyData = {
	labels: [], population: []
};

function loadMonthlyExpenses(categoryExpensesArray) {
	if (chart2 !== undefined) {
		chart2.destroy();
	}

	monthlyData.labels.length = 0;
	monthlyData.population.length = 0;

	categoryExpensesArray.forEach(categoryExpense => {
		const categoryName = categoryExpense.categoryName;
		const totalExpenses = categoryExpense.totalExpenses;

		monthlyData.labels.push(categoryName);
		monthlyData.population.push(totalExpenses);
	});

	const ctxP = monthlyTableFinished.getContext('2d');

	chart2 = new Chart(ctxP, {
		type: 'doughnut',
		data: {
			labels: monthlyData.labels,
			datasets: [{
				data: monthlyData.population,
				backgroundColor: ["#64B5F6", "#FFD54F", "#2196F3", "#FFC107", "#1976D2", "#FFA000", "#0D47A1"],
				hoverBackgroundColor: ["#B2EBF2", "#FFCCBC", "#4DD0E1", "#FF8A65", "#00BCD4", "#FF5722", "#0097A7"]
			}]
		},
		options: {
			aspectRatio: 1.5,
			legend: {
				display: true,
				position: "right"
			}
		}
	});
}
