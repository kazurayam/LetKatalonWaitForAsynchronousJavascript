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

String MARKER_ID = "marker${new Date().getTime()}"

String js = """
const sleep = m => new Promise(r => setTimeout(r, m));
(async () => {
    await sleep(3000);

	// insert a invisible marker at the bottom of the page
    var newElement = "<div id='${MARKER_ID}' style='display:none'>Marker</div>";
    var bodyElement = document.body;
    bodyElement.innerHTML = bodyElement.innerHTML + newElement;
})();
"""

LocalDateTime start = LocalDateTime.now()

WebUI.executeJavaScript(js, null)
WebUI.verifyElementPresent(makeTestObject("//div[@id='${MARKER_ID}']"), 10)

LocalDateTime end = LocalDateTime.now()
WebUI.comment("${Duration.between(start,end).toMillis()} milliseconds passed")

WebUI.closeBrowser()

