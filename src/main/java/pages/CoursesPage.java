package pages;

import annotations.Path;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import java.util.ArrayList;
import java.util.List;

@Path("/catalog/courses")
public class CoursesPage extends AbsBasePage<CoursesPage> {
  Locator difficultyLevel;
  Locator resetFilter;
  Locator slider;
  Locator coursesDates;
  Locator developmentCourse;
  Locator architecture;
  Locator checkbox;

  public CoursesPage() {
    super();
    checkbox = page.getByRole(AriaRole.CHECKBOX);
    difficultyLevel = page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName("Любой уровень"));
    developmentCourse = page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName("Программирование"));
    resetFilter = page.getByText("Очистить фильтры");
    slider = page.locator("div[role='slider']");
    coursesDates = page.locator("//*[@id=\"__next\"]/div[1]/main/div/section[2]/div[2]/div/a/div[2]/div/div");
    architecture = page.getByText("Архитектура");
  }

  public boolean isAllCoursesCheckboxSelected() {
    return isLocatorChecked(checkbox.nth(0));
  }

  public boolean isDifficultyLevelSelected() {
    return isLocatorChecked(difficultyLevel);
  }

  public boolean isDevelopmentCourseSelected(Page page) {
    page.waitForLoadState();
    developmentCourse = page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName("Программирование"));
    return isLocatorChecked(developmentCourse);
  }

  public CoursesPage moveLeftSlider(int value) {
    moveSliderHandleOnly(slider.nth(0), value);
    return this;
  }

  public CoursesPage moveRightSlider(int value) {
    moveSliderHandleOnly(slider.nth(1), value);
    return this;
  }

  public List<Integer> getCoursesDatesAsList() {
    implicitWait();
    ArrayList<String> strings = new ArrayList<>();
    for (int i = 0; i < coursesDates.all().size(); i++) {
      strings.add(getText(coursesDates.nth(i)));
    }
    return strings.stream()
        .map(String::trim)
        .map(courseDate -> {
          String[] parts = courseDate.split("·");
          String durationPart = parts[1].trim();
          String[] words = durationPart.split(" ");
          try {
            return Integer.parseInt(words[0]);
          } catch (NumberFormatException e) {
            e.printStackTrace();
          }
          return null;
        }).toList();
  }

  public CoursesPage clickArchitecture() {
    click(architecture.nth(1));
    return this;
  }

  public String getCoursesInfo(int index) {
    implicitWait();
    return getText(coursesDates.nth(index));
  }

  public CoursesPage resetFilter() {
    click(resetFilter);
    return this;
  }
}
