import play.api._
import play.api.mvc._
import play.api.Play.current

package models {

  import org.scalaquery.ql._
  import org.scalaquery.ql.extended.{ExtendedTable => Table}
  import org.scalaquery.ql.extended.H2Driver.Implicit._
  import org.scalaquery.session._

  case class Account(id: Long)

  object accounts extends Table[Long]("accounts") {
    val db = Database.forDataSource(play.api.db.DB.getDataSource())

    val id = column[Long]("id", O PrimaryKey)

    def * = id

    def findById(id: Long): Option[Account] = db withSession { implicit session =>
      (for {a <- accounts if a.id == id} yield a).firstOption.map(Account)
    }
  }

  class AccountService {
    def find(id: Long): Option[Account] = accounts.findById(id)
  }

  object AccountService extends AccountService

}

package controllers {

  class Application(accountService: models.AccountService) extends Controller {

    def index = Action {
      Ok(views.html.index("Your new application is ready."))
    }

    def showAccountDetail(id: Long) = Action {
      accountService.find(id).map {
        a =>
          Ok(a.toString)
      } getOrElse {
        NotFound("There is no account with id " + id)
      }
    }

  }

  object Application extends Application(models.AccountService)

}

