package de.jando.manga

import de.jando.manga.download.HttpClient

/**
 * Created by mjando on 02.06.16.
 */
class MangaNaruto extends MangaTube implements IManga {

    String path

    String folderAndFileName = "Naruto.Shippuuden"
    String hostUrl = "http://naruto-tube.org/"
    String episodesString = "shippuuden-folgen"
    String htmlLine = "shippuuden-sub"
    String iFrameString = "<IFRAME SRC="

    Integer indexForEpisode = 2

    String episodeStringPart = "/" +episodesString
    String allEpisodes = hostUrl + episodesString
    String domainPart = "${htmlLine}-"
    Set<String> episodeList = new LinkedList<>()
    String completeUrl = hostUrl + domainPart
    HttpClient httpClient


}
