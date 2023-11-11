/*
 * Notes: The @objc shows that this class & function should be exposed to Cordova.
 */
import CoreServices
import UIKit



@objc(PluginReference) class YourPluginName : CDVPlugin {
    
    @objc(yourFunction:) // Declare your function name.
    func yourFunction(command: CDVInvokedUrlCommand) { // write the function code.
        //Write swift code here

        print("*****************************")
        print("Running the plugin swift code")

        //to access data passed in through options object to the plugin code
        var content: [String: String] = command.arguments![0] as! [String : String]

        let item1 = content["item1"] ?? ""
        let item2 = content["item2"] ?? ""
        
        print("Item1: \(item1) Item2: \(item2)")
        print("*****************************")
       
        // Set the plugin result to fail.
        var pluginResult = CDVPluginResult (status: CDVCommandStatus_ERROR, messageAs: "The Plugin Failed");
        // Set the plugin result to succeed.
        pluginResult = CDVPluginResult(status: CDVCommandStatus_OK, messageAs: "Plugin was Sucessful");
        // Send the function result back to Cordova.
        self.commandDelegate!.send(pluginResult, callbackId: command.callbackId);
    }
}

