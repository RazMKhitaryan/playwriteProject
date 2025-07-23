package extensions;

import com.google.inject.Guice;
import com.microsoft.playwright.*;
import modules.GuicePageModule;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import java.io.File;
import java.util.List;

public class UIExtension implements BeforeEachCallback, AfterEachCallback {

  private static final ThreadLocal<Playwright> PLAYWRIGHT_THREAD_LOCAL = new ThreadLocal<>();
  private static final ThreadLocal<Browser> BROWSER_THREAD_LOCAL = new ThreadLocal<>();
  private static final ThreadLocal<BrowserContext> BROWSER_CONTEXT_THREAD_LOCAL = new ThreadLocal<>();
  public static final ThreadLocal<Page> PAGE = new ThreadLocal<>();

  @Override
  public void beforeEach(ExtensionContext context) {
    try {
      Playwright playwright = Playwright.create();
      Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
          .setHeadless(false)
          .setArgs(List.of("--start-maximized"))
      );

      BrowserContext browserContext = browser.newContext(
          new Browser.NewContextOptions().setViewportSize(null)
      );

      browserContext.tracing().start(new Tracing.StartOptions()
          .setSnapshots(true)
          .setSources(true)
          .setScreenshots(true)
      );

      Page page = browserContext.newPage();
      PLAYWRIGHT_THREAD_LOCAL.set(playwright);
      BROWSER_THREAD_LOCAL.set(browser);
      BROWSER_CONTEXT_THREAD_LOCAL.set(browserContext);
      PAGE.set(page);
      Guice.createInjector(new GuicePageModule()).injectMembers(context.getTestInstance().get());
    } catch (Exception e) {
      throw new RuntimeException("Error during test setup: " + e.getMessage(), e);
    }
  }

  @Override
  public void afterEach(ExtensionContext context) {
    try {
      Page page = PAGE.get();
      if (page != null) page.close();

      BrowserContext contextObj = BROWSER_CONTEXT_THREAD_LOCAL.get();
      if (contextObj != null) {
        contextObj.tracing().stop(new Tracing.StopOptions()
            .setPath(new File("trace-" + Thread.currentThread().getId() + ".zip").toPath())
        );
        contextObj.close();
      }

      Browser browser = BROWSER_THREAD_LOCAL.get();
      if (browser != null) browser.close();

      Playwright playwright = PLAYWRIGHT_THREAD_LOCAL.get();
      if (playwright != null) playwright.close();

    } catch (Exception e) {
      throw new RuntimeException("Error during test teardown: " + e.getMessage(), e);
    } finally {
      PAGE.remove();
      BROWSER_CONTEXT_THREAD_LOCAL.remove();
      BROWSER_THREAD_LOCAL.remove();
      PLAYWRIGHT_THREAD_LOCAL.remove();
    }
  }
}
