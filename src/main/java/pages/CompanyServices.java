package pages;

import annotations.Path;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

@Path("/uslugi-kompaniyam")
public class CompanyServices extends AbsBasePage<CompanyServices> {
  Locator moreInfo;

  public CompanyServices() {
    super();
    moreInfo = page.getByText("Подробнее");
  }

  public Page clickMoreInfo() {
    Page newTab = page.waitForPopup(() -> click(moreInfo));
    newTab.waitForLoadState();
    this.page = newTab;
    return newTab;
  }
}
