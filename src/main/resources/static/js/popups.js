const backgroundColorSuccess = "linear-gradient(to right, rgb(4, 151, 146), rgb(4 222 145))";

const transactionAddSuccessOptions = {
	message: "Expenses saved successfully",
	backgroundColor: backgroundColorSuccess
};

const categoryAddSuccessOptions = {
	message: "Category saved successfully",
	backgroundColor: backgroundColorSuccess
};

const incomeAddSuccessOptions = {
	message: "Income saved successfully",
	backgroundColor: backgroundColorSuccess
};

const userRegisteredSuccessOptions = {
	message: "User registered successfully",
	backgroundColor: backgroundColorSuccess
};

const loginSuccessOptions = {
	message: "You have been logged into your account",
	backgroundColor: backgroundColorSuccess
};

function toastify(toastOptions) {
	const toast = {
		text: toastOptions.message,
		duration: 1750,
		close: true,
		gravity: "top",
		position: "center",
		stopOnFocus: false,
		style: {
			background: toastOptions.backgroundColor,
			padding: "25px 25px 25px 35px",
			fontSize: "1.25rem"
		},
		onClick: function() {}
	};

	Toastify(toast).showToast();
}
