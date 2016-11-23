
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>JSON Editor Example</title>

<!-- placeholders for the theme switcher -->
<link rel='stylesheet' id='theme_stylesheet'>
<link rel='stylesheet' id='icon_stylesheet'>

<style>
[class*="foundicon-"] {
	font-family: GeneralFoundicons;
	font-style: normal;
}
</style>


<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src='js/editor.js'></script>

</head>
<body>


	<script>
function go() {
	$.ajax({
	    url: "/jersey/rest/service/trovaMedico/pippo",
	    type: "GET",
	    data: '{"sql":"select * from dual"}',
	    contentType: "application/json",
	    cache: false,
	    dataType: "json",
		success: function(d) { alert(JSON.stringify(d)); }
	});
}
function deleteMedico(cf) {
	$.ajax({
	    url: "/jersey/rest/service/deleteMedico/"+cf,
	    type: "DELETE",
	    data: '{"sql":"select * from dual"}',
	    contentType: "application/json",
	    cache: false,
	    dataType: "json",
		success: function(d) { alert(JSON.stringify(d)); }
	});
}
function insertMedico(m) {
	$.ajax({
	    url: "/jersey/rest/service/insertMedico",
	    type: "POST",
	    data: JSON.stringify(m),
	    contentType: "application/json",
	    cache: false,
	    dataType: "json",
		success: function(d) { alert(JSON.stringify(d)); }
	});
}
</script>
	->
	<input type="button" onclick="go()" name="go" value="go" />
<input type="button" onclick="deleteMedico('pippo')" name="del" value="del" />
<input type="button" onclick="insertMedico({'nome':'nome','cognome':'cognome','cf':'cf','canc':'N'})" name="ins" value="ins" />

<a href="/jersey/w/echo">vai a wicket</a>

</body>
</html>
