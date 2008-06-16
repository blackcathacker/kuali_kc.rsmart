
function selectAllGGForms(document) {
    var j = 0;
	for (var i = 0; i < document.KualiForm.elements.length; i++) {
	  var e = document.KualiForm.elements[i];
	  if(e.type == 'checkbox') {
	  	if (e.name == 'document.s2sOpportunity.s2sOppForms[' + j + '].selectToPrint') {
 		    if(e.disabled == false){
 		    	e.checked = true;
 		    }
	  		j++; 
	  	}
	  }
	}
}

function selectAllIncludedGGForms(document) {
    var j = 0;
	for (var i = 0; i < document.KualiForm.elements.length; i++) {
	  var e = document.KualiForm.elements[i];
	  if (e.name == 'document.s2sOpportunity.s2sOppForms[' + j + '].mandatory') {
	  		var e1 = e;
	  }
	  	if (e.name == 'document.s2sOpportunity.s2sOppForms[' + j + '].include') {
	  		var e2 = e;
	  }
	  if(e.type == 'checkbox') {	  	
	  	if (e.name == 'document.s2sOpportunity.s2sOppForms[' + j + '].selectToPrint') {
 		    if(e.disabled == false){
 		    	if(e1.value == 'Yes'){
 		    		e.checked = true;
 		    	}
 		    	if(e2.checked == true){
 		    		e.checked = true;
 		    	}
 		    }
	  		j++; 
	  	}
	  }
	}
}

function unselectAllGGForms(document) {
    var j = 0;
	for (var i = 0; i < document.KualiForm.elements.length; i++) {
	  var e = document.KualiForm.elements[i];
	  if(e.type == 'checkbox') {
	  	if (e.name == 'document.s2sOpportunity.s2sOppForms[' + j + '].selectToPrint') {
 		    if(e.disabled == false){
 		    	e.checked = false;
 		    }
	  		j++; 
	  	}
	  }
	}
}

function selectAllKeywords(document) {
    var j = 0;
	for (var i = 0; i < document.KualiForm.elements.length; i++) {
	  var e = document.KualiForm.elements[i];
	  if(e.type == 'checkbox') {
	  	var name = 'document.propScienceKeyword[' + j + '].selectKeyword';
	  	if (e.name == name) {
 		    e.checked = true;
	  		j++; 
	  	}
	  }
	}
}

function textAreaPop(textAreaName,htmlFormAction,textAreaLabel,docFormKey, sessionDocument){
  var documentWebScope
  if (sessionDocument == "true") {
      documentWebScope="session"
  }
  url=window.location.href
  pathname=window.location.pathname
  idx1=url.indexOf(pathname);
  idx2=url.indexOf("/",idx1+1);
  extractUrl=url.substr(0,idx2)
  //text=text.replace(/\n/g,'<br>');
  //window.open(extractUrl+"/updateTextArea.do?" + text+"&textAreaFieldName="+textAreaName+"&htmlFormAction="+htmlFormAction+"&textAreaFieldLabel="+textAreaLabel+"&docFormKey="+docFormKey+"&documentWebScope="+documentWebScope, "_blank", "width=640, height=600, scrollbars=yes");
  window.open(extractUrl+"/updateTextArea.do?&textAreaFieldName="+textAreaName+"&htmlFormAction="+htmlFormAction+"&textAreaFieldLabel="+textAreaLabel+"&docFormKey="+docFormKey+"&documentWebScope="+documentWebScope, "_blank", "width=640, height=600, scrollbars=yes");
}

var textAreaFieldName
function setTextArea() {
  passData=document.location.search.substring(1);
  var idx=passData.indexOf("&textAreaFieldName=")
  var idx2=passData.indexOf("&htmlFormAction=")
  textAreaFieldName=passData.substring(idx+19,idx2)
  text = window.opener.document.getElementById(textAreaFieldName).value;
  //text=passData.substr(0,idx)
  //text=unescape(text).replace(/<br>/g,"\n")
  document.getElementById(textAreaFieldName).value = text;
  
//  alert (escape(text))
//  alert (unescape(text))

}

function postValueToParentWindow() {
  opener.document.getElementById(textAreaFieldName).value = document.getElementById(textAreaFieldName).value;
  self.close();
}


// dwr functions
// this is a sample function for sponsor code
function loadSponsorCode( sponsorCodeFieldName) {
	var sponsorCode = DWRUtil.getValue( sponsorCodeFieldName );

	//if (sponsorCode == "") {
	//	clearRecipients( sponsorCodeFieldName, "" );
	//} else {
		var dwrReply = {
			callback:function(data) {
			if ( data != null ) {
				if ( sponsorCodeFieldName != null && sponsorCodeFieldName != "" ) {
					setRecipientValue( sponsorCodeFieldName, data );
				}
			} else {
				if ( sponsorCodeFieldName != null && sponsorCodeFieldName != "" ) {
					setRecipientValue( sponsorCodeFieldName, "" );
				}
			} },
			errorHandler:function( errorMessage ) {
				window.status = errorMessage;
				if ( sponsorCodeFieldName != null && sponsorCodeFieldName != "" ) {
					setRecipientValue( sponsorCodeFieldName, "" );
				}
			}
		};

		SponsorService.getSponsorCode(sponsorCode,dwrReply);

	//}
}

/*
 * Load the Sponsor Name field based on the Sponsor Code passed in.
 */
function loadSponsorName(sponsorCodeFieldName, sponsorNameFieldName ) {
	var sponsorCode = DWRUtil.getValue( sponsorCodeFieldName );

	if (sponsorCode=='') {
		clearRecipients( sponsorNameFieldName, "" );
	} else {
		var dwrReply = {
			callback:function(data) {
				if ( data != null ) {
					if ( sponsorNameFieldName != null && sponsorNameFieldName != "" ) {
						setRecipientValue( sponsorNameFieldName, data );
					}
				} else {
					if ( sponsorNameFieldName != null && sponsorNameFieldName != "" ) {
						setRecipientValue(  sponsorNameFieldName, wrapError( "not found" ), true );
					}
				}
			},
			errorHandler:function( errorMessage ) {
				window.status = errorMessage;
				setRecipientValue( sponsorNameFieldName, wrapError( "not found" ), true );
			}
		};
		SponsorService.getSponsorName(sponsorCode,dwrReply);
	}
}

/*
 * Load the Budget Category Code based on Object Code(Cost Element)
 */ 
function loadBudgetCategoryCode(objectCode,budgetCategoryCode){
	var objectCodeValue = DWRUtil.getValue( objectCode );

	if (objectCodeValue=='') {
		clearRecipients( budgetCategoryCode, "" );
	} else {
		var dwrReply = {
			callback:function(data) {
				if ( data != null ) {
					if ( objectCode != null && objectCode != "" ) {
						setRecipientValue( budgetCategoryCode, data );
					}
				} else {
					if ( objectCode != null && objectCode != "" ) {
						setRecipientValue(  budgetCategoryCode, wrapError( "not found" ), true );
					}
				}
			},
			errorHandler:function( errorMessage ) {
				window.status = errorMessage;
				setRecipientValue( budgetCategoryCode, wrapError( "not found" ), true );
			}
		};
		ObjectCodeToBudgetCategoryCodeService.getBudgetCategoryCodeForCostElment(objectCodeValue,dwrReply);
	}
}
 
/*
 * Load the Unit Name field based on the Unit Number passed in.
 */
function loadUnitName(unitNumberFieldName) {
	var unitNumber = DWRUtil.getValue( unitNumberFieldName );
    var elPrefix = findElPrefix( unitNumberFieldName );
	var unitNameFieldName = elPrefix + ".unitName";
	if (unitNumber=='') {
		clearRecipients( unitNameFieldName, "(select)" );
	} else {
		var dwrReply = {
			callback:function(data) {
				if ( data != null ) {
					if ( unitNameFieldName != null && unitNameFieldName != "" ) {
						setRecipientValue( unitNameFieldName, data );
					}
				} else {
					if ( unitNameFieldName != null && unitNameFieldName != "" ) {
						setRecipientValue(  unitNameFieldName, wrapError( "not found" ), true );
					}
				}
			},
			errorHandler:function( errorMessage ) {
				window.status = errorMessage;
				setRecipientValue( unitNameFieldName, wrapError( "not found" ), true );
			}
		};
		UnitService.getUnitName(unitNumber,dwrReply);
	}
}


function loadSponsorCode_1( sponsorCodeFieldName) {
    // alternative, delete later
	var sponsorCode = DWRUtil.getValue( sponsorCodeFieldName );
	//alert(sponsorCodeFieldName+" "+sponsorCode)
	//SponsorService.getSponsorCode(sponsorCode,function(data) {
    //DWRUtil.setValue(sponsorCodeFieldName, data);});
	SponsorService.getSponsorCode(sponsorCode,loadinfo);

}

function loadinfo(data) {
  //alert("loadinfo "+data)
  DWRUtil.setValue("document.sponsorCode", data);
}
var propAttRightWindow;
function proposalAttachmentRightsPop(lineNumber,docFormKey, sessionDocument){
  var documentWebScope
  if (sessionDocument == "true") {
      documentWebScope="session"
  }

  if (propAttRightWindow && propAttRightWindow.open && !propAttRightWindow.closed){
  	propAttRightWindow.focus();
  }else{
    propAttRightWindow = window.open(extractUrlBase()+"/proposalDevelopmentAbstractsAttachments.do?methodToCall=getProposalAttachmentRights&line="+lineNumber+"&docFormKey="+docFormKey+"&documentWebScope="+documentWebScope, "mywindow", "width=800, height=300, scrollbars=yes");
  }
}  

var propInstAttRightWindow;
function proposalInstituteAttachmentRightsPop(lineNumber,docFormKey, sessionDocument){
  var documentWebScope
  if (sessionDocument == "true") {
      documentWebScope="session"
  }
  if (propInstAttRightWindow && propInstAttRightWindow.open && !propInstAttRightWindow.closed){
  	propInstAttRightWindow.focus();
  }else{
    propInstAttRightWindow = window.open(extractUrlBase()+"/proposalDevelopmentAbstractsAttachments.do?methodToCall=getInstituteAttachmentRights&line="+lineNumber+"&docFormKey="+docFormKey+"&documentWebScope="+documentWebScope, "instAttWindow", "width=800, height=300, scrollbars=yes");
    if (window.focus) {
         propInstAttRightWindow.focus()
    }
  }
}
var fileBrowseWindow;
function openNewFileBrowseWindow(filePropertyName,fileFieldLabel,htmlFormAction,methodToCall,methodToSave,lineNumber){
  if (fileBrowseWindow && fileBrowseWindow.open && !fileBrowseWindow.closed){
  	fileBrowseWindow.focus();
  }else{
    fileBrowseWindow = window.open(extractUrlBase()+"/proposalDevelopmentAbstractsAttachments.do?methodToCall="+methodToCall+"&methodToSave="+methodToSave+"&line="+lineNumber+"&filePropertyName="+filePropertyName+"&fileFieldLabel="+fileFieldLabel, "mywindow", "width=800, height=300, scrollbars=yes");
  }
}
function extractUrlBase(){
  url=window.location.href;
  pathname=window.location.pathname;
  idx1=url.indexOf(pathname);
  idx2=url.indexOf("/",idx1+1);
  extractUrl=url.substr(0,idx2);
  return extractUrl; 
}
function openNewWindow(action,methodToCall,lineNumber,docFormKey, sessionDocument){
  var documentWebScope
  if (sessionDocument == "true") {
      documentWebScope="session"
  }
//function openNewWindow(action,methodToCall,lineNumber){
//  window.open(extractUrlBase()+"/"+action+".do?methodToCall="+methodToCall+"&line="+lineNumber);
  window.open(extractUrlBase()+"/"+action+".do?methodToCall="+methodToCall+"&line="+lineNumber+"&docFormKey="+docFormKey+"&documentWebScope="+documentWebScope);
}


function showHide(showId,hideId){
  var style_sheet = getStyleObject(showId);
  if (style_sheet)
  {
	changeObjectVisibility(showId, "block");
	changeObjectVisibility(hideId, "none");
  }
  else 
  {
    alert("sorry, this only works in browsers that do Dynamic HTML");
  }
}
function getStyleObject(objectId) {
  // checkW3C DOM, then MSIE 4, then NN 4.
  //
  if(document.getElementById && document.getElementById(objectId)) {
	return document.getElementById(objectId).style;
   }
   else if (document.all && document.all(objectId)) {  
	return document.all(objectId).style;
   } 
   else if (document.layers && document.layers[objectId]) { 
	return document.layers[objectId];
   } else {
	return false;
   }
}


function changeObjectVisibility(objectId, newVisibility) {
    // first get the object's stylesheet
    var styleObject = getStyleObject(objectId);

    // then if we find a stylesheet, set its visibility
    // as requested
    //
    if (styleObject) {
		styleObject.display = newVisibility;
	return true;
    } else {
	return false;
    }
}

/**
 * Display the Proposal's set of Roles and their Rights.
 * The roles are Aggregator, Budget Creator, etc.
 */
var propRoleRightsWindow = null;

function proposalRoleRightsPop(docFormKey, sessionDocument) {

	var documentWebScope = "";
	if (sessionDocument == true) {
		documentWebScope = "session";
	}

	if (propRoleRightsWindow != null) {
	    propRoleRightsWindow.close();
	} 

    propRoleRightsWindow = window.open(extractUrlBase() +
    	                               "/proposalDevelopmentPermissions.do?methodToCall=getPermissionsRoleRights" +
    	                               "&docFormKey=" + docFormKey + 
    	                               "&documentWebScope=" + documentWebScope, 
    	                               "permissionsRoleRights", 
    	                               "width=800, height=750, scrollbars=yes, resizable=yes");
    
}

/**
 * Display the Edit Roles popup window.  This window allows users
 * to change the roles for a user within a proposal.
 */
var propEditRolesWindow;

function editRolesPop(lineNumber, docFormKey, sessionDocument) {

    var documentWebScope = "";
    if (sessionDocument == "true") {
        documentWebScope="session"
    }
    
	if (propEditRolesWindow != null) {
	    propEditRolesWindow.close();
	} 

    propEditRolesWindow = window.open(extractUrlBase() +
    	                               "/proposalDevelopmentPermissions.do?methodToCall=editRoles" +
    	                               "&line=" + lineNumber +
    	                               "&docFormKey=" + docFormKey + 
    	                               "&documentWebScope=" + documentWebScope, 
    	                               "permissionsEditRoles", 
    	                               "width=800, height=350, scrollbars=yes, resizable=yes");
}

/**
 * Utility function for trimming a string.
 */
String.prototype.trim = function() {
  return this.replace(/^\s+|\s+$/g, "");
}

/**
 * Role Label Constants (same as labels in RoleConstants.java)
 */
var AGGREGATOR = "Aggregator";
var BUDGET_CREATOR = "Budget Creator";
var NARRATIVE_WRITER = "Narrative Writer";
var VIEWER = "Viewer";
var UNASSIGNED = "unassigned";

/**
 * The User Class.  A user has a name, its line number
 * within the user table, and a set of roles.
 */
function User(name, lineNumber) {
    this._name = name;
    this._lineNumber = lineNumber;
    this._roles = new Array();
}

User.prototype._name;
User.prototype._lineNumber;
User.prototype._roles;

User.prototype.getName = function() {
    return this._name;
}

User.prototype.getLineNumber = function() {
    return this._lineNumber;
}

User.prototype.getRoles = function() {
	return this._roles;
}

User.prototype.addRole = function(role) {
    this._roles[this._roles.length] = role;
}

User.prototype.clearRoles = function() {
    this._roles.length = 0;
}

User.prototype.hasRole = function(role) {
    for (var i = 0; i < this._roles.length; i++) {
        if (role == this._roles[i]) {
            return true;
        }
    }
    return false;
}

/**
 * When the Edit Roles popup window is closed, this function is invoked in
 * order to update the parent window and to close the popup window.  We need
 * to change the roles for the user that was modified.  We also need to 
 * update the listing of assigned roles.
 */
function updateEditRoles(lineNumber, aggregator, budgetCreator, narrativeWriter, viewer, unassigned) {

	var users = getUsers();
	updateUserRoles(users[lineNumber], aggregator, budgetCreator, narrativeWriter, viewer, unassigned)
    displayUserRoles(users[lineNumber]);
    displayAssignedRoles(users, "Aggregators", AGGREGATOR);
    displayAssignedRoles(users, "BudgetCreators", BUDGET_CREATOR);
    displayAssignedRoles(users, "NarrativeWriters", NARRATIVE_WRITER);
    displayAssignedRoles(users, "Viewers", VIEWER);
    
    self.close();
}

/**
 * Display the roles for a user.  This visibly changes the roles
 * for a user in the User Permissions panel.
 */
function displayUserRoles(user) {
	var html = "";
    var roles = user.getRoles();
    for (var i = 0; i < roles.length; i++) {
        if (i != 0) html += "<BR>";
        html += "<NOBR>" + roles[i] + "</NOBR>";
    }
     
    var roleElement = opener.document.getElementById("role" + user.getLineNumber());
    roleElement.innerHTML = html;
}

/**
 * Displays the names of users for a specific role.
 */
function displayAssignedRoles(users, elementId, role) {
    var usernames = new Array();
	for (var i = 0; i < users.length; i++) {
	    if (users[i].hasRole(role)) {
	        usernames[usernames.length] = users[i].getName();
	    }
	}
	var node = opener.document.getElementById(elementId);
	node.innerHTML = usernames.join("; ");
}

/**
 * Changes the roles for a user.  The current set of roles is cleared and
 * a new set of roles is added.
 */
function updateUserRoles(user, aggregator, budgetCreator, narrativeWriter, viewer, unassigned) {
    user.clearRoles();
    if (aggregator == true) {
        user.addRole(AGGREGATOR);
    }
    
    if (budgetCreator == true) {
        user.addRole(BUDGET_CREATOR);
    }
    
    if (narrativeWriter == true) {
        user.addRole(NARRATIVE_WRITER);
    }
    
    if (viewer == true) {
        user.addRole(VIEWER);
    }
    
    if (unassigned == true) {
        user.addRole(UNASSIGNED);
    }
 }
    
/**
 * Get the users in the Permission's Users panel.  Extract the information
 * from the HTML table.  We will store each user's name, line number in
 * the table, and the user's set of roles.
 */
function getUsers() {
    var users = new Array();
    var tableElement = opener.document.getElementById("user-roles");
    var numRows = tableElement.tBodies[0].rows.length;
    for (var i = 2; i < numRows; i++) {
    	var rowElement = tableElement.tBodies[0].rows[i];
    	var nameCell = rowElement.cells[2];
    	var name = nameCell.childNodes[0].innerHTML;
    	var user = new User(name, i - 2);
    	
    	var roleCell = rowElement.cells[5];
    	var numRoles = roleCell.childNodes.length;
    	for (var j = 0; j < numRoles; j++) {
    		var node = roleCell.childNodes[j];
    		if (node.nodeName.toUpperCase() == "NOBR") {
    		    var roleName = node.innerHTML.trim();
    		    user.addRole(roleName);
    		}
    	}
    	users[users.length] = user;
    }
    return users;
}

/*
 * Load the person's full name based on the person's username.
 */
function loadPersonName(usernameFieldName, fullnameElementId) {
    if (document.getElementById(fullnameElementId) != null) {
		var username = DWRUtil.getValue( usernameFieldName );
		var fullNameElement = document.getElementById(fullnameElementId);
		if (username == '') {
			fullNameElement.innerHTML = "";
		} else {
			var dwrReply = {
				callback:function(data) {
					if ( data != null ) {
					    fullNameElement.innerHTML = data;
					} else {
						fullNameElement.innerHTML = wrapError( "not found" );
					}
				},
				errorHandler:function( errorMessage ) {
					window.status = errorMessage;
					fullNameElement.innerHTML = wrapError( "not found" );
				}
			};
			PersonService.getPersonFullname(username, dwrReply);
		}
	}
}


/*
 * functions for custom attribute maintenance 
 */	
  
  var lookupReturnName ;
 function updateLookupReturn( lookupClassField, callbackFunction ) {
    //alert ("enter update"+lookupClassField.name);
	
	if (lookupClassField.name == "lookupClass" ) {
	    lookupReturnName = "lookupReturn" ;
	} else {
	    lookupReturnName =  findElPrefix( lookupClassField.name ) + ".lookupReturn" ;
	}
    //alert ("in update" +lookupClassField+"-"+lookupClassField.name+"-"+lookupReturn+lookupClassField.value);
	//var lookupClass = getElementValue( lookupClassField.name ); // ie7 get nothing out of this
    var lookupClass = lookupClassField.value;
    //alert ('update ' +lookupClass);
	if ( lookupClass != "") {
		var dwrReply = {
			callback:callbackFunction,
			errorHandler:function( errorMessage ) { 
				window.status = errorMessage;
			}
		};
		CustomAttributeService.getLookupReturnsForAjaxCall( lookupClass, dwrReply );
	} else {
	    kualiElements[lookupReturnName].options.length=1;
	}
}

function updateLookupReturn_Callback( data ) {
            //alert ("enter callback" +lookupReturnName);
			
			kualiElements[lookupReturnName].options.length=0; //reset 
			var option_array=data.split(",");
			var optionNum=0;
			var nameLabelPair;
			while (optionNum < option_array.length)
			 {
				  if (optionNum == 0) {
				        //alert(optionNum+option_array[0])
				        kualiElements[lookupReturnName].options[0]=new Option("select:","", true, true);
				  } else {
				        //alert("else"+optionNum+option_array[optionNum])
				        nameLabelPair = option_array[optionNum].split(";");
				        kualiElements[lookupReturnName].options[optionNum]=new Option(nameLabelPair[1], nameLabelPair[0]);
				  }
				  optionNum+=1;
			 }

}

function enableBudgetStatus(document, index) {
	var newFinalIndicator;
	var newFinalStatus;
	var newFinalStatusHidden;
	var j = 0;
	var cancelled = false;
	for (var i = 0; i < document.KualiForm.elements.length; i++) {
	  var e = document.KualiForm.elements[i];
	  if(e.type == 'checkbox') {
	  	var status = document.KualiForm.elements[i - 1];
	  	var statusHidden = document.KualiForm.elements[i - 2];
	  	if (e.checked && j != index) {
	  		if (confirm("You are changing the final version.  Are you sure?")) {
	  			e.checked = false;
	  			statusHidden.value = status.value;
	  			statusHidden.disabled = false;
	  			status.disabled = true;
	  		} else {
	  			cancelled = true;	
	  		}
	  	} else if (e.checked && j == index) {
	  		newFinalIndicator = e;
	  		newFinalStatus = status;
	  		newFinalStatusHidden = statusHidden;
	  	} else {
	  		statusHidden.value = status.value;
	  		statusHidden.disabled = false;
	  		status.disabled = true;
	  	}
	  	j++;
	  }
	}
	if (!cancelled && newFinalStatus != null) {
		newFinalStatus.disabled = false;
		newFinalStatusHidden.disabled = true;
	}
	if (cancelled && newFinalIndicator != null) {
		newFinalIndicator.checked = false;
	}
}

function setupBudgetStatuses(document) {
	for (var i = 0; i < document.KualiForm.elements.length; i++) {
	  var e = document.KualiForm.elements[i];
	  if(e.type == 'checkbox') {
	  	var status = document.KualiForm.elements[i - 1];
	  	var statusHidden = document.KualiForm.elements[i - 2];
	  	if (e.checked) {
	  		statusHidden.disabled = true;
	  		status.disabled = false;
	  	} else {
	  		statusHidden.disabled = false;
	  		status.disabled = true;
	  	}
	  }
	}
}

function toggleFinalCheckboxes(document) {
	var completed = false;
	var toggledElement;
	for (var i = 0; i < document.KualiForm.elements.length; i++) {
	  var e = document.KualiForm.elements[i];
	  if(e.type == 'select-one' && e.value == '1') {
	  	completed = true;
	  	toggledElement = e;
	  }
	}
	if (completed) {
		for (var i = 0; i < document.KualiForm.elements.length; i++) {
			var el = document.KualiForm.elements[i];
			if (el.type == 'checkbox') {
				var elStatus = document.KualiForm.elements[i - 1];
				if (elStatus != toggledElement) {
					el.disabled = true;
				} else {
					var elHidden = document.KualiForm.elements[i + 2];
					elHidden.value = true;
					elHidden.disabled = false;
					el.disabled = true;
				}
			}
		}
	} else {
		for (var i = 0; i < document.KualiForm.elements.length; i++) {
			var el = document.KualiForm.elements[i];
			var elHidden = document.KualiForm.elements[i + 2];
			if (el.type == 'checkbox') {
				elHidden.disabled = true;
				el.disabled = false;
			}
		}
	}
	
}

function setupVersionsPage(document) {
	var completed = false;
	var toggledElement;
	for (var i = 0; i < document.KualiForm.elements.length; i++) {
	  var e = document.KualiForm.elements[i];
	  if(e.type == 'select-one' && e.value == '1') {
	  	completed = true;
	  	toggledElement = e;
	  }
	}
	if (completed) {
		for (var i = 0; i < document.KualiForm.elements.length; i++) {
			var el = document.KualiForm.elements[i];
			if (el.type == 'checkbox') {
				var elStatus = document.KualiForm.elements[i - 1];
				if (elStatus != toggledElement) {
					el.disabled = true;
					elStatus.disabled = true;
					elStatusHidden.disabled = false;
				} else {
					var elHidden = document.KualiForm.elements[i + 2];
					elHidden.value = true;
					elHidden.disabled = false;
					el.disabled = true;
				}
			}
		}
	} else {
		for (var i = 0; i < document.KualiForm.elements.length; i++) {
			var el = document.KualiForm.elements[i];
			var elHidden = document.KualiForm.elements[i + 2];
			if (el.type == 'checkbox') {
				elHidden.disabled = true;
				el.disabled = false;
				var elStatus = document.KualiForm.elements[i - 1];
				elStatus.disabled
			}
		}
	}
}

function confirmFinalizeVersion(document, numVersions) {
	for (var i = 0; i < document.KualiForm.elements.length; i++) {
		var e = document.KualiForm.elements[i];
		if (e.name == 'document.finalVersionFlag') {
			if (e.checked == true) {
				for (var j = 0; j < numVersions; j++) {
	  				var finalVersionFlag = document.getElementById('finalVersionFlag' + j);
	  				if (finalVersionFlag != null && finalVersionFlag.value == 'Yes') {
	  					if (confirm("You are changing the final version.  Are you sure?")) {
	  						var updateFinalVersion = document.getElementById('updateFinalVersion');
	  						updateFinalVersion.value = 'Yes';
	  					} else {
	  						e.checked = false;
	  					}
	  				}
				}
			} else {
				var updateFinalVersion = document.getElementById('updateFinalVersion');
	  			updateFinalVersion.value = 'No';
			}
		}
	}
	
}

//CustomAttributeService.js - put it in kra-config.xml
//function CustomAttributeService() { }
// CustomAttributeService._path = '../dwr'; 
// CustomAttributeService.getLookupReturnsForAjaxCall = function(p0, callback) { DWREngine._execute(CustomAttributeService._path, 'CustomAttributeService', 'getLookupReturnsForAjaxCall', p0, callback); } 

