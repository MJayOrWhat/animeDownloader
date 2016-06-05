package de.jando.manga

import de.jando.manga.download.HttpClient

/**
 * Created by mjand on 05.06.2016.
 */
abstract class MangaTube {

    void setupFolder() {
        new File(folderAndFileName).mkdirs()
    }

    void getFileListToDownload() {
        String allEpisodeHtmlContent = httpClient.getFileContentString(allEpisodes)
        extractAllEpisodeNumbers(allEpisodeHtmlContent)
        loadAllVideoPages()
    }

    void extractAllEpisodeNumbers(String alleEpisodeHtmlContent) {
        println "Fetching all EpisodeNumbers"
        alleEpisodeHtmlContent.eachLine { String htmlLine ->
            if (htmlLine.contains(this.htmlLine)) {
                println htmlLine
                String titleAndEpisodeNumber = htmlLine.substring(htmlLine.indexOf(episodeStringPart), htmlLine.indexOf(">") - 2)
                println titleAndEpisodeNumber
                episodeList.add(titleAndEpisodeNumber.split("-")[indexForEpisode])
            }

        }
    }

    void loadAllVideoPages() {
        episodeList.each { String episodeNumber ->
            println "#####################################"
            println "Now at Epsiodenumber ${episodeNumber}"
            String videoPageContent = httpClient.getFileContentString(completeUrl + episodeNumber)
            filterVideoPage(videoPageContent, episodeNumber)
        }

    }

    void filterVideoPage(String videoPageContent, String episodeNumber) {
        videoPageContent.eachLine { String htmlLine ->
            String videoUrl = null

            if (htmlLine.startsWith(iFrameString)) {
                println "Video file fetched"
                videoUrl = htmlLine.split("\"")[1]
            }
            if (videoUrl)
                fetchVideoFromPage(videoUrl, episodeNumber)
        }
    }

    void fetchVideoFromPage(String videoPageUrl, String episodeNumber) {
        String videoPageContent = httpClient.getFileContentString(videoPageUrl)
        videoPageContent.eachLine { String htmlLine ->
            if (htmlLine.contains("file: ") && htmlLine.endsWith(",")) {
                downloadVideo(getVideoUrl(htmlLine), episodeNumber)
            }
        }
    }

    String getVideoUrl(String htmlLine) {
        return htmlLine.split("file: ")[1].replace("'", "").replace(",", "")
    }

    void downloadVideo(String videoFile, String episodeNumber) {
        httpClient.downloadFileResource(videoFile, folderAndFileName, episodeNumber)
    }

}
