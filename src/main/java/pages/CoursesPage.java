package pages;

import annotations.Path;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

@Path("/catalog/courses")
public class CoursesPage extends AbsBasePage<CoursesPage> {
  Locator allCoursesCheckbox = page.locator("xpath=/html/body/div[1]/div[1]/main/div/section[1]/div[1]/div[2]/div/div/div[1]/div/input");
  Locator difficultyLevel = page.locator("xpath=/html/body/div[1]/div[1]/main/div/section[1]/div[2]/div[2]/div/div/div[1]/div/input");
  Locator slider = page.locator("div[role='slider']");
  Locator sliderRight = page.locator("xpath=/html/body/div[1]/div[1]/main/div/section[1]/div[4]/div[2]/div/div/div[2]/div/div[2]");

  public CoursesPage(Page page) {
    super(page);
  }

  public boolean isAllCoursesCheckboxSelected() {
    return isLocatorChecked(allCoursesCheckbox);
  }

  public boolean isDifficultyLevelSelected() {
    return isLocatorChecked(difficultyLevel);
  }

  public void moveLeftSlider(int value) {
    moveSliderHandleOnly(slider.nth(0), value);
  }

  public void moveRightSlider(int value) {
    moveSliderHandleOnly(slider.nth(1), value);
  }
}
