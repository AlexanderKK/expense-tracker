const registerEmail = document.querySelector("#register-email");
const registerPassword = document.querySelector("#register-password");
const registerPasswordConfirm = document.querySelector("#register-password-confirm");
const registerAction = document.querySelector(".register .btn-actions");

let isUserRegisterEmailValid = false;
let isUserRegisterPasswordValid = false;

registerAction.addEventListener("click", registerUser);

registerEmail.addEventListener("keyup", function(evt) {
	const registerEmailError = registerEmail.nextElementSibling;
	const registerEmailRegex = /^[\w-.]+@([\w-]+\.)+[\w-]{2,4}$/;

	if(!registerEmailRegex.test(registerEmail.value)) {
		registerEmail.classList.add("is-invalid");
		registerEmailError.innerText = "Please enter a valid email";

		isUserRegisterEmailValid = false;
	} else {
		registerEmail.classList.remove("is-invalid");
		registerEmailError.innerText = "";

		isUserRegisterEmailValid = true;

		if(evt.key === "Enter" && evt.keyCode === 13) {
			checkPasswordValidity();
			registerUser();
		}
	}
});

[registerPasswordConfirm, registerPassword].forEach(element => {
	element.addEventListener("blur", function(evt) {
		checkPasswordValidity();
	});
});

function checkPasswordValidity() {
	const registerPasswordError = registerPassword.nextElementSibling;
	const registerPasswordConfirmError = registerPasswordConfirm.nextElementSibling;
	const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;

	if(registerPassword.value === "") {
		registerPassword.classList.add("is-invalid");
		registerPasswordError.innerText = "Please enter a password";

		isUserRegisterPasswordValid = false;

		return;
	}

	if (!passwordRegex.test(registerPassword.value)) {
		registerPassword.classList.add("is-invalid");

		registerPasswordError.innerText =
			"Please enter a password with minimum length of 8 characters.\n" +
			"At least one uppercase letter, one lowercase letter, one digit and one special character (e.g., !@#$%^&*).";

		isUserRegisterPasswordValid = false;

		return;
	} else {
		registerPassword.classList.remove("is-invalid");

		registerPasswordError.innerText = "";
	}

	if (registerPassword.value !== registerPasswordConfirm.value) {
		registerPassword.classList.add("is-invalid");
		registerPasswordConfirm.classList.add("is-invalid");

		registerPasswordConfirmError.innerText = "Passwords do not match";

		isUserRegisterPasswordValid = false;
	} else {
		registerPassword.classList.remove("is-invalid");
		registerPasswordConfirm.classList.remove("is-invalid");
		registerPasswordError.innerText = "";
		registerPasswordConfirmError.innerText = "";

		isUserRegisterPasswordValid = true;
	}
}

function registerUser() {
	if(!isUserRegisterEmailValid || !isUserRegisterPasswordValid) {
		return;
	}

	registerEmail.value = registerEmail.value.trim();

	const requestOptions = {
		method: "POST",
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json'
		},
		body: JSON.stringify({
			email: registerEmail.value,
			password: registerPassword.value
		})
	};

	const registerURL = `${location.origin}/users/register`;

	fetch(registerURL, requestOptions)
		.then(response => {
			if(response.ok) {
				clearRegisterControls();

				toastify(userRegisteredSuccessOptions);
				lowLag.play("success");

				return;
			}

			return response.json();
		})
		.then(json => {
			handleValidationErrors(json);
		});
}
