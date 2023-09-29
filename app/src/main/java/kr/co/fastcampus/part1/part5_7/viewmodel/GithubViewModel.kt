package kr.co.fastcampus.part1.part5_7.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.co.fastcampus.part1.part5_7.model.Repo
import kr.co.fastcampus.part1.part5_7.service.GithubService
import javax.inject.Inject

// 단계 6: @HiltViewModel 어노테이션을 지정합니다.
// 단계 7: 생성자에 @Inject를 붙여줍시다.
/*class GithubViewModel(
    private val githubService: GithubService
) : ViewModel() {*/

/**
 * @Inject를 사용하기 위해서는 Spring Boot @Autowired처럼
 * constructor()를 사용해야한다!
 */

/**
 * GithubViewModel을 의존성 주입의 대상이자 MainActivity에서 사용할 수 있게
 * @HiltViewModel 어노테이션을 붙인다.
 */
@HiltViewModel
class GithubViewModel @Inject constructor(
    private val githubService: GithubService
) : ViewModel() {
    /**
     * repos는 mutableStateListOf 타입이기 때문에
     * composable에서 관찰 할 수 있다.
     */
    val repos = mutableStateListOf<Repo>()

    fun getRepos() {
        repos.clear()
        viewModelScope.launch {
            val result = githubService.listRepos("Oracle-Philip")
            repos.addAll(result)
        }
    }
}