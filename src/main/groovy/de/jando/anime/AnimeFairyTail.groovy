package de.jando.anime

import de.jando.anime.download.HttpClient

class AnimeFairyTail extends AnimeTube implements IAnime {

    String path

    String folderAndFileName = "Fairy.Tail"
    String hostUrl = "http://fairytail-tube.org/"
    String episodesString = "folge"
    String htmlLine = "-ger-sub"
    String iFrameString = "<IFRAME SRC="

    Integer indexForEpisode = 1

    String episodeStringPart = "/" +episodesString
    String allEpisodes = hostUrl + episodesString
    String domainPart = "${htmlLine}-"
    Set<String> episodeList = new LinkedList<>()
    String completeUrl = allEpisodes
    HttpClient httpClient

    void loadAllVideoPages() {
        episodeList = ["264", "265", "266", "267", "268", "269", "270", "271", "272", "273", "274", "275", "276", "277"]
        episodeList.each { String episodeNumber ->
            if (!fileAlreadyExists(episodeNumber)) {
                println "#####################################"
                println "Now at Epsiodenumber ${episodeNumber}"
                String videoPageContent = httpClient.getFileContentString(completeUrl + "-" + episodeNumber + htmlLine)
                filterVideoPage(videoPageContent, episodeNumber)
            } else {
                println "${folderAndFileName} Episode ${episodeNumber} skipped, it already exist"
            }

        }
    }



}
