package modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.microsoft.playwright.Page;
import pages.ClickHousePage;
import pages.MainPage;
import popup.TeacherAvatarPopup;

public class GuicePageModule extends AbstractModule {
  private Page page;

  public GuicePageModule(Page page) {
    this.page = page;
  }

  @Provides
  @Singleton
  public MainPage provideMainPage() {
    return new MainPage(page);
  }

  @Provides
  @Singleton
  public ClickHousePage getClickHousePage() {
    return new ClickHousePage(page);
  }

  @Provides
  @Singleton
  private TeacherAvatarPopup getTeacherAvatarPopup() {
    return new TeacherAvatarPopup(page);
  }
}