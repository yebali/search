package com.yebali.search.feignclient.rest

import com.fasterxml.jackson.annotation.JsonProperty

interface FetchNaverNews {
    data class Response(
        val lastBuildDate: String,
        val total: Int,
        val start: Int,
        val display: Int,
        val items: List<Item>,
    ) {
        data class Item(
            val title: String,
            // naver api spec이 실제로 originalLink이 아니라 originallink이다.
            @JsonProperty("originallink")
            val originalLink: String,
            val link: String,
            val description: String,
            val pubDate: String,
        )
    }
}
