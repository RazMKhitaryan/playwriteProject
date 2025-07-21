package pages;

import annotations.Path;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

@Path("/")
public class MainPage extends AbsBasePage<MainPage> {
  public MainPage(Page page) {
    super(page);
  }

  public String getMainPageTitle() {
    Locator locator = this.page.locator("h1");
    return locator.textContent();
  }

  public String clickButton(String title) {
    this.page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(title)).click();
    return this.getMainPageTitle();
  }

}
