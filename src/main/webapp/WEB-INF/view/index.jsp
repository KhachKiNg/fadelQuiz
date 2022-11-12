<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix= "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <link href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
    
    <!-- Font Awesome CSS -->
	<link href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet">
	
	<style>
		body{
			background-color: #C0C0C0;
		}
		
		i:hover {
 			 cursor: pointer;
		}
		
		.cursorP:hover{
 			 cursor: pointer;
		}
		
		.formError {
		    color: #dc3545;
		    font-style: italic;
		    font-weight: 600;
		}
		
	</style>
    <title>Hello, world!</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  </head>
  <body>
  	<div class="container-fluid container px-4">
	  	<div class="row" style="margin: 25px">
	  		<h1 style="text-align: center;color: #0d6efd;">Fadel Demo</h1>
	  	</div>
  	</div>
  	
  	<div class="position-absolute top-50 start-50" id="loader">
  		<div class="spinner-border text-primary" role="status">
	  		<span class="visually-hidden">Loading...</span>
		</div>
  	</div>
  	<div class="container-fluid" id="page" style="display:none">
		<div class="container">
			<div class="row gy-5" style="color: white">
				<div class="col-lg-7">
					<div class="input-group mb-3">
						<div class="form-check form-switch" style="margin-right: 1em;">
							 <input class="form-check-input" type="checkbox" id="flexSwitchCheckDefault" onclick='changeFilter()'>
							 <label class="form-check-label" for="flexSwitchCheckDefault">Js/Java Filter</label>
						</div>
						<input id="filterByNameJS" type="text" class="form-control" placeholder="Filter By Name" aria-label="Recipient's username" aria-describedby="button-addon2">
					  	<input id="filterByNameJSLike" type="text" class="form-control" placeholder="Filter By Name (LIKE)" aria-label="Recipient's username" aria-describedby="button-addon2">
						<button class="btn btn-primary" type="button" onclick='javaFilter();' id='javaFilterBtn'>Java Filter</button>
					</div>
				</div>
				<div class="col">
					<button class="btn btn-primary" type="button" onclick="$('#advancedSearchForm').slideDown( 'slow' );">Advanced Filter</button>
				</div>
			</div>
			
			<form:form modelAttribute="searchEmployee" action="/Fadel/employee/" method="POST" style='display:none' id='advancedSearchForm'>
				<div style="text-align: center;">
					<div class="row gy-5 text-primary" style="text-align: left;">
						<div class="col">
							<h4>Advanced Search</h4>
						</div>
					</div>
					
					<div class="row gy-5" style="color:white">
						<div class="col">
							<div class="row gy-5"> 
								<div class="input-group mb-3">
								  	<span class="input-group-text">First name</span>
								  	<form:input path="name" type="text" aria-label="First name" class="form-control"/>
								  	<div class="input-group mb-3">
								  		<div class="form-check form-check-inline" style="padding: 0px">
									  		<form:radiobutton name="nameRadio" path="nameRadio" value="equal" />
										  	<label class="form-check-label" for="inlineRadio1">Equal</label>
										</div>
										<div class="form-check form-check-inline" style="padding: 0px">
										  	<form:radiobutton name="nameRadio" path="nameRadio" value="contain" />
										  	<label class="form-check-label" for="inlineRadio2">Contain</label>
										</div>
								  	</div>
								</div>
							</div>
						</div>
						<div class="col">
							<div class="row gy-5">
								<div class="input-group mb-3">
								  	<span class="input-group-text">Famly name</span>
								  	<form:input path="familyName" type="text" aria-label="Family name" class="form-control"/>
								  	<div class="input-group mb-3">
								  		<div class="form-check form-check-inline" style="padding: 0px">
									  		<form:radiobutton name="familyNameRadio" path="familyNameRadio" value="equal" />
										  	<label class="form-check-label" for="inlineRadio1">Equal</label>
										</div>
										<div class="form-check form-check-inline" style="padding: 0px">
										  	<form:radiobutton name="familyNameRadio" path="familyNameRadio" value="contain" />
										  	<label class="form-check-label" for="inlineRadio2">Contain</label>
										</div>
								  	</div>
								</div>
							</div>
						</div>
						<div class="col">
							<div class="row gy-5">
								<div class="input-group mb-3">
								  	<span class="input-group-text">Gender</span>
								  	<form:input path="gender" type="text" aria-label="gender" class="form-control" maxlength="1" data-bs-toggle="tooltip" data-bs-placement="right" title="M or F"/>
								</div>
							</div>
						</div>
					</div>
					
					<div class="row gy-5" style="color:white">
						<div class="col">
							<div class="row gy-5">
								<div class="input-group mb-3">
								  	<span class="input-group-text">Age</span>
								  	<form:input path="age" type="number" aria-label="age" class="form-control"/>
								  	<div class="input-group mb-3">
								  		<div class="form-check form-check-inline" style="padding: 0px">
										  	<form:radiobutton name="ageRadio" path="ageRadio" value="equal" />
										  	<label class="form-check-label" for="inlineRadio1">Equal</label>
										</div>
										<div class="form-check form-check-inline" style="padding: 0px">
										  	<form:radiobutton name="ageRadio" path="ageRadio" value="greater" />
										  	<label class="form-check-label" for="inlineRadio2">Greater than</label>
										</div>
										<div class="form-check form-check-inline" style="padding: 0px">
										  	<form:radiobutton name="ageRadio" path="ageRadio" value="lower" />
										  	<label class="form-check-label" for="inlineRadio3">lower than</label>
										</div>
								  	</div>
								</div>
							</div>
						</div>
						<div class="col">
							<div class="row gy-5">
								<div class="input-group mb-3" style="margin-bottom: 0px!important">
								  	<span class="input-group-text">Start Date</span>
								  	<form:input path="startDate" type="date" aria-label="startDate" class="form-control"/>								  	
								</div>
								<div class="input-group" style="margin:0px;display:none" id="secondDateDiv">
									<span class="input-group-text">Secodn Date</span>
									<form:input path="secondDate" type="date" aria-label="startDate" class="form-control"/>
								</div>
								<div class="input-group" style="margin:0px;">
							  		<div class="form-check form-check-inline" style="padding: 0px" >
										<form:radiobutton name="startDateRadio" path="startDateRadio" value="greater" onclick="$('#secondDateDiv').hide()"/>
									  	<label class="form-check-label" for="inlineRadio1">Greater</label>
									</div>
									<div class="form-check form-check-inline" style="padding: 0px" >
										<form:radiobutton name="startDateRadio" path="startDateRadio" value="lower" onclick="$('#secondDateDiv').hide()"/>
									  	<label class="form-check-label" for="inlineRadio2">lower</label>
									</div>
									<div class="form-check form-check-inline" style="padding: 0px" >
										<form:radiobutton name="startDateRadio" path="startDateRadio" value="equal" onclick="$('#secondDateDiv').hide()"/>
									  	<label class="form-check-label" for="inlineRadio2">equal</label>
									</div>
									<div class="form-check form-check-inline" style="padding: 0px">
										<form:radiobutton name="startDateRadio" path="startDateRadio" value="between" onclick="$('#secondDateDiv').show()"/>
									  	<label class="form-check-label" for="inlineRadio2">Between</label>
									</div>
							  	</div>
							</div>
						</div>
						<div class="col">
							<div class="row gy-5">
								<div class="input-group mb-3">
								  	<span class="input-group-text">Salary</span>
								  	<form:input path="salary" type="number" aria-label="salary" class="form-control"/>
								  	<div class="input-group mb-3">
								  		<div class="form-check form-check-inline" style="padding: 0px">
									  		<form:radiobutton name="salaryRadio" path="salaryRadio" value="equal" />
										  	<label class="form-check-label" for="inlineRadio1">Equal</label>
										</div>
										<div class="form-check form-check-inline" style="padding: 0px">
									  		<form:radiobutton name="salaryRadio" path="salaryRadio" value="greater" />
										  	<label class="form-check-label" for="inlineRadio2">Greater than</label>
										</div>
										<div class="form-check form-check-inline" style="padding: 0px">
									  		<form:radiobutton name="salaryRadio" path="salaryRadio" value="lower" />
										  	<label class="form-check-label" for="inlineRadio3">lower than</label>
										</div>
								  	</div>
								</div>
							</div>
						</div>
					</div>
					
					<div class="row gy-5 mb-3">
						<div class="col-lg-10">
						</div>
						<div class="col-lg-2">
							<button type="submit" name="search" value="search" class="btn btn-primary">Submit</button>
							<button type="button" class="btn btn-danger" onclick="$('#advancedSearchForm').slideUp( 'slow' );">Cancel</button>
						</div>
					</div>
				
				</div>
			</form:form>
			
			<div class="row gy-5" style="color: white">
				<div class="col">
					<p style="text-align: center;">Employee Id</p>
				</div>
				<div class="col">
					<p style="text-align: center;">Name</p>
				</div>
				<div class="col">
					<p style="text-align: center;">Family Name</p>
				</div>
				<div class="col">
					<p style="text-align: center;">Gender</p>
				</div>
				<div class="col">
					<p style="text-align: center;">Age</p>
				</div>
				<div class="col">
					<p style="text-align: center;">Start Date</p>
				</div>
				<div class="col">
					<p style="text-align: center;">Salary</p>
				</div>
				<div class="col">
					<p style="text-align: center;">Action</p>
				</div>
			</div>
			
			<c:forEach items="${list}" var="obj">
					<div class="row text-primary rowToFilter">
						<div class="col">
							<p style="text-align: center;">${obj.id}</p>
						</div>
						<div class="col">
							<p style="text-align: center;">${obj.name}</p>
						</div>
						<div class="col">
							<p style="text-align: center;">${obj.familyName}</p>
						</div>
						<div class="col">
							<p style="text-align: center;">${obj.gender}</p>
						</div>
						<div class="col">
							<p style="text-align: center;">${obj.age}</p>
						</div>
						<div class="col">
							<p style="text-align: center;">
								<fmt:formatDate value="${obj.startDate}" pattern="d-MMM-yyyy"/> 
							</p>
						</div>
						<div class="col">
							<p style="text-align: center;">${obj.salary}</p>
						</div>
						<div class="col">
							<p class="cursorP" style="text-align: center;" onclick="deleteEmploye('${obj.id}')"><i class="icon-remove"></i> Delete</p>
						</div>
					</div>
			</c:forEach>
			<div class="row text-primary">
				<div class="col">
				</div>
				<div class="col">
				</div>
				<div class="col">
				</div>
				<div class="col">
				</div>
				<div class="col">
				</div>
				<div class="col">
				</div>
				<div class="col">
				</div>
				<div class="col">
						<p class="cursorP" style="text-align: center;" onclick="addEmp()"> <i class="icon-plus"></i> Add new Employee</p>
					</div>
			</div>
		</div>
		<div class="container" style="display: none" id="addEmpContainer">
			<div class="row gy-5 text-primary">
				<h3 style="text-align: center;">Add new Employee</h3>
				<div class="row gy-5">
					<div class="col">
					</div>
					<div class="col">
						<div class="row gy-5 border">
							<form:form modelAttribute="employee" action="/Fadel/employee/" method="POST" cssClass="formError">
								<div class="row gy-5">
									<div class="input-group mb-3">
									  	<span class="input-group-text">Id</span>
									  	<form:input path="id" type="text" aria-label="First name" class="form-control" />
									</div>
								</div>
								<div class="row gy-5">
									<div class="input-group mb-3">
									  	<span class="input-group-text">First name</span>
									  	<form:input path="name" type="text" aria-label="First name" class="form-control"/>
									</div>
								</div>
								<div class="row">
									<p style="margin-left: 1em;"><form:errors path="name" class="errorexist"></form:errors></p>
								</div>
								<div class="row gy-5">
									<div class="input-group mb-3">
									  	<span class="input-group-text">Family name</span>
									  	<form:input path="familyName" type="text" aria-label="Family name" class="form-control"/>
									</div>
								</div>
								<div class="row">
									<p style="margin-left: 1em;"><form:errors path="familyName" class="errorexist"></form:errors></p>
								</div>
								<div class="row gy-5">
									<div class="input-group mb-3">
									  	<span class="input-group-text">Gender</span>
									  	<form:input path="gender" type="text" aria-label="gender" class="form-control" maxlength="1" data-bs-toggle="tooltip" data-bs-placement="right" title="M or F"/>
									</div>
								</div>
								<div class="row">
									<p style="margin-left: 1em;"><form:errors path="gender" class="errorexist"></form:errors></p>
								</div>
								<div class="row gy-5">
									<div class="input-group mb-3">
									  	<span class="input-group-text">Age</span>
									  	<form:input path="age" type="number" aria-label="age" class="form-control"/>
									</div>
								</div>
								<div class="row">
									<p style="margin-left: 1em;"><form:errors path="age" class="errorexist"></form:errors></p>
								</div>
								<div class="row gy-5">
									<div class="input-group mb-3">
									  	<span class="input-group-text">Start Date</span>
									  	<form:input path="startDate" type="date" aria-label="startDate" class="form-control"/>
									</div>
								</div>
								<div class="row">
									<p style="margin-left: 1em;"><form:errors path="startDate" class="errorexist"></form:errors></p>
								</div>
								<div class="row gy-5">
									<div class="input-group mb-3">
									  	<span class="input-group-text">Salary</span>
									  	<form:input path="salary" type="number" aria-label="salary" class="form-control"/>
									</div>
								</div>
								<div class="row">
									<p style="margin-left: 1em;"><form:errors path="salary" class="errorexist"></form:errors></p>
								</div>
								<div class="row gy-5 mb-3">
									<div class="col-lg-7">
										<button type="submit" name="delete" value="delete" class="btn btn-primary" style="display: none" id="deleteButtonId">delete</button>
									</div>
									<div class="col-lg-5">
										<button type="submit" name="submit" value="submit" class="btn btn-primary">Submit</button>
										<button type="button" class="btn btn-danger" onclick="removeAddEmpForm()">Cancel</button>
									</div>
								</div>
							</form:form>
						</div>
					</div>
					<div class="col">
					</div>
				</div>
			</div>
		</div>
  	</div>
  	
  	<div class="modal" tabindex="-1" id="myModal">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title">Modal title</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
	        <p>Are you sure you want to DELETE this object ?</p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-danger" onclick="$('#deleteButtonId').click();">Delete</button>
	      </div>
	    </div>
	  </div>
	</div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>

	<script>	
	
		var jsJavaFilter = 'JS';
		
		function changeFilter(){
			
			$('#filterByNameJS').val('');
			$('#filterByNameJSLike').val('');
			$('.rowToFilter').each(function(){
					$(this).show();
			});
			if(jsJavaFilter == 'JS'){
				jsJavaFilter = 'java';
				$('#javaFilterBtn').show();
			}else{
				jsJavaFilter = 'JS';
				$('#javaFilterBtn').hide();
			}
		}

		function deleteEmploye(id){
			$('#id').val(id);
			var myModal = new bootstrap.Modal(document.getElementById('myModal'), {
				  keyboard: false
				})
			var modalToggle = document.getElementById('toggleMyModal') // relatedTarget
			myModal.show(modalToggle)
		}
		
		$( document ).ready(function() {
			
			$('#filterByNameJSLike').keyup(function(){
				$('#filterByNameJS').val('');
				var filter = $('#filterByNameJSLike').val();
				if(jsJavaFilter === 'JS'){
					if(filter == ''){
						$('.rowToFilter').each(function(){
								$(this).show();
						});
					}else{
						$('.rowToFilter').each(function(){
							if(!$(this).children().eq(1).children().eq(0).html().includes(filter)){
								$(this).hide();
							}else{
								$(this).show();
							}
						});
					}
				}
				
			});
			
			$('#filterByNameJS').keyup(function(){
				$('#filterByNameJSLike').val('');
				console.log('filter');
				var filter = $('#filterByNameJS').val();
				
				if(jsJavaFilter === 'JS'){
					if(filter == ''){
						$('.rowToFilter').each(function(){
								$(this).show();
						});
					}else{
						$('.rowToFilter').each(function(){
							console.log('sub filter  '+filter);
							if($(this).children().eq(1).children().eq(0).html() !== filter){
								$(this).hide();
							}else{
								$(this).show();
							}
						});
					}
				}
				
			});
			
		    console.log( "ready!" );
		    
		    if($('.errorexist')[0]){
		    	$('#addEmpContainer').show();
		    }else{
		    	$('#addEmpContainer').hide();
		    }
		    
		    $('#loader').hide();
		    $('#javaFilterBtn').hide();
		    $('#page').show();
		    var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
		    var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
		      return new bootstrap.Tooltip(tooltipTriggerEl)
		    })
		});
		
		function addEmp(){
			$('#addEmpContainer').slideDown( "slow" );
		}
		function removeAddEmpForm(){
			$('#addEmpContainer').slideUp( "slow" );
		}
		
		function javaFilter(){
			if($('#filterByNameJS').val() == ''){
				if($('#filterByNameJSLike').val() !== ''){
					window.location.href = '/Fadel/employee/search/' + $('#filterByNameJSLike').val() + '/like';
				}else{
					alert("Can't search for nothing ! ");
				}
			}else{
				window.location.href = '/Fadel/employee/search/' + $('#filterByNameJS').val();
			}
		}
		
	</script>
  </body>
</html>