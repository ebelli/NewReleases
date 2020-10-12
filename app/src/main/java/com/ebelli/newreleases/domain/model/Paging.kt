package com.ebelli.newreleases.domain.model

data class Paging(
    val href : String?,
    val items: List<Album>,
    val limit: Int?,
    val next: String?,
    val offset: Int?,
    val previous: String?,
    val total: Int?
)