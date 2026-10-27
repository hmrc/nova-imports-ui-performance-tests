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

object NotificationTaskListRequests extends BaseRequest {

  private val createVehicleNotification = s"$route/create-vehicle-notification"

  // Notification Task List
  val navigateToCreateVehicleNotification: HttpRequestBuilder =
    http("Navigate to Create Vehicle Notification")
      .get(s"$baseUrl$createVehicleNotification")
      .check(status.is(200))
      .check(regex("Create a vehicle notification").exists)
}
