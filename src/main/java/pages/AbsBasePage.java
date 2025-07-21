package pages;

import annotations.Path;
import com.microsoft.playwright.Page;

public abstract class AbsBasePage<T> {
  protected Page page;
  private String baseUrl = System.getProperty("base.url", "https://otus.ru");

  public AbsBasePage(Page page) {
    this.page = page;
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
}