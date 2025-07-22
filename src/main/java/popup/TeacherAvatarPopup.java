package popup;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import pages.AbsBasePage;

public class TeacherAvatarPopup extends AbsBasePage<TeacherAvatarPopup> {
  Locator xButton = page.locator("//*[@id=\"__PORTAL__\"]/div/div/div/button");
  Locator leftIcon = page.locator("//*[@id=\"__PORTAL__\"]/div/div/div/div/div[2]/button[1]");
  Locator rightIcon = page.locator("//*[@id=\"__PORTAL__\"]/div/div/div/div/div[2]/button[2]");
  Locator teacherNames = page.locator("xpath=//*[@id='__PORTAL__']/div/div/div/div/div[1]/div/div/div/div/h3");

  public TeacherAvatarPopup(Page page) {
    super(page);
  }

  public void clickXButton() {
    click(xButton);
  }

  public void clickLeftIcon() {
    click(leftIcon);
  }

  public void clickRightIcon() {
    click(rightIcon);
  }

  public String getTeacherName(int index) {
    waitForVisibility(teacherNames.nth(index));
    return teacherNames.nth(index).textContent();
  }
}
