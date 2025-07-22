package pages;

import annotations.Path;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import java.util.Random;

@Path("/subscription")
public class SubscriptionPage extends AbsBasePage<SubscriptionPage> {
  Locator buySubscription = page.locator("xpath=/html/body/div[1]/div[1]/main/section[2]/div/div/div[1]/div/div[2]/button");
  Locator moreInfo = page.locator("xpath=/html/body/div[1]/div[1]/main/section[2]/div/div[2]/div[2]/div/div[2]/button");

  public SubscriptionPage(Page page) {
    super(page);
  }

  public SubscriptionPage clickBuySubscription() {
    click(buySubscription.nth(new Random().nextInt(3)));
    return this;
  }

  public SubscriptionPage clickMoreInfo() {
    click(moreInfo);
    return this;
  }

  public String getInfoText() {
    return getText(moreInfo);
  }
}
