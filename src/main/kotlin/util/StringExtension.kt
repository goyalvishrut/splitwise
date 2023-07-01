package util

import models.ContributionType
import models.InputAction

fun String.toContributionType(): ContributionType =
    ContributionType.values().first { it.name.equals(this, ignoreCase = true) }

fun String.toInputAction(): InputAction =
    InputAction.values().firstOrNull { it.name.equals(this, ignoreCase = true) } ?: InputAction.QUIT
