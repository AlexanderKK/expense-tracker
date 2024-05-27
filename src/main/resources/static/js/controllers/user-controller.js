const tabsContainer = document.querySelector("#tabsContainer");
const tabContentContainer = document.querySelector("#tab-content");

let isRegisterPasswordValid = false;

/**
 * Register
 */
tabContentContainer.addEventListener("click", function(evt) {
	if (evt.target.closest(".register__actions button")) {
		registerUser();
	}

	if(evt.target.closest("#password-icon-container")) {
		evt.preventDefault();

		const input = document.querySelector("#show-hide-password input");
		const icon = document.querySelector("#show-hide-password i");

		if (input.getAttribute("type") === "text") {
			input.setAttribute("type", "password");
			icon.classList.add("fa-eye-slash");
			icon.classList.remove("fa-eye");
		} else if (input.getAttribute("type") === "password") {
			input.setAttribute("type", "text");
			icon.classList.add("fa-eye");
			icon.classList.remove("fa-eye-slash");
		}
	}
});

tabContentContainer.addEventListener("keyup", function(evt) {
	if (evt.target.closest("#register-email")) {
		if (isEmailValid(evt.target) && evt.key === "Enter" && evt.keyCode === 13) {
			registerUser();
		}
	}

	if (evt.target.closest("#register-password")) {
		isPasswordValid();
	}
});

tabContentContainer.addEventListener("blur", function(evt) {
	if (evt.target.closest("#register-password") || evt.target.closest("#register-password-confirm")) {
		arePasswordsEqual();
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
	const registerPassword = document.querySelector("#register-password");

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
	const registerPassword = document.querySelector("#register-password");
	const registerPasswordConfirm = document.querySelector("#register-password-confirm");

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
	const registerEmail = document.querySelector("#register-email");
	const registerPassword = document.querySelector("#register-password");
	const registerPasswordConfirm = document.querySelector("#register-password-confirm");

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
				registerEmail.value = "";
				registerPassword.value = "";
				registerPasswordConfirm.value = "";

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

/**
 * Login
 */
tabContentContainer.addEventListener("click", function(evt) {
	if (evt.target.closest(".login__actions button")) {
		attemptLogin();
	}
});

tabContentContainer.addEventListener("keyup", function(evt) {
	if (evt.target.closest("#login-email")) {
		if (isEmailValid(evt.target) && evt.key === "Enter" && evt.keyCode === 13) {
			attemptLogin();
		}
	}
});

window.addEventListener("load", async () => {
	try {
		const currentUser = await getCurrentUser();

		addLogoutTab();
	} catch(e) {
		addAuthorizationTabs();
	}
});

tabsContainer.addEventListener("click", async function(evt) {
	if(evt.target.closest("#tab-7")) {
		try {
			const logout = await logoutUser();

			removeLogoutTab();
			addAuthorizationTabs();
		} catch(e) {}
	}
});

function addLogoutTab() {
	const tab7HTML =
		`<li class="nav-item">
		<a class="nav-link" id="tab-7" data-bs-toggle="tab" href="#tabpanel-7" role="tab"
		   aria-controls="tabpanel-7" aria-selected="false"><i class="fas fa-sign-in-alt"></i>Sign Out</a>
	</li>`;

	tabsContainer.appendChild(parseHTMLStringToNode(tab7HTML));
}

function removeLogoutTab() {
	const tab7 = document.querySelector("#tab-7");

	if(tab7) {
		const tab7Wrapper = tab7.parentElement;
		tabsContainer.removeChild(tab7Wrapper);
	}
}

function addAuthorizationTabs() {
	const tab5HTML =
		`<li class="nav-item">
		<a class="nav-link" id="tab-5" data-bs-toggle="tab" href="#tabpanel-5" role="tab"
		   aria-controls="tabpanel-5" aria-selected="false"><i class="fas fa-sign-in-alt"></i>Sign In</a>
	</li>`;
	const tab6HTML =
		`<li class="nav-item">
		<a class="nav-link" id="tab-6" data-bs-toggle="tab" href="#tabpanel-6" role="tab"
		   aria-controls="tabpanel-6" aria-selected="false"><i class="fas fa-user-plus"></i>Sign Up</a>
	</li>`;

	const tabPanel5HTML =
		`<div class="tab-pane" id="tabpanel-5" role="tabpanel" aria-labelledby="tab-5">
		<div class="login m-auto">
			<div class="mb-3">
				<h4>Login</h4>
			</div>

			<div class="login__inner">
				<div class="mb-3">
					<label class="form-label" for="login-email">Email</label>

					<div class="input-group">
						<div class="input-group-prepend">
							<div class="input-group-text text-secondary" style="border-radius: 6px 0 0 6px">@</div>
						</div>

						<input value="alex@gmail.com" type="text" class="form-control login-email__control rounded-end-2" id="login-email"
							   placeholder="johndoe@gmail.com">

						<div class="invalid-feedback login-email-error"></div>
					</div>
				</div>

				<div class="mb-3">
					<label for="login-password" class="form-label">Password</label>

					<input value="Alex1234*" type="password" class="form-control login-password__control" id="login-password">

					<div class="invalid-feedback login-error"></div>
				</div>

				<div class="login__actions">
					<button class="btn btn-primary mb-3 btn-actions">Proceed</button>
				</div>
			</div>
		</div>
	</div>`;

	const tabPanel6HTML =
		`<div class="tab-pane" id="tabpanel-6" role="tabpanel" aria-labelledby="tab-6">
		<div class="register m-auto">
			<div class="mb-4">
				<h4>Register</h4>
			</div>

			<div class="register__inner">
				<div class="mb-3">
					<label class="form-label" for="register-email">Email</label>

					<div class="input-group">
						<div class="input-group-prepend">
							<div class="input-group-text text-secondary" style="border-radius: 6px 0 0 6px">@</div>
						</div>

						<input type="text" class="form-control register-email__control rounded-end-2"
							   id="register-email"
							   placeholder="johndoe@gmail.com">

						<div class="invalid-feedback register-email-error"></div>
					</div>
				</div>

				<div class="mb-3">
					<label for="register-password" class="form-label">Password</label>

					<div class="input-group mb-3" id="show-hide-password">
						<input type="password" class="form-control register-password__control" id="register-password">

						<div class="input-group-append">
							<div id="password-icon-container" class="input-group-text text-secondary" style="padding: 10px 2px 10px 10px; border-radius: 0 6px 6px 0;">
								<i class="fa fa-eye-slash"></i>
							</div>
						</div>

						<div class="invalid-feedback register-password-error"></div>
					</div>

					<div class="mb-3">
						<label for="register-password-confirm" class="form-label">Confirm Password</label>

						<input type="password" class="form-control register-password-confirm__control" id="register-password-confirm">

						<div class="invalid-feedback register-passwordConfirm-error"></div>
					</div>

					<div class="register__actions">
						<button class="btn btn-primary mb-3 btn-actions">Proceed</button>
					</div>
				</div>
			</div>
		</div>
	</div>`;

	// Add tab navigation
	tabsContainer.appendChild(parseHTMLStringToNode(tab5HTML));
	tabsContainer.appendChild(parseHTMLStringToNode(tab6HTML));

	//Add tab panels
	tabContentContainer.appendChild(parseHTMLStringToNode(tabPanel5HTML));
	tabContentContainer.appendChild(parseHTMLStringToNode(tabPanel6HTML));

	const tabPanel1 = document.querySelector("#tabpanel-1");
	tabPanel1.classList.add("active", "show");

	const tabs = Array.from(tabsContainer.childNodes)
		.filter(node => node.nodeType === Node.ELEMENT_NODE);
	tabs.forEach(tab => tab.classList.remove("active"));

	tab1.classList.add("active");
	tab1.parentElement.classList.add("active");

	navigate();
}

function removeAuthorizationTabs() {
	const tab5 = document.querySelector("#tab-5");
	const tab6 = document.querySelector("#tab-6");

	const tab5Wrapper = tab5.parentElement;
	const tab6Wrapper = tab6.parentElement;

	const tabPanel5 = document.querySelector("#tabpanel-5");
	const tabPanel6 = document.querySelector("#tabpanel-6");
	const tabPanel1 = document.querySelector("#tabpanel-1");

	// Remove tab navigation
	tabsContainer.removeChild(tab6Wrapper);
	tabsContainer.removeChild(tab5Wrapper);

	tab1.classList.add("active");
	tab1.parentElement.classList.add("active");

	navigate();

	//Remove tab panels
	tabContentContainer.removeChild(tabPanel5);
	tabContentContainer.removeChild(tabPanel6);

	tabPanel1.classList.add("active", "show");
}

function parseHTMLStringToNode(htmlString) {
	const parser = new DOMParser();
	const document = parser.parseFromString(htmlString, 'text/html');

	return document.body.firstChild;
}

function attemptLogin() {
	const loginEmail = document.querySelector("#login-email");
	const loginPassword = document.querySelector("#login-password");

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
				loginEmail.value = "";
				loginPassword.value = "";

				toastify(loginSuccessOptions);
				lowLag.play("success");
			} else {
				loginEmail.classList.add("is-invalid");
				loginPassword.classList.add("is-invalid");

				const authError = document.querySelector(".login-error");
				authError.innerText = "Wrong email or password.";
			}

			return response.json();
		})
		.then(async json => {
			if (json.hasOwnProperty("accessToken")) {
				localStorage.setItem("accessToken", json.accessToken);

				try {
					const currentUser = await getCurrentUser();

					removeAuthorizationTabs();
					addLogoutTab();
				} catch(e) {

				}

				return;
			}

			handleAuthenticationErrors(json);
		});
}

const handleAuthenticationErrors = (json) => {
	if(json === undefined) {
		return;
	}

	const loginEmail = document.querySelector("#login-email");
	const loginPassword = document.querySelector("#login-password");
	const authError = document.querySelector(".login-error");

	loginEmail.classList.add("is-invalid");
	loginPassword.classList.add("is-invalid");

	authError.innerText = "";
	authError.innerText = json.message;
};

async function getCurrentUser() {
	const accessToken = localStorage.getItem("accessToken");

	const requestOptions = {
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json',
			'Authorization': `Bearer ${accessToken}`
		}
	};

	const getCurrentUserURL = `${location.origin}/auth/current-user`;
	const response = await fetch(getCurrentUserURL, requestOptions);

	if (!response.ok) {
		const errorMessage = `Error: ${response.status} ${response.statusText}`;

		throw new Error(errorMessage);
	}

	return await response.json();
}

async function logoutUser() {
	const accessToken = localStorage.getItem("accessToken");

	const requestOptions = {
		method: "POST",
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json',
			'Authorization': `Bearer ${accessToken}`
		}
	};

	const logoutUserURL = `${location.origin}/auth/logout`;

	fetch(logoutUserURL, requestOptions)
		.then(response => response.json())
		.then(json => json);
}
