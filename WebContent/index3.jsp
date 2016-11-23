
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


<script src='js/editor.js'></script>

</head>
<body>

<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

	<script>
function go() {
	$.ajax({
	    url: "/jersey/rest/service/command",
	    type: "POST",
	    data: '{"sql":"select * from dual"}',
	    contentType: "application/json",
	    cache: false,
	    dataType: "json",
		success: function(d) { alert(JSON.stringify(d)); }
	});
}
</script>
	->
	<input type="button" onclick="go()" name="go" value="go" />




	<div class='container'>
		<div class='row'>
			<div class='span8 col-md-8 columns eight large-8'>
				<h2>Editor</h2>
				<p>Below is the editor generated from the JSON Schema.</p>

				<div id='editor'></div>
			</div>
			<div class='span4 col-md-4 columns four large-4'>
				<div>
					<a href='#' id='direct_link'>Direct Link</a> (preserves schema,
					value, and options)
				</div>
				
					<a href='rest/service/test' id='test'>test</a> 
					<a href='rest/service/command' id='commmand'>command</a> 

				<h2>JSON Output</h2>
				<p>
					You can also make changes to the JSON here and set the value in the
					editor by clicking
					<button class='btn btn-primary' id='setvalue'>Update Form</button>
				</p>
				<textarea id='output'
					style='width: 100%; height: 300px; font-family: monospace;'
					class='form-control'></textarea>

				<h2>Options</h2>
				<div id='options_holder'>
					<div>
						<label>CSS Framework</label> <select id='theme_switcher'
							class='form-control'>
							<option value='barebones'>Barebones</option>
							<option value='html'>HTML</option>
							<option value='jqueryui'>jQuery UI</option>
							<option value='bootstrap2'>Bootstrap 2</option>
							<option value='bootstrap3'>Bootstrap 3</option>
							<option value='foundation3'>Foundation 3</option>
							<option value='foundation4'>Foundation 4</option>
							<option value='foundation5'>Foundation 5</option>
							<option value='foundation6'>Foundation 6</option>
						</select>
					</div>
					<div>
						<label>Icon Library</label> <select id='icon_switcher'
							class='form-control'>
							<option value=''>None</option>
							<option value='jqueryui'>jQuery UI</option>
							<option value='bootstrap2'>Bootstrap 2 Glyphicons</option>
							<option value='bootstrap3'>Bootstrap 3 Glyphicons</option>
							<option value='foundation2'>Foundicons 2</option>
							<option value='foundation3'>Foundicons 3</option>
							<option value='fontawesome3'>FontAwesome 3</option>
							<option value='fontawesome4'>FontAwesome 4</option>
						</select>
					</div>
					<div>
						<label>Object Layout</label> <select id='object_layout'
							class='form-control'>
							<option value='normal'>normal</option>
							<option value='grid'>grid</option>
						</select>
					</div>
					<div>
						<label>Show Errors</label> <select id='show_errors'
							class='form-control'>
							<option value='interaction'>On Interaction</option>
							<option value='change'>On Field Change</option>
							<option value='always'>Always</option>
							<option value='never'>Never</option>
						</select>
					</div>
					<div>
						<label>Boolean options</label> <select multiple size=9
							id='boolean_options' style='width: 100%;' class='form-control'>
							<option value='required_by_default'>Object properties
								required by default</option>
							<option value='display_required_only'>Only show required
								properties by default</option>
							<option value='no_additional_properties'>No additional
								object properties</option>
							<option value='ajax'>Allow loading schemas via Ajax</option>
							<option value='disable_edit_json'>Disable "Edit JSON"
								buttons</option>
							<option value='disable_collapse'>Disable collapse
								buttons</option>
							<option value='disable_properties'>Disable properties
								buttons</option>
							<option value='disable_array_add'>Disable array add
								buttons</option>
							<option value='disable_array_reorder'>Disable array move
								buttons</option>
							<option value='disable_array_delete'>Disable array
								delete buttons</option>
							<option value='disable_array_delete_all_rows'>Disable
								array delete all rows buttons</option>
							<option value='disable_array_delete_last_row'>Disable
								array delete last row buttons</option>
						</select>
					</div>
				</div>

				<h2>Validation</h2>
				<p>This will update whenever the form changes to show validation
					errors if there are any.</p>
				<textarea id='validate'
					style='width: 100%; height: 100px; font-family: monospace;'
					readonly disabled class='form-control'></textarea>
			</div>
			<div class='row'>
				<div class='span12 col-md-12 columns twelve large-12'>
					<h2>Schema</h2>
					<p>
						You can change the schema and see how the generated form looks.
						After you make changes, click
						<button class='btn btn-primary' id='setschema'>Update
							Schema</button>
					</p>

					<textarea id='schema'
						style='width: 100%; height: 450px; font-family: monospace;'
						class='form-control'></textarea>
				</div>
			</div>
			<div class='row'>
				<div class='span12 col-md-12 columns twelve large-12'>
					<h2>Code</h2>
					<pre>
						<code>// Set default options
JSONEditor.defaults.options.theme = 'bootstrap2';

// Initialize the editor
var editor = new JSONEditor(document.getElementById("editor_holder"),{
  schema: {
      type: "object",
      properties: {
          name: { "type": "string" }
      }
  }
});

// Set the value
editor.setValue({
    name: "John Smith"
});

// Get the value
var data = editor.getValue();
console.log(data.name); // "John Smith"

// Validate
var errors = editor.validate();
if(errors.length) {
  // Not valid
}

// Listen for changes
editor.on("change",  function() {
  // Do something...
});</code>
					</pre>
				</div>
			</div>
		</div>
	</div>
	<script>
(function() {
    var schema;
    
    // Default starting schema
    if(!schema) {
        schema = {
            title: "Person",
            headerTemplate: "{{ self.name }} (age {{ self.age }}) <a href='index2.jsp'>2</a>",
            type: "object",
            properties: {
                link: 
                {
                	  "title": "Document filename",
                	  "type": "string",
                	  "links": [
                	    {
                	      "rel": "Download File",
                	      "href": "asas",
                	      // Can also set `download` to a string as per the HTML5 spec
                	      "download": true
                	    }
                	  ]
                	},
                	table :{
                		  "type": "array",
                		  "format": "table",
                		  "items": {
                		    "type": "object",
                		    "properties": {
                		      "name": {
                		        "type": "string"
                		      }
                		    }
                		  }
                		}
                		                	,
                    	grid:{
                    		  "type": "object",
                    		  "properties": {
                    		    "name": { "type": "string" }
                    		  },
                    		  "format": "grid"
                    		},
                    		depend:
                    		{
                    			  "type": "object",
                    			  "properties": {
                    			    "first_name": {
                    			      "type": "string"
                    			    },
                    			    "last_name": {
                    			      "type": "string"
                    			    },
                    			    "full_name": {
                    			      "type": "string",
                    			      "watch": {
                    			        "fname": "first_name",
                    			        "lname": "last_name"
                    			      }
                    			    }
                    			  }
                    			},
              	bit : {
              		  "type": "boolean",
              		  "format": "checkbox"
              		},
                wysi :
                {
                	  "type": "string",
                	  "format": "html",
                	  "options": {
                	    "wysiwyg": true
                	  }
                	},
                	ace: {
                		  "type": "string",
                		  "format": "yaml"
                		},
                video:	{
                		  "title": "Video filename",
                		  "type": "string",
                		  "links": [
                		    {
                		      "href": "/videos/{{self}}.mp4",
                		      "mediaType": "video/mp4"
                		    }
                		  ]
                		},
                name: {
                    type: "string",
                    description: "First and Last name",
                    minLength: 4,
                    default: "Jeremy Dorn"
                },
                age: {
                    type: "integer",
                    default: 25,
                    minimum: 18,
                    maximum: 99
                },
                favorite_color: {
                    type: "string",
                    format: "color",
                    title: "favorite color",
                    default: "#ffa500"
                },
                gender: {
                    type: "string",
                    enum: ["male", "female"]
                },
                location: {
                    type: "object",
                    title: "Location",
                    properties: {
                        city: {
                            type: "string",
                            default: "San Francisco"
                        },
                        state: {
                            type: "string",
                            default: "CA"
                        },
                        citystate: {
                            type: "string",
                            description: "This is generated automatically from the previous two fields",
                            template: "{{city}}, {{state}}",
                            watch: {
                                city: 'location.city',
                                state: 'location.state'
                            }
                        }
                    }
                },
                            
                pets: {
                    type: "array",
                    format: "table",
                    title: "Pets",
                    uniqueItems: true,
                    items: {
                        type: "object",
                        title: "Pet",
                        properties: {
                            type: {
                                type: "string",
                                enum: ["cat","dog","bird","reptile","other"],
                                default: "dog"
                            },
                            name: {
                                type: "string"
                            }
                        }
                    },
                    default: [
                        {
                            type: "dog",
                            name: "Walter"
                        }
                    ]
                }
            }
        }
    }

    // Divs/textareas on the page
    var $schema = document.getElementById('schema');
    var $output = document.getElementById('output');
    var $editor = document.getElementById('editor');
    var $validate = document.getElementById('validate');

    // Buttons
    var $set_schema_button = document.getElementById('setschema');
    var $set_value_button = document.getElementById('setvalue');

    var jsoneditor;

    var updateDirectLink = function() {
        var url = window.location.href.replace(/\?.*/,'');

        url += '?schema='+LZString.compressToBase64(JSON.stringify(schema));
        url += '&value='+LZString.compressToBase64(JSON.stringify(jsoneditor.getValue()));

        for(var i in JSONEditor.defaults.options) {
            if(!JSONEditor.defaults.options.hasOwnProperty(i)) continue;
            if(JSONEditor.defaults.options[i]===false) continue;
            else if(JSONEditor.defaults.options[i]===true) {
                url += '&'+i;
            }
            else {
                url += '&'+i+'='+JSONEditor.defaults.options[i];
            }
        }

        document.getElementById('direct_link').href = url;
    };

    var reload = function(keep_value) {
        var startval = (jsoneditor && keep_value)? jsoneditor.getValue() : window.startval;
        window.startval = undefined;

        if(jsoneditor) jsoneditor.destroy();
        jsoneditor = new JSONEditor($editor,{
            schema: schema,
            startval: startval
        });
        window.jsoneditor = jsoneditor;

        // When the value of the editor changes, update the JSON output and validation message
        jsoneditor.on('change',function() {
            var json = jsoneditor.getValue();

            $output.value = JSON.stringify(json,null,2);

            var validation_errors = jsoneditor.validate();
            // Show validation errors if there are any
            if(validation_errors.length) {
                $validate.value = JSON.stringify(validation_errors,null,2);
            }
            else {
                $validate.value = 'valid';
            }

            updateDirectLink();
        });
    };

    // Start the schema and output textareas with initial values
    $schema.value = JSON.stringify(schema,null,2);
    $output.value = '';

    // When the 'update form' button is clicked, set the editor's value
    $set_value_button.addEventListener('click',function() {
        jsoneditor.setValue(JSON.parse($output.value));
    });

    // Update the schema when the button is clicked
    $set_schema_button.addEventListener('click',function() {
        try {
            schema = JSON.parse($schema.value);
        }
        catch(e) {
            alert('Invalid Schema: '+e.message);
            return;
        }

        reload();
    });

    // Set the theme by loading the right stylesheets
    var setTheme = function(theme,no_reload) {
        theme = theme || '';

        var mapping = {
            barebones: '',
            html: '',
            bootstrap2: '//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css',
            bootstrap3: '//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css',
            foundation3: '//cdnjs.cloudflare.com/ajax/libs/foundation/3.2.5/stylesheets/foundation.css',
            foundation4: '//cdn.jsdelivr.net/foundation/4.3.2/css/foundation.min.css',
            foundation5: '//cdn.jsdelivr.net/foundation/5.0.2/css/foundation.min.css',
            foundation6: '//cdn.jsdelivr.net/foundation/6.2.1/foundation.min.css',
            jqueryui: '//code.jquery.com/ui/1.10.3/themes/south-street/jquery-ui.css'
        };

        if(typeof mapping[theme] === 'undefined') {
            theme = 'bootstrap3';
            document.getElementById('theme_switcher').value = theme;
        }

        JSONEditor.defaults.options.theme = theme;

        document.getElementById('theme_stylesheet').href = mapping[theme];
        document.getElementById('theme_switcher').value = JSONEditor.defaults.options.theme;

        if(!no_reload) reload(true);
    };

    // Set the icontheme
    // Set the theme by loading the right stylesheets
    var setIconlib = function(iconlib,no_reload) {
        iconlib = iconlib || '';
        var mapping = {
            foundation2: '//cdnjs.cloudflare.com/ajax/libs/foundicons/2.0/stylesheets/general_foundicons.css',
            foundation3: '//cdnjs.cloudflare.com/ajax/libs/foundicons/3.0.0/foundation-icons.css',
            fontawesome3: '//cdnjs.cloudflare.com/ajax/libs/font-awesome/3.2.1/css/font-awesome.css',
            fontawesome4: '//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.0.3/css/font-awesome.css'
        };

        JSONEditor.defaults.options.iconlib = iconlib;

        document.getElementById('icon_stylesheet').href = mapping[iconlib] || '';
        document.getElementById('icon_switcher').value = JSONEditor.defaults.options.iconlib;

        if(!no_reload) reload(true);
    };

    var refreshBooleanOptions = function(no_reload) {
        var boolean_options = document.getElementById('boolean_options').children;
        for(var i=0; i<boolean_options.length; i++) {
            JSONEditor.defaults.options[boolean_options[i].value] = boolean_options[i].selected;
        }
        if(!no_reload) reload(true);
    };

    // Change listeners for options
    document.getElementById('theme_switcher').addEventListener('change',function() {
        setTheme(this.value);
    });
    document.getElementById('icon_switcher').addEventListener('change',function() {
        setIconlib(this.value);
    });
    document.getElementById('object_layout').addEventListener('change',function() {
       JSONEditor.defaults.options.object_layout = this.value;
        reload(true);
    });
    document.getElementById('show_errors').addEventListener('change',function() {
       JSONEditor.defaults.options.show_errors = this.value;
        reload(true);
    });
    document.getElementById('boolean_options').addEventListener('change',function() {
        refreshBooleanOptions();
    });

    // Get starting value from url
    if(window.location.href.match('[?&]value=([^&]+)')) {
      window.startval = JSON.parse(LZString.decompressFromBase64(window.location.href.match('[?&]value=([^&]+)')[1]));
    }

    // Set options from direct link
    setTheme((window.location.href.match(/[?&]theme=([^&]+)/)||[])[1] || 'bootstrap2', true);

    setIconlib((window.location.href.match(/[?&]iconlib=([^&]*)/)||[null,'fontawesome4'])[1], true);

    document.getElementById('object_layout').value = (window.location.href.match(/[?&]object_layout=([^&]+)/)||[])[1] || 'normal';
    JSONEditor.defaults.options.object_layout = document.getElementById('object_layout').value;

    document.getElementById('show_errors').value = (window.location.href.match(/[?&]show_errors=([^&]+)/)||[])[1] || 'interaction';
    JSONEditor.defaults.options.show_errors = document.getElementById('show_errors').value;

    var boolean_options = document.getElementById('boolean_options').children;
    for(var i=0; i<boolean_options.length; i++) {
        if(window.location.href.match(new RegExp('[?&]'+boolean_options[i].getAttribute('value')+'([&=]|$)'))) {
            boolean_options[i].selected = true;
        }
    }
    refreshBooleanOptions(true);

    reload();
})();
</script>

</body>
</html>
