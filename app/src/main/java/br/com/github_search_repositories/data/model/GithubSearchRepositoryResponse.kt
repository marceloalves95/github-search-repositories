package br.com.github_search_repositories.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubSearchRepositoryResponse(
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("full_name")
    val fullName: String?,
    @SerializedName("language")
    val language: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("owner")
    val owner: OwnerResponse?,
    @SerializedName("pushed_at")
    val pushedAt: String?,
    @SerializedName("size")
    val size: Int?,
    @SerializedName("updated_at")
    val updatedAt: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("html_url")
    val htmlUrl: String?
) : Parcelable

@Parcelize
data class OwnerResponse(
    @SerializedName("avatar_url")
    val avatarUrl: String?,
    @SerializedName("html_url")
    val htmlUrl: String?,
    @SerializedName("login")
    val login: String?,
    @SerializedName("repos_url")
    val reposUrl: String?,
    @SerializedName("url")
    val url: String?
) : Parcelable