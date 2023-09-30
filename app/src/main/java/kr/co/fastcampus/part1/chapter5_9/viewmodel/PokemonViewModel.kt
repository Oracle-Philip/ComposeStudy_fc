package kr.co.fastcampus.part1.chapter5_9.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kr.co.fastcampus.part1.chapter5_9.PokeAPI
import kr.co.fastcampus.part1.chapter5_9.Response
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kr.co.fastcampus.part1.chapter5_9.PokemonResponse
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val pokeAPI: PokeAPI
) : ViewModel() {

    /**
     * 08:43 설명
     * val pokemonList: Flow<PagingData<Response.Result>> = getPokemons().cachedIn(viewModelScope)에서
     * getPokemons()이 호출되면 flow return되고 그걸  .cachedIn(viewModelScope)해서 viewModelScope에 집어넣게했다.
     */
    val pokemonList: Flow<PagingData<Response.Result>> = getPokemons().cachedIn(viewModelScope)
    var pokemonResult by mutableStateOf(
        PokemonResponse(
            PokemonResponse.Species(""),
            PokemonResponse.Sprites("")
        )
    )

    /**
     * pager에서는 2가지 항목을 setting 해야한다.
     * 1. config -> 이번 페이지에 몇개 데이터가 있을것인지 설정
     * 2. pagingSourceFactory -> getRefreshKey, load
     *  - getRefreshKey : refresh를 할때 가장 근처에 있는 위치가 어디냐에 따라서 page를 결정하는것
     *  - load : load에서 핵심적인것은 LoadResult.Page를 return 하는 것이다.
     *   - LoadResult.Page에는 인자가 3개 있다.
     *    - data : 현재 페이지의 데이터,
     *    - prevKey : 이전 페이지로 가기위한 key
     *    - nextKey : 다음 페이지로 가기위한 key
     */

    private fun getPokemons(): Flow<PagingData<Response.Result>> = Pager(
        config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = true,
            prefetchDistance = 5
        ),
        pagingSourceFactory = {
            object : PagingSource<Int, Response.Result>() {
                override fun getRefreshKey(state: PagingState<Int, Response.Result>): Int? {
                    return state.anchorPosition
                }

                override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Response.Result> {
                    try {
                        /**
                         * key가 없는 경우에는 baseUrl + pokemon/
                         * @GET("pokemon/")
                            suspend fun getPokemons(): Response를 호출해서 key를 가져오고 있다.
                            -> 처음 페이지를 보는 경우

                            key가 있는 경우에는 key와 loadSize를 전달하고 있다.
                            -> 즉 여러 페이지를 보는 경우이다.
                         */
                        val pokemons = if (params.key != null) {
                            pokeAPI.getPokemons(params.key as Int, params.loadSize)
                        } else {
                            pokeAPI.getPokemons()
                        }
                        // 단계 2: `offset=20&limit=20` 형태의 주소에서
                        // `prevKey`와 `nextKey`를 만들어 전달하자.
                        return LoadResult.Page(
                            data = pokemons.results,
                            prevKey = pokemons.previous?.substringAfter("offset=")?.substringBefore("&")?.toInt(),
                            nextKey = pokemons.next?.substringAfter("offset=")?.substringBefore("&")?.toInt()
                        )
                    } catch (e: Exception) {
                        Log.e("EEE", "error: $e")
                        e.printStackTrace()
                        return LoadResult.Error(e)
                    }
                }
            }
        }
    ).flow

    fun getPokemon(pokemonId: Int) {
        viewModelScope.launch {
            pokemonResult = pokeAPI.getPokemon(pokemonId)
        }
    }
}
