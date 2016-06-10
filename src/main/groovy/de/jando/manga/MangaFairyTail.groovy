package de.jando.manga

import de.jando.manga.download.HttpClient

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
//        episodeList = ["202", "203", "204", "206", "207", "208", "264", "265", "266", "267", "268", "269", "270", "271", "272", "273", "274", "275", "276", "277"]
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

    private boolean fileAlreadyExists(String episodeNumber) {
        return httpClient.checkForFile(episodeNumber, folderAndFileName)
    }

}
