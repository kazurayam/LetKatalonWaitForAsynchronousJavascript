import java.time.Duration
import java.time.LocalDateTime

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

TestObject makeTestObject(String xpath) {
	TestObject tObj = new TestObject(xpath)
	tObj.addProperty("xpath", ConditionType.EQUALS, xpath)
	return tObj
}

WebUI.openBrowser("")
WebUI.navigateToUrl("https://katalon-demo-cura.herokuapp.com/profile.php#login")

String js = """
const sleep = m => new Promise(r => setTimeout(r, m));
(async () => {
    await sleep(3000);
    var newElement = "<div id='marker'>Marker</div>";
    var bodyElement = document.body;
    bodyElement.innerHTML = newElement + bodyElement.innerHTML;
})();
"""

LocalDateTime start = LocalDateTime.now()

WebUI.executeJavaScript(js, null)
WebUI.verifyElementPresent(makeTestObject("//div[@id='marker']"), 10)

LocalDateTime end = LocalDateTime.now()
WebUI.comment("${Duration.between(start,end).toMillis()} milliseconds passed")

WebUI.closeBrowser()

