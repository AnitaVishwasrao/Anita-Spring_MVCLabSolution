<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Debate Event: Registration Form</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous" />
</head>
<body>
	<div class="container">
		<div class="row">
			<h2 class="col-10">Registration Form</h2>
			<h5 class="col-2 text-right">
				<a href="/DebateRegistrationWebApplication/registration/">Home</a>
			</h5>
		</div>
		<hr>
		<form action="/DebateRegistrationWebApplication/registration/save"
			method="POST">
			<div class="form-row">
				<input type="hidden" name="studentId" id="studentId"
					value="${student.studentId}">
				<div class="form-group col-md-4">
					<label for="name">Name</label> <input type="text"
						class="form-control" name="name" id="name" placeholder="John Doe"
						value="${student.name}" required>
				</div>
				<div class="form-group col-md-4 mt-2">
					<label for="department">Department</label> <input type="text"
						class="form-control" name="department" id="department"
						placeholder="MSc. Computer Science" value="${student.department}"
						required>
				</div>
				<div class="form-group col-md-4 mt-2">
					<input type="hidden" name="hdn-country" id="hdn-country"
						value="${student.country}"> <label for="country">Country</label>
					<select class="form-select" id="country" name="country"
						aria-label="Select Country" required>
						<option value="" selected>Select Country</option>
						<option value="India">India</option>
						<option value="USA">USA</option>
						<option value="Canada">Canada</option>
						<option value="Singapore">Singapore</option>
						<option value="Australia">Australia</option>
					</select>
				</div>
			</div>
			<input type="submit" class="btn btn-primary mt-2" value="Save" /> <input
				type="reset" class="btn btn-secondary mt-2" value="Clear" /> <a
				href="/DebateRegistrationWebApplication/registration/registrations"
				class="btn btn-warning mt-2">Registration List</a>
		</form>
	</div>

	<script type="text/javascript">
		window.onload = function() {
			let hiddenCountry = document.getElementById("hdn-country");
			let selectedCountry = (hiddenCountry.value != undefined
					&& hiddenCountry.value != null && hiddenCountry.value != '') ? hiddenCountry.value
					: "";
			document.getElementById("country").value = selectedCountry;
		}
	</script>
</body>
</html>