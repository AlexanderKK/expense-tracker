const registerEmail = document.querySelector("#register-email");
const registerPassword = document.querySelector("#register-password");
const registerPasswordConfirm = document.querySelector("#register-password-confirm");
const registerAction = document.querySelector(".register .btn-actions");
const passwordBtn = document.querySelector("#password-icon-container");

let isUserRegisterEmailValid = false;
let isUserRegisterPasswordValid = false;
let arePasswordsEqual = false;

registerAction.addEventListener("click", registerUser);

registerEmail.addEventListener("keyup", function(evt) {
	if(isEmailValid() && evt.key === "Enter" && evt.keyCode === 13) {
		registerUser();
	}
});

registerPassword.addEventListener("keyup", checkPasswordValidity);

[registerPassword, registerPasswordConfirm].forEach(element => {
	element.addEventListener("blur", checkMatchingPasswords);
});

passwordBtn.addEventListener("click", function(evt) {
	evt.preventDefault();

	const input = document.querySelector("#show-hide-password input");
	const icon = document.querySelector("#show-hide-password i");

	if(input.getAttribute("type") === "text") {
		input.setAttribute("type", "password");
		icon.classList.add("fa-eye-slash");
		icon.classList.remove("fa-eye");
	} else if(input.getAttribute("type") === "password") {
		input.setAttribute("type", "text");
		icon.classList.add("fa-eye");
		icon.classList.remove("fa-eye-slash");
	}
});

function isEmailValid() {
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
	}

	return isUserRegisterEmailValid;
}

function checkPasswordValidity() {
	const registerPasswordError = registerPassword.nextElementSibling.nextElementSibling;
	const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;

	if (!passwordRegex.test(registerPassword.value)) {
		registerPassword.classList.add("is-invalid");
		registerPasswordError.innerText =
			"Please enter a password with minimum length of 8 characters.\n" +
			"At least one uppercase letter, one lowercase letter, one digit and one special character (e.g., !@#$%^&*).";

		isUserRegisterPasswordValid = false;
	} else {
		registerPassword.classList.remove("is-invalid");
		registerPasswordError.innerText = "";

		isUserRegisterPasswordValid = true;
	}
}

function checkMatchingPasswords() {
	const registerPasswordConfirmError = registerPasswordConfirm.nextElementSibling;

	if (registerPassword.value !== registerPasswordConfirm.value) {
		registerPassword.classList.add("is-invalid");
		registerPasswordConfirm.classList.add("is-invalid");
		registerPasswordConfirmError.innerText = "Please enter matching passwords";

		arePasswordsEqual = false;
	} else {
		if(isUserRegisterPasswordValid) {
			registerPassword.classList.remove("is-invalid");
		}

		registerPasswordConfirm.classList.remove("is-invalid");
		registerPasswordConfirmError.innerText = "";

		arePasswordsEqual = true;
	}
}

function registerUser() {
	isEmailValid();
	checkPasswordValidity();
	checkMatchingPasswords();

	if(!isUserRegisterEmailValid || !isUserRegisterPasswordValid || !arePasswordsEqual) {
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
			password: registerPassword.value,
			passwordConfirm: registerPasswordConfirm.value
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

function clearRegisterControls() {
	registerEmail.value = "";
	registerPassword.value = "";
	registerPasswordConfirm.value = "";
}
