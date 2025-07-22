package pages;

import annotations.Path;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

@Path("/uslugi-kompaniyam")
public class CompanyServices extends AbsBasePage<CompanyServices> {
  Locator moreInfo = page.locator("//*[@id=\"__next\"]/div[1]/main/div[9]/div/div/a/button");

  public CompanyServices(Page page) {
    super(page);
  }

  public CustomCourses clickMoreInfo() {
    Page newTab = page.waitForPopup(() -> click(moreInfo));
    newTab.waitForLoadState();
    this.page = newTab;
    return new CustomCourses(newTab);
  }
}
