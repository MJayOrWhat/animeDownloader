package de.jando.manga

import de.jando.manga.download.HttpClient

/**
 * Created by mjand on 05.06.2016.
 */
class MangaFairyTail extends MangaTube implements IManga {

    String folderAndFileName = "Fairy.Tail"
    String hostUrl = "http://fairytail-tube.org/"
    String episodesString = "folge"
    String htmlLine = "-ger-sub"
    String iFrameString = "<IFRAME SRC="
    Integer indexForEpisode = 1

    String episodeStringPart = episodesString
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

    void loadAllVideoPages() {
        episodeList.each { String episodeNumber ->
            println "#####################################"
            println "Now at Epsiodenumber ${episodeNumber}"
            String videoPageContent = httpClient.getFileContentString(completeUrl + "-" + episodeNumber + htmlLine)
            filterVideoPage(videoPageContent, episodeNumber)
        }

    }

}
