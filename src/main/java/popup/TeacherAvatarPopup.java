package popup;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import pages.AbsBasePage;

public class TeacherAvatarPopup extends AbsBasePage<TeacherAvatarPopup> {
  Locator leftIcon = page.locator("//*[@id=\"__PORTAL__\"]/div/div/div/div/div[2]/button[1]");
  Locator rightIcon = page.locator("//*[@id=\"__PORTAL__\"]/div/div/div/div/div[2]/button[2]");
  Locator teacherNames = page.locator("xpath=//*[@id='__PORTAL__']/div/div/div/div/div[1]/div/div/div/div/h3");

  public TeacherAvatarPopup(Page page) {
    super(page);
  }

  public TeacherAvatarPopup clickLeftIcon() {
    click(leftIcon);
    return this;
  }

  public TeacherAvatarPopup clickRightIcon() {
    click(rightIcon);
    return this;
  }

  public String getTeacherName(int index) {
    waitForVisibility(teacherNames.nth(index));
    return teacherNames.nth(index).textContent();
  }
}
