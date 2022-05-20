package gorosheg.network

import gorosheg.myapplication.model.Character
import gorosheg.myapplication.model.Description
import gorosheg.myapplication.model.Episode
import gorosheg.myapplication.model.Location
import gorosheg.network.model.*
import gorosheg.network.model.EpisodeResponse
import gorosheg.network.model.EpisodesResponse

private const val CHARACTER_URL = "https://rickandmortyapi.com/api/character/"

internal fun CharactersResponse.toSimpleCharacter(): List<Character> {
    return description.map {
        Character(it.id, it.name, it.image)
    }
}

internal fun DescriptionResponse.toSimpleDescription(): Description =
    Description(id = id, name = name, image = image, status = status, species = species)


internal fun LocationResponse.toSimpleLocation() = Location(name = name, dimension = dimension)

internal fun EpisodesResponse.toSimpleEpisodes(id: Int): List<Episode> {
    return episodes.filterByCharacterId(id)
        .map { episodeResponse -> episodeResponse.toEpisode() }
}

private fun List<EpisodeResponse>.filterByCharacterId(id: Int): List<EpisodeResponse> {
    return filter { episodeResponse ->
        episodeResponse.characters.containsCharacterId(id)
    }
}

private fun List<String>.containsCharacterId(id: Int): Boolean {
    return any { characterId ->
        characterId.getCharacterId() == id
    }
}

private fun String.getCharacterId(): Int {
    return substringAfter(CHARACTER_URL).toInt()
}

private fun EpisodeResponse.toEpisode(): Episode = Episode(name = name, airDate = airDate)