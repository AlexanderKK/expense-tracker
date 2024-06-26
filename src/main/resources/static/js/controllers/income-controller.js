const incomeAmount = document.querySelector(".add-income #incomeAmount");
const incomeSource = document.querySelector(".add-income #incomeSource");
const incomeDate = document.querySelector(".add-income #incomeDate");
const addIncomeBtn = document.querySelector(".add-income__actions button");
const incomeControls = document.querySelectorAll(".add-income__control");
let isCreateIncomeCancelled = false;

incomeAmount.addEventListener("keyup", function(evt) {
	const incomeError = incomeAmount.nextElementSibling;
	const incomeRegex = /^(?!0\.00|0\.0$)(?!0\d)(?:[1-9]\d{0,6}(?:\.\d{0,2})?|\d\.\d{1,2})$/;

	if(!incomeRegex.test(incomeAmount.value)) {
		incomeAmount.classList.add("is-invalid");
		incomeError.innerText = "Please enter a positive income amount.\nMaximum length is considered to be 7 digits.";

		isCreateIncomeCancelled = true;
	} else {
		incomeAmount.classList.remove("is-invalid");
		incomeError.innerText = "";
		isCreateIncomeCancelled = false;

		if(evt.key === "Enter" && evt.keyCode === 13) {
			createIncome();
		}
	}
});

addIncomeBtn.addEventListener("click", createIncome);

function createIncome () {
	if(isCreateIncomeCancelled) {
		return;
	}

	incomeAmount.value = incomeAmount.value.trim();

	const accessToken = localStorage.getItem("accessToken");

	const requestOptions = {
		method: "POST",
		headers: jsonAuthHeaders(accessToken),
		body: JSON.stringify({
			amount: incomeAmount.value,
			source: incomeSource.value,
			date: transformDate(incomeDate.value)
		})
	};

	const addIncomeURL = `${window.location.origin}/incomes/add`;

	fetch(addIncomeURL, requestOptions)
		.then(response => {
			if(response.ok) {
				clearIncomeControls();

				toastify(incomeAddSuccessOptions);
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

function clearIncomeControls() {
	incomeAmount.value = "";
	incomeSource.value = "";
	setCurrentDatePickerDate();

	incomeControls.forEach(incomeControl => incomeControl.classList.remove("is-invalid"));
}
