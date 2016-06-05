package de.jando.manga

import de.jando.manga.download.HttpClient

/**
 * Created by mjando on 02.06.16.
 */
class MangaNaruto implements IManga {

    String folderAndFileName = "Naruto.Shippuuden"
    String hostUrl = "http://naruto-tube.org/"
    String domainPart = "shippuuden-sub-"
    Set<String> episodeList = new LinkedList<>()
    String completeUrl = hostUrl + domainPart
    HttpClient httpClient = new HttpClient()

    public MangaNaruto() {
        new File(folderAndFileName).mkdirs()
    }


    @Override
    public void getFileListToDownload() {
        String allEpisodeHtmlContent = httpClient.getFileContentString(hostUrl + "shippuuden-folgen")
        extractAllEpisodeNumbers(allEpisodeHtmlContent)
        loadAllVideoPages()
    }

    @Override
    void extractAllEpisodeNumbers(String alleEpisodeHtmlContent) {
        println "Fetching all EpisodeNumbers"
        alleEpisodeHtmlContent.eachLine { String htmlLine ->
            if (htmlLine.contains("/shippuuden-sub")) {
                String titleAndEpisodeNumber = htmlLine.substring(htmlLine.indexOf("shippuuden-sub-"), htmlLine.indexOf(">") - 2)
                episodeList.add(titleAndEpisodeNumber.split("-")[2])
            }
        }
    }

    @Override
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
            if (htmlLine.startsWith("<IFRAME SRC=")) {
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

    protected String getVideoUrl(String htmlLine) {
        return htmlLine.split("file: ")[1].replace("'", "").replace(",", "")
    }

    void downloadVideo(String videoFile, String episodeNumber) {
        httpClient.downloadFileResource(videoFile, folderAndFileName, episodeNumber)
    }
}
