package uk.gov.hmrc.perftests.example

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder

object AuthLoginRequests extends BaseRequest {

  val navigateToAuth: HttpRequestBuilder =
    http("Auth Wizard")
      .get(authLoginStubUrl)
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("Authority Wizard").exists)

  private val startPageUrl: String = s"$baseUrl$route"

  val authLogInAsIndividual: HttpRequestBuilder =
    http("Login as Individual User")
      .post(authLoginStubUrl)
      .formParam("redirectionUrl", startPageUrl)
      .formParam("csrfToken", csrfTokenExpr)
      .formParam("credentialStrength", "strong")
      .formParam("affinityGroup", "Individual")
      .formParam("enrolment[0].taxIdentifier[0].name", "")
      .formParam("enrolment[0].identifiers[0].name", "")
      .formParam("enrolment[0].identifiers[0].value", "")
      .check(status.is(303))
      .check(header("Location").is(startPageUrl))
}
