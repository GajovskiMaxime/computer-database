$(function(){
	$("#addComputerForm").validate({
		rules :{
			
			computerName: "required",
//			introduced:{
////				DateFormat: true
////				lessThan: ["Discontinued date", "#discontinued"]
//			},
//			discontinued:{
////				DateFormat: true
////				greaterThan: ["Introduced date", "#introduced"]
//			}
		},
		messages :{
			
			computerName:"Please enter a name for computer.",
//			
//			introduced:{
//				dateLessThan:"Introduced date can't be after discontinued date."
//			},
////			
//			discontinued:{
//				dateGreaterThan: "Discontinued date can't be before introduced date."
//			}
		},
		submitHandler:function(form){
			form.submit();
		}
	
	});
});
//
//jQuery.validator.addMethod("DateFormat", function(value, element) {
//	  var regEx = "^(?:(?:31(\/|-|\.)(?:0?[13578]|1[02]))\1|(?:(?:29|30)(\/|-|\.)(?:0?[1,3-9]|1[0-2])\2))(?:(?:1[6-9]|[2-9]\d)?\d{2})$|^(?:29(\/|-|\.)0?2\3(?:(?:(?:1[6-9]|[2-9]\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\d|2[0-8])(\/|-|\.)(?:(?:0?[1-9])|(?:1[0-2]))\4(?:(?:1[6-9]|[2-9]\d)?\d{2})$";
//	  if(!value)				{return true;}
//	  if(value.match(regEx))	{return true;}
//	  return false;
////	  var date;
////	  if(!((date = new Date(value))|0)){
////		  return false;
////	  }
////	  console.log(date.toISOString().slice(0,10));
////	  console.log(value);
////	  return date.toISOString().slice(0,10) == value;
//	  },"Please respect the format : yyyy-MM-dd");
