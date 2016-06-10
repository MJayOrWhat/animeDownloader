package de.jando.manga.download

import org.apache.commons.io.IOUtils
import org.apache.http.client.methods.CloseableHttpResponse
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClients

/**
 * Created by mjando on 02.06.16.
 */
class HttpClient {

    String path = "/media/mjando/Libraries/Owncloud/Serien/"

    CloseableHttpClient httpclient
    CloseableHttpResponse response
    HttpGet httpGet

    public downloadFileResource(String fileRessource, String folderAndFileName, String episodeNumber) {
        httpclient = HttpClients.createDefault()
        File file = getFileOnDisk(episodeNumber, folderAndFileName)
        httpGet = new HttpGet(fileRessource)
        response = httpclient.execute(httpGet)
        file.newOutputStream().write(getFileContentBytes(response))
        println "${folderAndFileName} Episode ${episodeNumber} successfully downloaded"


    }

    public byte[] getFileContentBytes(CloseableHttpResponse response) {
        return response.getEntity().getContent().bytes
    }

    public String getFileContentString(String urlContentToDisplay) {
        httpclient = HttpClients.createDefault()
        HttpGet httpGet = new HttpGet(urlContentToDisplay)
        response = httpclient.execute(httpGet)
        String fileContent = IOUtils.toString(response.getEntity().getContent(), 'UTF-8')
        return fileContent
    }

    boolean checkForFile(String episodeNumber, folderAndFileName) {
        return getFileOnDisk(episodeNumber, folderAndFileName).exists() && getFileOnDisk(episodeNumber, folderAndFileName).size() != 0
    }

    File getFileOnDisk(String episodeNumber, String folderAndFileName) {
        return new File("${path}${folderAndFileName}/${folderAndFileName}.E${episodeNumber}.mp4")
    }
}
