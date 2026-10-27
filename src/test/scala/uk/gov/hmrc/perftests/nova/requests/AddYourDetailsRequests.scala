/*
 * Copyright 2026 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.perftests.nova.requests

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder

object AddYourDetailsRequests extends BaseRequest {

  private val aboutYourDetails               = s"$route/about-your-details"
  private val addYourName                    = s"$route/name"
  private val addYourBusinessName            = s"$route/business-name"
  private val addYourContactNumbers          = s"$route/contact-numbers"
  private val addYourEmailAddress            = s"$route/email-address"
  private val checkYourAnswersAddYourDetails = s"$route/check-answers/your-details"

  // Organisation - About your details
  val navigateToAboutYourDetails: HttpRequestBuilder =
    http("Navigate to About your details Page")
      .get(s"$baseUrl$aboutYourDetails")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("About your details").exists)

  val continueFromAboutYourDetails: HttpRequestBuilder =
    http("Continue from About your details Page")
      .post(s"$baseUrl$aboutYourDetails")
      .formParam("csrfToken", csrfTokenExpr)
      .check(status.is(303))

  // Add your details
  val navigateToAddYourName: HttpRequestBuilder =
    http("Navigate to Add your details - Name")
      .get(s"$baseUrl$addYourName")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("What is your name?").exists)

  val navigateToAddYourBusinessName: HttpRequestBuilder =
    http("Navigate to Add your details - Business Name")
      .get(s"$baseUrl$addYourBusinessName")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("What is your business name?").exists)

  val submitAddYourName: HttpRequestBuilder =
    http("Provide your name and Continue")
      .post(s"$baseUrl$addYourName")
      .formParam("csrfToken", csrfTokenExpr)
      .formParam("title", "#{notifierTitle}")
      .formParam("firstName", "#{notifierFirstName}")
      .formParam("lastName", "#{notifierLastName}")
      .check(status.is(303))

  val submitAddYourBusinessName: HttpRequestBuilder =
    http("Provide your business Name and Continue")
      .post(s"$baseUrl$addYourBusinessName")
      .formParam("csrfToken", csrfTokenExpr)
      .formParam("value", "#{notifierBusinessName}")
      .check(status.is(303))

  val navigateToAddContactNumbers: HttpRequestBuilder =
    http("Navigate to Add your details - Contact Numbers")
      .get(s"$baseUrl$addYourContactNumbers")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("What are your contact numbers?").exists)

  val submitAddContactNumbers: HttpRequestBuilder =
    http("Provide contact numbers and Continue")
      .post(s"$baseUrl$addYourContactNumbers")
      .formParam("csrfToken", csrfTokenExpr)
      .formParam("phoneNumber", "#{notifierPhoneNumber}")
      .formParam("mobileNumber", "#{notifierMobileNumber}")
      .check(status.is(303))

  val navigateToAddEmailAddress: HttpRequestBuilder =
    http("Navigate to Add your details - Email Address")
      .get(s"$baseUrl$addYourEmailAddress")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("What is your email address?").exists)

  val submitAddEmailAddress: HttpRequestBuilder =
    http("Provide email address and Continue")
      .post(s"$baseUrl$addYourEmailAddress")
      .formParam("csrfToken", csrfTokenExpr)
      .formParam("value", "#{notifierEmailAddress}")
      .check(status.is(303))

  // Check Your Answers - Add your details
  val navigateToCheckYourAnswersAddYourDetails: HttpRequestBuilder =
    http("Navigate to Check your answers - Add your details Page")
      .get(s"$baseUrl$checkYourAnswersAddYourDetails")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(regex("Add your details").exists)
      .check(regex("Check your answers").exists)

  val submitCheckYourAnswersAddYourDetails: HttpRequestBuilder =
    http("Submit Check your answers - Add your details Page")
      .post(s"$baseUrl$checkYourAnswersAddYourDetails")
      .formParam("csrfToken", csrfTokenExpr)
      .check(status.is(303))
      .check(header("Location").is(s"$route/create-vehicle-notification"))

}
