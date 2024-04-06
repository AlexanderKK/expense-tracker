const dates = document.querySelectorAll(".date");

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
 * Load current datepicker dates
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

	return `${dateParts[2]}-${dateParts[1]}-${dateParts[0]}`;
}
