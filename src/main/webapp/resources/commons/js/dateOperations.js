function setDatePickerStartWithClass(className,startDateValue){
	setDatePickerWithClass(className,startDateValue,null);
}

function setDatePickerStartWithId(idName,startDateValue){
	debugger;
	setDatePickerWithId(idName,startDateValue,null);
}

function setDatePickerEndWithClass(className,endDateValue){
	setDatePickerWithClass(className,null,endDateValue);
}

function setDatePickerEndWithId(idName,endDateValue){
	setDatePickerWithId(idName,null,endDateValue);
}

function setDateTimePickerStartWithClass(className,startDateValue){
	setDateTimePickerWithClass(className,startDateValue,null);
}

function setDateTimePickerStartWithId(idName,startDateValue){
	setDateTimePickerWithId(idName,startDateValue,null);
}

function setDateTimePickerEndWithClass(className,endDateValue){
	setDateTimePickerWithClass(className,null,endDateValue);
}

function setDateTimePickerStartEndWithId(idName,startDateValue,endDateValue){
	setDateTimePickerWithId(idName,startDateValue,endDateValue);
}

function setDateTimePickerStartEndWithClass(className,startDateValue,endDateValue){
	setDateTimePickerWithClass(className,startDateValue,endDateValue);
}

function setDateTimePickerStartWithId(idName,startDateValue){
	setDateTimePickerWithId(idName,startDateValue,null);
}

function setDatePickerWithClass(className,startDateValue,endDateValue){
	endDateValue=endDateValue==null?Infinity:toDate(endDateValue);
	startDateValue=startDateValue==null?-Infinity:toDate(startDateValue);
	$('.'+className).datepicker('remove');
	$('.'+className).datepicker({
		weekStart: 1,
		autoclose: true,
		startView: 2,
		forceParse: false,
		format: 'dd-mm-yyyy',
		orientation: "auto",
		pickerPosition: "left-right",
		startDate: startDateValue,
		endDate:endDateValue
	});
}

function setDatePickerWithId(idName,startDateValue,endDateValue){
	debugger;
	endDateValue=endDateValue==null?Infinity:toDate(endDateValue);
	startDateValue=startDateValue==null?-Infinity:toDate(startDateValue);
	$('#'+idName).datepicker('remove');
	$('#'+idName).datepicker({
		/*weekStart: 1,
		autoclose: true,
		startView: 2,
		forceParse: false,
		format: 'dd-mm-yyyy',
		orientation: "auto",
		pickerPosition: "left-right",*/
		startDate: startDateValue
		/*endDate: endDateValue*/
    });
}

function toDate(dateString){
	const [day, month, year] =  dateString.split("-");
	return new Date(year, month-1, day);
}

function toDateTime(dateString){
	const [day, month, year,time,second] =  dateString.split(/[ -.:?,|\[\]\r\n/\\]+/);
	/*return new Date(year, month-1, day, time, second);*/
	
	return month+"-"+day+"-"+year+" "+time+":"+second;
}

function setDateTimePickerWithClass(className,startDateValue,endDateValue){
	endDateValue=endDateValue==null?Infinity:toDateTime(endDateValue);
	startDateValue=startDateValue==null?-Infinity:toDateTime(startDateValue);
	$('.'+className).datetimepicker("remove");
	$('.'+className).datetimepicker({
    	weekStart: 1,
		autoclose: 1,
		startView: 2,
		forceParse: 0,
		format: 'dd-mm-yyyy hh:ii',
		showMeridian: 0, 
		orientation: 'auto',
		pickerPosition: "top-left",
		endDate: endDateValue,
		startDate: startDateValue,
		pick12HourFormat: false,
        widgetPositioning:{
            horizontal: 'auto',
            vertical: 'bottom'
        }
    });
}

function setDateTimePickerWithId(idName,startDateValue,endDateValue){
	debugger;
	endDateValue=endDateValue==null?Infinity:toDateTime(endDateValue);
	startDateValue=startDateValue==null?-Infinity:toDateTime(startDateValue);
	$('#'+idName).datetimepicker("remove");
	$('#'+idName).datetimepicker({
    	weekStart: 1,
		autoclose: 1,
		startView: 2,
		forceParse: 0,
		format: 'dd-mm-yyyy hh:ii',
		showMeridian: 0, 
		orientation: 'auto',
		pickerPosition: "top-left",
		endDate: new Date(endDateValue),
		startDate: new Date(startDateValue),
		pick12HourFormat: false,
        widgetPositioning:{
            horizontal: 'auto',
            vertical: 'bottom'
        }
    });
}

/* compares date1 with date2. If date1 is eqals date2 returns 0, 
 * else if date1 is before date2 returns -1, 
 * else if date1 is after date2 returns 1 */
function dateComparator(date1,date2){
	debugger;
	var d1=toDate(date1);
	var d2= toDate(date2);
	var longD1=Number(d1.getTime());
	var longD2=Number(d2.getTime());
	
	if(longD1==longD2){
		return 0;
	}else if(longD1>longD2){
		return 1;
	}else if(longD1<longD2){
		return -1;
	}
}

function dateComparatorTime(date1,date2){
	debugger;
	var d1=toDateTime(date1);
	var d2= toDateTime(date2);
	
	
	if(d1==d2){
		return 0;
	}else if(d1>d2){
		return 1;
	}else if(d1<d2){
		return -1;
	}
}