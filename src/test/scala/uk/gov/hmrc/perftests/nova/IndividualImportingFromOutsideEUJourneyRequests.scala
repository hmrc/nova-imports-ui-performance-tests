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

package uk.gov.hmrc.perftests.nova

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder

object IndividualImportingFromOutsideEUJourneyRequests extends BaseRequest {

  val getStartPage: HttpRequestBuilder =
    http("Navigate to Start Page")
      .get(s"$baseUrl$route/start")
      .check(status.is(303))

  val getVehicleFromEUPage: HttpRequestBuilder =
    http("Navigate to Vehicle from EU page")
      .get(s"$baseUrl$route/vehicle-from-eu")
      .check(status.is(200))
      .check(saveCsrfToken())
      .check(
        regex(
          "Are you completing a notification for a vehicle brought into Northern Ireland from an EU country\\?"
        ).exists
      )

  val postVehicleFromEUPageAsNo: HttpRequestBuilder =
    http("Submit Vehicle from EU page as No")
      .post(s"$baseUrl$route/vehicle-from-eu")
      .formParam("csrfToken", csrfTokenExpr)
      .formParam("value", "false")
      .check(status.is(303))
      .check(header("Location").is(s"$route/vehicle-outside-eu"))

  val getVehicleOutsideEUPage: HttpRequestBuilder =
    http("Navigate to Vehicle from Outside EU page")
      .get(s"$baseUrl$route/vehicle-outside-eu")
      .check(status.is(200))
      .check(regex("If you’ve brought a vehicle into Northern Ireland from outside the EU").exists)
}
