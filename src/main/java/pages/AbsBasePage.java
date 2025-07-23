package pages;

import annotations.Path;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Mouse;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.BoundingBox;
import com.microsoft.playwright.options.WaitForSelectorState;
import extensions.UIExtension;

public abstract class AbsBasePage<T> {
  protected Page page;
  private String baseUrl = System.getProperty("base.url", "https://otus.ru");

  public AbsBasePage() {
    this.page = UIExtension.PAGE.get();
  }

  public T open() {
    page.navigate(this.baseUrl + getPath());
    addCookie();
    page.reload();
    page.waitForLoadState();
    return (T) this;
  }

  private String getPath() {
    Class<T> clazz = (Class<T>) getClass();
    if (clazz.isAnnotationPresent(Path.class)) {
      Path path = clazz.getDeclaredAnnotation(Path.class);
      return path.value();
    }
    return "";
  }

  public void addCookie() {
    page.evaluate("() => localStorage.getItem('cookieAccess')");
  }

  public void waitForVisibility(Locator locator) {
    locator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
  }

  public void implicitWait() {
    page.waitForTimeout(5000);

  }
  public String getText(Locator locator) {
    locator.scrollIntoViewIfNeeded();
    waitForVisibility(locator);
    return locator.textContent();
  }

  public void click(Locator locator) {
    locator.scrollIntoViewIfNeeded();
    waitForVisibility(locator);
    locator.click();
  }

  public boolean isLocatorChecked(Locator locator) {
    locator.scrollIntoViewIfNeeded();
    waitForVisibility(locator);
    return locator.isChecked();
  }

  public void moveSliderHandleOnly(Locator slider, int moveByX) {
    waitForVisibility(slider);
    slider.scrollIntoViewIfNeeded();
    BoundingBox box = slider.boundingBox();
    page.mouse().move(box.x + box.width / 2, box.y + box.height / 2);
    page.mouse().down();
    page.mouse().move(box.x + box.width / 2 + moveByX, box.y + box.height / 2, new Mouse.MoveOptions().setSteps(5));
    page.mouse().up();
  }

  public boolean isLocatorVisible(Locator locator) {
    waitForVisibility(locator);
    locator.scrollIntoViewIfNeeded();
    return locator.isVisible();
  }
  @SuppressWarnings("EI_EXPOSE_REP2")
  public void setPage(Page newPage) {
    this.page = newPage;
  }
}