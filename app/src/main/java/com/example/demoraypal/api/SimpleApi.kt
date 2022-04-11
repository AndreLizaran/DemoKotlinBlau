package com.example.demoraypal.api

import com.example.demoraypal.model.Post
import retrofit2.http.GET

interface SimpleApi {
  @GET ("posts/1")
  supend fun getPost():Post
}