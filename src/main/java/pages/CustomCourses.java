package pages;

import annotations.Path;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

@Path("/custom_courses")
public class CustomCourses extends AbsBasePage<CustomCourses> {
  Locator developmentSection;

  public CustomCourses() {
    super();
    developmentSection = page.locator("a.tn-atom[href='https://otus.ru/categories/programming']");
  }

  public boolean isCustomCoursesPageOpened(Page page) {
    page.waitForLoadState();
    return page.url().contains("custom_courses");
  }

  public void clickDevelopmentSection(Page page) {
    page.waitForLoadState();
    developmentSection = page.locator("a.tn-atom[href='https://otus.ru/categories/programming']");
    developmentSection.scrollIntoViewIfNeeded();
    click(developmentSection);
  }
}
