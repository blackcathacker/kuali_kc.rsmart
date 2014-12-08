<%--
 Copyright 2005-2014 The Kuali Foundation
 
 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.osedu.org/licenses/ECL-2.0
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
<title>Unit Hierarchy</title>
<style type="text/css">
/*margin and padding on body element
  can introduce errors in determining
  element position and are not recommended;
  we turn them off as a foundation for YUI
  CSS treatments. */
body {
    margin:0;
    padding:0;
}
</style>

<!-- <script language="JavaScript" type="text/javascript" src="dwr/engine.js"></script> -->
<script language="JavaScript" type="text/javascript" src="dwr/util.js"></script>
<script language="JavaScript" type="text/javascript" src="scripts/kuali_application.js"></script>


</head>
<body>
<html:form styleId="kualiForm" method="post"
    action="/awardHierarchyAwardActionsAjax.do" enctype=""
    onsubmit="return hasFormAlreadyBeenSubmitted();"> 
<!--  initial data here -->
<%-- <input type="text" id = "researchAreas" name="researchAreas"   value="${ResearchAreasForm.researchAreas}"/> --%>

<div id="json">
${KualiForm.awardHierarchy}
</div>

<script type="text/javascript">

alert ("in researchareaload ");
</script>


<!--END SOURCE CODE =============================== -->
</html:form>
</body>
</html>
