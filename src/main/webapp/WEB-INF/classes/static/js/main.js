
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
		