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

    CloseableHttpClient httpclient
    CloseableHttpResponse response
    HttpGet httpGet

    public downloadFileResource(String fileRessource, String folderAndFileName, String episodeNumber) {
        httpclient = HttpClients.createDefault()
        httpGet = new HttpGet(fileRessource)
        response = httpclient.execute(httpGet)
        GString newFilePathName = "${folderAndFileName}/${folderAndFileName}.E${episodeNumber}.mp4"
        File fileToDownload = new File(newFilePathName)
        if (!fileToDownload.exists()) {
            new File(newFilePathName).newOutputStream().write(getFileContentBytes(response))
            println "${folderAndFileName} Episode ${episodeNumber} successfully downloaded"
        } else {
            println "${folderAndFileName} Episode ${episodeNumber} skipped, it already exist"
        }

    }

    public byte[] getFileContentBytes(CloseableHttpResponse response) {
        return response.getEntity().getContent().bytes
    }

    public String getFileContentString(String urlContentToDisplay) {
        httpclient = HttpClients.createDefault()
        HttpGet httpGet = new HttpGet(urlContentToDisplay)
        response = httpclient.execute(httpGet)
        return IOUtils.toString(response.getEntity().getContent(), 'UTF-8')
    }

}
