package pages;

import annotations.Path;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

@Path("/custom_courses")
public class CustomCourses extends AbsBasePage<CustomCourses> {
  Locator developmentSection = page.locator("a.tn-atom[href='https://otus.ru/categories/programming']");

  public CustomCourses(Page page) {
    super(page);
  }

  public boolean isCustomCoursesPageOpened() {
    page.waitForLoadState();
    return page.url().contains("custom_courses");
  }

  public void clickDevelopmentSection() {
    page.waitForLoadState();
    developmentSection.scrollIntoViewIfNeeded();
    click(developmentSection);
  }
}
