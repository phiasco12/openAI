var exec = require('cordova/exec');

var OpenAITranslation = {
  translate: function (text, targetLanguage, successCallback, errorCallback) {
    exec(successCallback, errorCallback, 'OpenAITranslationPlugin', 'translate', [text, targetLanguage]);
  }
};

module.exports = OpenAITranslation;