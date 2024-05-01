const dates = document.querySelectorAll(".date");
const currentYearSpan = document.querySelector("#current-year");
const currentMonthSpan = document.querySelector("#current-month");

/**
 * Load current year and current month
 */
loadCurrentMonthAndYear();

/**
 * Load current dates
 */
dates.forEach(d => {
	resetDate(d);

	d.addEventListener("focus", function(evt) {
		resetDate(d);
	});
});

/**
 * Set current datepicker dates
 */
const dateInput = $('.date-select');

setCurrentDatePickerDate();

/**
 * Listen for datepicker change event
 */
dateInput.on('changeDate', function (evt) {
	const selectedDate = evt.format();

	const formattedDate = transformDate(selectedDate);

	const validDate = new Date(formattedDate);

	if (isNaN(validDate.valueOf())) {
		return;
	}

	this.classList.remove("is-invalid");
});

function loadCurrentMonthAndYear() {
	const currentDate = new Date();

	currentYearSpan.innerText = currentDate.getFullYear();
	currentMonthSpan.innerText = currentDate.toLocaleString('default', { month: 'long' });
}

function setCurrentDatePickerDate() {
	dateInput.datepicker({
		format: 'dd.mm.yyyy'
	}).datepicker("setDate", new Date());
}

function resetDate(d) {
	const date = destructDate(new Date());
	d.style.pointerEvents = "none";
	d.value = `${date.day}.${date.month}.${date.year}`;
}

function destructDate(date) {
	const day = ("0" + date.getDate()).slice(-2);
	const month = ("0" + (date.getMonth() + 1)).slice(-2);
	const year = date.getFullYear();

	return {
		day: day,
		month: month,
		year: year
	};
}

function transformDate(dateString) {
	const dateParts = dateString.split(".");
	if(dateParts.length !== 3) {
		return null;
	}

	return `${dateParts[2]}-${dateParts[1]}-${dateParts[0]}`;
}
