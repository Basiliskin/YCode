var exec = require('cordova/exec');

exports.scan = function(arg0, success, error) {
    exec(success, error, "YCodeScanner", "scan", [arg0]);
};
exports.openScanner = function(apiKey, success, error) {
    exec(success, error, "YCodeScanner", "openScanner", [apiKey]);
};
