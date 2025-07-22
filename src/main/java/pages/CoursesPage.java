package pages;

import annotations.Path;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import java.util.ArrayList;
import java.util.List;

@Path("/catalog/courses")
public class CoursesPage extends AbsBasePage<CoursesPage> {
  Locator allCoursesCheckbox = page.locator("xpath=/html/body/div[1]/div[1]/main/div/section[1]/div[1]/div[2]/div/div/div[1]/div/input");
  Locator difficultyLevel = page.locator("xpath=/html/body/div[1]/div[1]/main/div/section[1]/div[2]/div[2]/div/div/div[1]/div/input");
  Locator resetFilter = page.locator("//*[@id=\"__next\"]/div[1]/main/div/section[1]/button");
  Locator slider = page.locator("div[role='slider']");
  Locator coursesDates = page.locator("//*[@id=\"__next\"]/div[1]/main/div/section[2]/div[2]/div/a/div[2]/div/div");
  Locator developmentCourse = page.locator("xpath=/html/body/div[1]/div[1]/main/div/section[1]/div[1]/div[2]/div/div/div[2]/div/input");
  Locator architecture = page.getByText("Архитектура");
  public CoursesPage(Page page) {
    super(page);
  }

  public boolean isAllCoursesCheckboxSelected() {
    return isLocatorChecked(allCoursesCheckbox);
  }

  public boolean isDifficultyLevelSelected() {
    return isLocatorChecked(difficultyLevel);
  }

  public boolean isDevelopmentCourseSelected() {
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
