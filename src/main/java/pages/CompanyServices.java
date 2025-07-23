package pages;

import annotations.Path;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

@Path("/uslugi-kompaniyam")
public class CompanyServices extends AbsBasePage<CompanyServices> {
  Locator moreInfo;

  public CompanyServices() {
    super();
    moreInfo = page.locator("//*[@id=\"__next\"]/div[1]/main/div[9]/div/div/a/button");
  }

  public Page clickMoreInfo() {
    Page newTab = page.waitForPopup(() -> {
      click(moreInfo);
    });
    newTab.waitForLoadState();
    return newTab;
  }
}
