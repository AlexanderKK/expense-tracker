const incomeTotal = document.querySelector(".income__total");
const balanceTotal = document.querySelector(".balance__total");
const expensesTotal = document.querySelector(".expenses__total");
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

			incomeTotal.innerText = json.income + "$";
			balanceTotal.innerText = json.balance + "$";
			expensesTotal.innerText = json.expenses + "$";
		});
}
