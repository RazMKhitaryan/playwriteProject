package popup;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import pages.AbsBasePage;

public class LoginPopup extends AbsBasePage<LoginPopup> {
  Locator xbutton = page.locator("xpath=/html/body/div[2]/div/div/div[2]");

  public LoginPopup(Page page) {
    super(page);
  }

  public boolean isClickXButtonVisible() {
    return isLocatorVisible(xbutton);
  }
}
