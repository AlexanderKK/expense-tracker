const addExpensesControls = document.querySelectorAll(".add-expenses__control");
const addExpensesBtn = document.querySelector(".add-expenses__actions button");

const audioControls = new Audio('/sounds/squiggle.mp3');
const audioActions = new Audio(`${window.location.origin}/sounds/splits.mp3`);

addExpensesControls.forEach((element) => {
	element.addEventListener("focus", function(evt) {
		audioControls.play();
	});
});

addExpensesBtn.addEventListener("click", function(evt) {
	audioActions.play();
});