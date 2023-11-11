# cordova-plugin-example-structure
Example folder structure of a Cordova plugin

This structure is set up to work automatically as is, however when actually building your plugin you should customize to match your plugin. 

To do so, do the following
- Replace PluginName with a name for your plugin (eg StructureExample)
- Replace PluginReference with a reference name for your ios plugin module (eg StructureExamplePlugin)
- Replace yourFunction with a name for your swift function
- rename plugin.js to a more descriptive name but make sure to also update the references to it in plugin.xml

- for android update the package name in the java folder and change it in the plugin.xml file to com.yourplugin.cordova or in the sourcefile entry to com/yourplugin/cordova

Android:
- if you need external frameworks use the build-extras.gradle. Otherwise you don't need it. 



To call your plugin within your cordova project use the following code: 


 PluginName.yourFunction(
            function(result){
                //Use successful result
            },
            function(error){
                //Error result
            },
            {
                item1: "some value",
                item2: "Hi How Are Ya"
            }
        );