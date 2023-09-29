package kr.co.fastcampus.part1.part5_7

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// 단계 2: DIApp을 `@HiltAndroidApp`로 어노테이션합니다.
/**
 * Hilt로 의존성 주입을 하기 위해서는
 * Application 객체가 만들어져 있어야 한다.
 * @HiltAndroidApp 을 통해
 * 의존성 주입을 위한 초기화코드가 자동 생성된다.
 */
@HiltAndroidApp
class DIApp : Application()