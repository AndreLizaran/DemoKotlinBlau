package com.example.demoraypal.repository

import com.example.demoraypal.api.RetrofitInstance
import com.example.demoraypal.model.Post

class Repository {
  suspend fun getPost():Post {
    return RetrofitInstance.api.getPost()
  }
}