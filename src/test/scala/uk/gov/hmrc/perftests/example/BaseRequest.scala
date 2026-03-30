package uk.gov.hmrc.perftests.example

import io.gatling.core.Predef._
import io.gatling.core.check.CheckBuilder
import io.gatling.core.check.regex.RegexCheckType
import uk.gov.hmrc.performance.conf.ServicesConfiguration

trait BaseRequest extends ServicesConfiguration {

  val baseUrl: String = baseUrlFor("nova-imports-notification-frontend")
  val route: String   = "/nova-imports/start"
  val authUrl: String  = baseUrlFor("auth-login-stub")
  val authLoginStubUrl: String = s"$authUrl/auth-login-stub/gg-sign-in"

  val CsrfPattern = """<input type="hidden" name="csrfToken" value="([^"]+)""""

  protected def saveCsrfToken(): CheckBuilder[RegexCheckType, String] =
    regex(_ => CsrfPattern).saveAs("csrfToken")

  protected val csrfTokenExpr: String = "#{csrfToken}"

}
