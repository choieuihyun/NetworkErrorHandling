package com.enjoy_project.networkhandling

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var api: DataApi

    @Before
    fun setup() {

        mockWebServer = MockWebServer()
        mockWebServer.start()

        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("https://jsonplaceholder.typicode.com/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(DataApi::class.java)

    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    // 데이터 성공 테스트
    @Test
    fun testApiCallSuccess() {

        // MockWebServer를 사용할 경우, 일반적으로 네트워크 호출에 대한 예외가 발생하지 않기 때문에 사실 상 의미없는 네트워크 에러 핸들링인데
        // 그냥 해봄. 에러 발생은 아래 코드에 있음
        try {

            val response =
                MockResponse().setResponseCode(200).setBody("{ \"message\": \"success\" }")

            mockWebServer.enqueue(response)

            // 테스트 대상 함수 호출
            val call = api.getData(1)
            val actualResponse = call.execute()

            NetworkResult.Success(actualResponse.body())

            // 테스트 결과 검증
            assertTrue(actualResponse.isSuccessful)
            assertEquals(actualResponse.body(), actualResponse.body())

            println(actualResponse.body().toString())

        } catch (e : Exception) {

            val networkError = NetworkErrorHandlerImpl()
            val errorType = networkError.handleNetworkError(e)
            NetworkResult.Error(errorType)

        }
    }

    // 500 에러 테스트
    @Test
    fun testApiCallServerError() {

        val response = MockResponse().setResponseCode(500)
        mockWebServer.enqueue(response)

        val call = api.getData(2)
        val actualResponse = call.execute()

        assertFalse(actualResponse.isSuccessful)
        assertEquals(500, actualResponse.code())

        println(actualResponse.code())

    }

}