package de.jando.anime

import de.jando.anime.download.HttpClient

/**
 * Created by mjand on 05.06.2016.
 */
class AnimeOnePiece extends AnimeTube implements IAnime {

    String path

    String folderAndFileName = "One.Piece"
    String hostUrl = "http://onepiece-tube.com/"
    String episodesString = "folge/"
    String htmlLine = "/folge"
    String iFrameString = "<IFRAME SRC="

    Integer indexForEpisode = 2

    String episodeStringPart = "/" +episodesString
    String allEpisodes = hostUrl + episodesString
    String domainPart = "${htmlLine}-"
    Set<String> episodeList = new LinkedList<>()
    String completeUrl = allEpisodes
    HttpClient httpClient


    void extractAllEpisodeNumbers(String alleEpisodeHtmlContent) {
        println "Fetching all EpisodeNumbers"
        alleEpisodeHtmlContent.eachLine { String htmlLine ->
            if (htmlLine.matches("<tr class=\"mediaitem\" onclick=\"window.location.href = '/folge/[0-9]+...")) {
                String titleAndEpisodeNumber = htmlLine.substring(htmlLine.indexOf(episodeStringPart), htmlLine.indexOf(">") - 2)
                episodeList.add(titleAndEpisodeNumber.split("/")[indexForEpisode])
            }
        }
    }


}
