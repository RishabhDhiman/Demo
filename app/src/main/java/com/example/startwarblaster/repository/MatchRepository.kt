package com.example.startwarblaster.repository

import android.content.res.Resources
import com.example.startwarblaster.R
import com.example.startwarblaster.dataclass.MatchInfoData
import com.example.startwarblaster.dataclass.PlayerAndPointsData
import com.example.startwarblaster.utils.getFileFromRaw
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Collections

object MatchRepository {
    private val cachedMatchesMapOfPlayers = HashMap<Int, ArrayList<MatchInfoData>>()
    private val cachedMapOfWinnersCount = HashMap<Int, Int>()
    private val cachedMapOfPlayerById = HashMap<Int, PlayerAndPointsData>()
    private var cachedPlayerList = listOf<PlayerAndPointsData>()

    suspend fun loadPointsTable(resources: Resources) =
        withContext(Dispatchers.Default) {
            if (cachedPlayerList.isNotEmpty()) return@withContext

            cachedPlayerList =
                getFileFromRaw(
                    resources,
                    R.raw.starwarsplayers,
                )
            for (player in cachedPlayerList) {
                cachedMapOfPlayerById[player.id] = player
            }

            val matchesData = getFileFromRaw<List<MatchInfoData>>(
                resources,
                R.raw.starwarsmatches
            )

            for (match in matchesData) {
                cachedMatchesMapOfPlayers.putIfAbsent(match.player1.id, ArrayList())
                cachedMatchesMapOfPlayers.putIfAbsent(match.player2.id, ArrayList())
                match.player1.name = cachedMapOfPlayerById[match.player1.id]?.name ?: ""
                match.player2.name = cachedMapOfPlayerById[match.player2.id]?.name ?: ""
                cachedMatchesMapOfPlayers[match.player1.id]!!.add(match)
                cachedMatchesMapOfPlayers[match.player2.id]!!.add(match)
                if (match.player1.score > match.player2.score) {
                    cachedMapOfWinnersCount[match.player1.id] =
                        cachedMapOfWinnersCount.getOrDefault(match.player1.id, 0) + 1
                } else if (match.player1.score == match.player2.score) {
                    cachedMapOfWinnersCount[match.player1.id] =
                        cachedMapOfWinnersCount.getOrDefault(match.player1.id, 0) + 1
                    cachedMapOfWinnersCount[match.player2.id] =
                        cachedMapOfWinnersCount.getOrDefault(match.player2.id, 0) + 1
                } else {
                    cachedMapOfWinnersCount[match.player2.id] =
                        cachedMapOfWinnersCount.getOrDefault(match.player2.id, 0) + 1
                }
            }
            for (player in cachedPlayerList) {
                player.score = cachedMapOfWinnersCount[player.id] ?: 0
            }

            Collections.sort(cachedPlayerList) { item1, item2 ->
                if (item1.score != item2.score) {
                    item2.score.compareTo(item1.score)
                } else {
                    item1.name.compareTo(item2.name)
                }
            }
        }

    fun getMatchesByPlayerId(playerId: Int): ArrayList<MatchInfoData> {
        return cachedMatchesMapOfPlayers[playerId] ?: arrayListOf()

    }

    fun getPlayerList(): List<PlayerAndPointsData> {
        return cachedPlayerList
    }
}