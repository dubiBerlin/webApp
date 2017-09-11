$(document).ready(function(){
	
    // Füge de
	$("#txtArea").val( $("#preValue").text());
	var _preValues = "";
	var _btnHasBeenClicked = false;
	var _labelsArray = [];
	
	$("button").click(function(e){
	   
		if(_btnHasBeenClicked===false){
			var btnID = e.target.id;
			
			savePrevValues();	
			parseTemplate1(btnID);
			_btnHasBeenClicked = true;
		}	
		
	});
	
	
	function parseTemplate1(btnID){
		
        var sourceTxt = $("#template").text();
		
		$("#txtArea").val( sourceTxt+" \n "+_preValues);
		
		takeTemplateOutOfTextArea1();
		createTemplate();
		
//		printLblArray();
	}
	
	function createElement( label, sectionLabel, id, required, userInput){
		
		var newElement = {};

		if(label!=="" || label!==null){
			newElement.label = label;
		}else{
			newElement.label = "";
		}
		if(sectionLabel!=="" || sectionLabel!==null){
			newElement.sectionLabel = sectionLabel;
		}else{
			newElement.sectionLabel = "";
		}
		if(id!=="" || id!==null){
			newElement.id = id;
		}
		if(required!==null){
			newElement.required = required;
		}
		if(userInput!=="" || userInput!==null){
			newElement.userInput = userInput;
		}else{
			newElement.userInput = "";
		}
		return newElement;
	}
	
	
	function takeTemplateOutOfTextArea1(){
		
		var txtAreaVal =  $("#txtArea").val();

		// Regex: Finde alle ===Bla bla bla===
		var regex = /===.*===/;
		
		var comments = "";
		
//		var rows = txtAreaVal.split(regex);
		
		var rows = txtAreaVal.split("\n");
		
		var i = 0;
		
		
		while(i < rows.length){
			
			if(rows[i].match(regex) && rows[i]!=="===General Information===" && rows[i].trim()!=="===Comments==="){
				

				console.log("==== "+rows[i]+" ====");
				var sectionLabel = rows[i].replace(/=/g,"");
				console.log(""+sectionLabel);
				
				_labelsArray.push(createElement("",sectionLabel, 0, false, ""));
				
				var u = i+1;
				
				// we walk through the labels of the === === section
				while(!rows[u].match(regex) && u < rows.length-1){
					
					console.log(u+" "+rows[u]);
					
					var lbl = rows[u].replace(/\.|:/g,"");
					
					var required = "";
					
					if(rows[u].includes("*")){
						required = true;
					}else{
						required = false;
					}
					
					_labelsArray.push(createElement(lbl,"", u, required, ""));
					
					u++;
				}
				
				i = u;
				
			}else{
				if(rows[i].trim()==="===Comments==="){
					
					console.log(rows[i]);
					
					comments = comments +"\n"+rows[i];
					
					var u = i+1;
					
					while( u < rows.length-1){
						comments = comments+"\n"+rows[u];
						u++;
					}
					break;
					
				}else{
					i++;
				}	
			}
		}
	}
	
		
	function createTemplate(){
		
		var overlay = $("#modalContent");
		
		
		var table = "";
		var i = 0;
		while(i < _labelsArray.length-1){
//			alert("i "+i);
			if(_labelsArray[i].sectionLabel!==""){
				table = table+"<div class='row' >" +
				                "<div class='col-md-4'><h4>"+_labelsArray[i].sectionLabel+"</h4></div>"+	
				                "<div class='col-md-8'></div>"+
				              "</div>";
			}else{
				if(_labelsArray[i].label!=="" ){
					table = table+"<div class='row' >" +
				                    "<div class='col-md-4'>"+_labelsArray[i].label+"</div>";	
				                    if(_labelsArray[i].required===true){
				                    	table = table+"<div class='col-md-8'><input type='text' id='"+_labelsArray[i].id+"' style='width: 70%;' required>"+"</div>";
				                    }else{
				                    	table = table+"<div class='col-md-8'><input type='text' id='"+_labelsArray[i].id+"' style='width: 70%;'>"+"</div>";
				                    }
				                    
				                    table = table+"</div>";
				}
			}
			i++;
			  
		}
		
		overlay.html(table);
		overlay.show();
	}
	
	
	
	
	function savePrevValues(){
		_preValues = $("#txtArea").val();
	}
	
	
	
	function getPrevValues(){
		return _preValues;
	}
	
	function printLblArray(){
		
		console.log("\nprintLBLARRAY")

		for(var i = 0; i < _labelsArray.length; i++){
			if(_labelsArray[i].label!==""){
				console.log(i+"_: "+_labelsArray[i].label);
			}else{
				console.log(i+"_: ======"+_labelsArray[i].sectionLabel+"======");
			}
			
		}
	}
	
	$("#saveTmplBtn").click(function(){
		saveTemplateContent();
	} );
	
	
	function saveTemplateContent(){
		
		for(var i = 0; i < _labelsArray.length-1; i++){
			
			var requiredFieldNotFilled = false;
			
			var id = _labelsArray[i].id;
			
			console.log("id: "+id);
			
			if(id > 0){
				var txt = $("#"+id).val();
				alert("text: "+txt)
			}
		}
		
	}
	

	function takeTemplateOutOfTextArea2(){
		
		var txtAreaVal =  $("#txtArea").val();
		
		var rows = txtAreaVal.split("\n");
		
		/*
		 *  ^  Ausdruck muss am Anfang stehen
		 *  d eine einzelne Dezimalzahl
		 *  * eine beliebig lange Reihenfolge von Dezimalzahlen
		 *  - Das Minuszeichen muss hinten dran sein
		 *  s gefolt von einem Leerzeichen
		 *  s gefolt von einem Leerzeichen
		 */
		var regex = /^\d*-\s\s/g;
		
		for(var i = 0; i < rows.length; i++){
			if(rows[i].match(regex)){
				
			    var searched = regex.exec(rows[i]);
				var label = rows[i].replace(searched, "");
				
				var newElement = {};
				newElement.id = "win_"+i;
				newElement.label = label;
				
				_labelsArray.push(newElement);
			}
		}		
	}
});