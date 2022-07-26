package com.tolgahan.android.domain.mapper

import com.tolgahan.android.data.network.model.response.FollowersItem
import javax.inject.Inject
import javax.inject.Singleton

interface UserMapper {
    fun map(model: FollowersItem): FollowersItem
}

@Singleton
class UserMapperImpl @Inject constructor() : UserMapper {
    override fun map(model: FollowersItem): FollowersItem =
        FollowersItem(
            model.avatarUrl,
            model.eventsUrl,
            model.followersUrl,
            model.followingUrl,
            model.gistsUrl,
            model.gravatarId,
            model.htmlUrl,
            model.id,
            model.login,
            model.nodeId,
            model.organizationsUrl,
            model.receivedEventsUrl,
            model.reposUrl,
            model.siteAdmin,
            model.starredUrl,
            model.subscriptionsUrl,
            model.type,
            model.url,
            model.isClicked
        )

}