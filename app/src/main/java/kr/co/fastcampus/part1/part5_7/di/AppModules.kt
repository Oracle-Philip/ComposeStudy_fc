package kr.co.fastcampus.part1.part5_7.di

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.co.fastcampus.part1.part5_7.service.GithubService
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

// 단계 4: `AppModules`에 `@Module` 어노테이션과 `@InstallIn(SingletonComponent::class)`
// 어노테이션을 추가합니다.
/**
 * @Module을 통해 대입해주는 객체를 만든다.
 * 앞서 MainActivity에는 @AndroidEntryPoint를 통해 대입을 받겠다고 명시되어 있다.
 *
 * @Module은 의존성 주입을 하는 provider가 있는 공간으로 보면 된다.
 *
 * @InstallIn -> 어디에 설치하겠다는 애노테이션
 * 기본으로 Hilt에는 SingletonComponent가 있다.
 *
 * 일반적으로 SingletonComponent 외에 컴포넌트를 직접 만들어서
 * 주입을 받는쪽과 주입을 하는 쪽을 연결해줘야한다.
 * Dagger Hilt에서는 SingletonComponent를 통해 자동으로 연결해줄 수 있다.
 */
@InstallIn(SingletonComponent::class)
@Module
class AppModules {
    // 단계 5: 아래 프로파이더를 만듭시다.
    /**
     * @Singleton -> 문자열 하나만 생성되게 하겠다.
     * @Provides -> 주입을 하는것이므로 @Provides를 붙인다.
     * @Named("API_URI") -> String이 여러개일수 있어 구분위해
     * Dagger Hilt에게는 메소드명이 의미가 없어서 @Named를 통해 컴파일러가 구분할 수 있게 해야한다.
     */
    @Singleton
    @Provides
    @Named("API_URI")
    fun provideWebAPI(): String = "https://api.github.com/"

    // GSON : 구글이 만든 컨버팅 라이브러리 JSON <-> 코틀린 객체, 자바 객체
    @Singleton
    @Provides
    fun provideGson(): Gson =
        GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()

    /**
     * GSON과 Retrofit 연결
     */
    @Singleton
    @Provides
    fun provideConverterFactory(
        gson: Gson
    ): Converter.Factory = GsonConverterFactory.create(gson)

    @Singleton
    @Provides
    fun provideRetrofit(
        @Named("API_URI") apiUrl: String,
        converterFactory: Converter.Factory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(apiUrl)
        .addConverterFactory(converterFactory)
        .build()

    @Singleton
    @Provides
    fun provideGithubService(
        retrofit: Retrofit
    ): GithubService = retrofit.create(GithubService::class.java)
}