package pages;

import annotations.Path;
import com.microsoft.playwright.Locator;

@Path("/custom_courses")
public class CustomCourses extends AbsBasePage<CustomCourses> {
  Locator developmentSection;

  public CustomCourses() {
    super();
    developmentSection = page.locator("a.tn-atom[href='https://otus.ru/categories/programming']");
  }

  public boolean isCustomCoursesPageOpened() {
    page.waitForLoadState();
    return page.url().contains("custom_courses");
  }

  public void clickDevelopmentSection() {
    page.waitForLoadState();
    developmentSection = page.locator("a.tn-atom[href='https://otus.ru/categories/programming']");
    developmentSection.scrollIntoViewIfNeeded();
    click(developmentSection);
  }
}
