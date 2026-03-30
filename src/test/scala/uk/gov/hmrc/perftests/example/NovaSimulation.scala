package uk.gov.hmrc.perftests.example

import uk.gov.hmrc.performance.simulation.PerformanceTestRunner
import uk.gov.hmrc.perftests.example.AuthLoginRequests._

class NovaSimulation extends PerformanceTestRunner{

  setup(
    "auth-baseline-simulation",
    "NoVA Imports Notification Baseline Simulation"
  ) withRequests(
      navigateToAuth,
      authLogInAsIndividual
    )
  runSimulation()
}
