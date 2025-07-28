package popup;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import pages.AbsBasePage;

public class TeacherAvatarPopup extends AbsBasePage<TeacherAvatarPopup> {
  Locator leftIcon;
  Locator rightIcon;
  Locator teacherNames;

  public TeacherAvatarPopup() {
    super();
    leftIcon = page.locator("//*[@id=\"__PORTAL__\"]/div/div/div/div/div[2]/button[1]");
    rightIcon = page.locator("//*[@id=\"__PORTAL__\"]/div/div/div/div/div[2]/button[2]");
    teacherNames = page.locator("xpath=//*[@id='__PORTAL__']/div/div/div/div/div[1]/div/div/div/div/h3");

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
