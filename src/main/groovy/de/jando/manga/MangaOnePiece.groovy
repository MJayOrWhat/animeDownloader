package de.jando.manga

import de.jando.manga.download.HttpClient

/**
 * Created by mjand on 05.06.2016.
 */
class MangaOnePiece extends MangaTube implements IManga {

    String folderAndFileName = "One.Piece"
    String hostUrl = "http://onepiece-tube.com/"
    String episodesString = "folge"
    String htmlLine = "/folge"
    String iFrameString = "<IFRAME SRC="
    Integer indexForEpisode = 1

    String episodeStringPart = "/" +episodesString
    String allEpisodes = hostUrl + episodesString
    String domainPart = "${htmlLine}-"
    Set<String> episodeList = new LinkedList<>()
    String completeUrl = hostUrl + episodesString
    HttpClient httpClient = new HttpClient()


    @Override
    void beginWithDownload() {
        setupFolder()
        getFileListToDownload()
    }

    void extractAllEpisodeNumbers(String alleEpisodeHtmlContent) {
        println "Fetching all EpisodeNumbers"
        alleEpisodeHtmlContent.eachLine { String htmlLine ->
            if (htmlLine.matches("<tr class=\"mediaitem\" onclick=\"window.location.href = '/folge/[0-9]+...")) {
                println htmlLine.indexOf(episodeStringPart)
                String titleAndEpisodeNumber = htmlLine.substring(htmlLine.indexOf(episodeStringPart), htmlLine.indexOf(">") - 2)
                episodeList.add(titleAndEpisodeNumber.split("/")[indexForEpisode])
            }

        }
    }
}
