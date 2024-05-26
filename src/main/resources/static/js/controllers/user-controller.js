const registerEmail = document.querySelector("#register-email");
const registerPassword = document.querySelector("#register-password");
const registerPasswordConfirm = document.querySelector("#register-password-confirm");
const registerAction = document.querySelector(".register__actions button");
const passwordBtn = document.querySelector("#password-icon-container");
let isRegisterPasswordValid = false;

const loginAction = document.querySelector(".login__actions button");
const loginEmail = document.querySelector("#login-email");
const loginPassword = document.querySelector("#login-password");

/**
 * Register
 */
registerAction.addEventListener("click", registerUser);

registerEmail.addEventListener("keyup", function(evt) {
	if(isEmailValid(this) && evt.key === "Enter" && evt.keyCode === 13) {
		registerUser();
	}
});

registerPassword.addEventListener("keyup", isPasswordValid);

[registerPassword, registerPasswordConfirm].forEach(element => {
	element.addEventListener("blur", arePasswordsEqual);
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

function isEmailValid(emailInput) {
	const emailError = emailInput.nextElementSibling;
	const emailRegex = /^[\w-.]+@([\w-]+\.)+[\w-]{2,4}$/;

	if(!emailRegex.test(emailInput.value)) {
		emailInput.classList.add("is-invalid");
		emailError.innerText = "Please enter a valid email";

		return false;
	} else {
		emailInput.classList.remove("is-invalid");
		emailError.innerText = "";

		return true;
	}
}

function isPasswordValid() {
	const registerPasswordError = registerPassword.nextElementSibling.nextElementSibling;
	const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;

	if (!passwordRegex.test(registerPassword.value)) {
		registerPassword.classList.add("is-invalid");
		registerPasswordError.innerText =
			"Please enter a password with minimum length of 8 characters.\n" +
			"At least one uppercase letter, one lowercase letter, one digit and one special character (e.g., !@#$%^&*).";
		isRegisterPasswordValid = false;

		return false;
	} else {
		registerPassword.classList.remove("is-invalid");
		registerPasswordError.innerText = "";
		isRegisterPasswordValid = true;

		return true;
	}
}

function arePasswordsEqual() {
	const registerPasswordConfirmError = registerPasswordConfirm.nextElementSibling;

	if (registerPassword.value !== registerPasswordConfirm.value) {
		registerPassword.classList.add("is-invalid");
		registerPasswordConfirm.classList.add("is-invalid");
		registerPasswordConfirmError.innerText = "Please enter matching passwords";

		return false;
	} else {
		if(isRegisterPasswordValid) {
			registerPassword.classList.remove("is-invalid");
		}

		registerPasswordConfirm.classList.remove("is-invalid");
		registerPasswordConfirmError.innerText = "";

		return true;
	}
}

function registerUser() {
	if(!isEmailValid(registerEmail) || !isPasswordValid() || !arePasswordsEqual()) {
		return;
	}

	registerEmail.value = registerEmail.value.trim();

	const requestOptions = {
		method: "POST",
		headers: jsonHeaders,
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

/**
 * Login
 */
loginAction.addEventListener("click", attemptLogin);
loginEmail.addEventListener("keyup", function(evt) {
	if(isEmailValid(loginEmail) && evt.key === "Enter" && evt.keyCode === 13) {
		attemptLogin();
	}
});

function attemptLogin() {
	if(!isEmailValid(loginEmail)) {
		return;
	}

	loginEmail.classList.remove("is-invalid");
	loginPassword.classList.remove("is-invalid");
	loginEmail.value = loginEmail.value.trim();

	const requestOptions = {
		method: "POST",
		headers: jsonHeaders,
		body: JSON.stringify({
			email: loginEmail.value,
			password: loginPassword.value
		})
	};

	const loginURL = `${location.origin}/auth/login`;

	fetch(loginURL, requestOptions)
		.then(response => {
			if(response.ok) {
				clearLoginControls();

				toastify(loginSuccessOptions);
				lowLag.play("success");
			}

			return response.json();
		})
		.then(json => {
			if(json.hasOwnProperty("accessToken")) {
				localStorage.setItem("accessToken", json.accessToken);

				return;
			}

			handleAuthenticationErrors(json);
		});
}

function clearLoginControls() {
	loginEmail.value = "";
	loginPassword.value = "";
}

const handleAuthenticationErrors = (json) => {
	if(json === undefined) {
		return;
	}

	const authError = document.querySelector(".login-error");
	authError.innerText = "";
	authError.innerText = json.message;

	loginEmail.classList.add("is-invalid");
	loginPassword.classList.add("is-invalid");
};

/**
 * Get current user
 */
getCurrentUser();

function getCurrentUser() {
	const accessToken = localStorage.getItem("accessToken");

	const requestOptions = {
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json',
			'Authorization': `Bearer ${accessToken}`
		}
	};

	const getCurrentUserURL = `${location.origin}/auth/current-user`;

	fetch(getCurrentUserURL, requestOptions)
		.then(response => response.json())
		.then(json => console.log(json));
}
