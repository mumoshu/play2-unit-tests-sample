import org.specs2.mutable._
import org.specs2.mock._
import play.api.test._
import play.api.test.Helpers._
import models._
import controllers._

/**
 * @author KUOKA Yusuke
 */

object ApplicationSpec extends Specification with Mockito {

  "Application" should {

    "show details of the account" in {

      // models.AccountServiceのモック作成
      val accountService = mock[models.AccountService]
      // controllers.Applicationにmodels.AccountServiceのモックをDIする
      val application = new controllers.Application(accountService)

      // findのスタブ作成
      accountService.find(1L) returns None
      // アクション実行
      val result = application.showAccountDetail(1L)(FakeRequest(GET, "/accounts/1"))
      // モックの検証
      there was one(accountService).find(1L)
      // レスポンスの検証
      contentAsString(result) must be equalTo "There is no account with id 1"
    }
  }

}
