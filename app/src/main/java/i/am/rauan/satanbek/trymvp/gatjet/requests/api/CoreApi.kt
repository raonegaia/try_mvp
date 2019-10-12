package i.am.rauan.satanbek.trymvp.gatjet.requests.api

import retrofit2.http.GET
import i.am.rauan.satanbek.trymvp.gatjet.requests.pojo.Album
import i.am.rauan.satanbek.trymvp.gatjet.requests.pojo.Post
import i.am.rauan.satanbek.trymvp.gatjet.requests.pojo.User
import i.am.rauan.satanbek.trymvp.gatjet.util.Constants
import io.reactivex.Observable
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.DELETE
import retrofit2.http.Path

interface CoreApi {

    @GET("albums")
    fun getAlbumList(): Observable<List<Album>>

    @GET("album/{id}")
    fun getAlbum(@Path("id") id: Int): Observable<Album>

    @DELETE("album/{id}")
    fun deleteAlbum(@Path("id") id: Int)

    @GET("posts")
    fun getPostList(): Observable<List<Post>>

    @GET("posts/{id}")
    fun getPost(@Path("id") id: Int): Observable<Post>

    @DELETE("posts/{id}")
    fun deletePosts(@Path("id") id: Int)

    @GET("users")
    fun getUserList(): Observable<List<User>>

    @GET("user/{id}")
    fun getUser(@Path("id") id: Int): Observable<User>

    @DELETE("user/{id}")
    fun deleteUser(@Path("id") id: Int)


    companion object Factory {
        fun create(): CoreApi {
            val retrofit = retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .build()

            return retrofit.create(CoreApi::class.java)
        }
    }
}