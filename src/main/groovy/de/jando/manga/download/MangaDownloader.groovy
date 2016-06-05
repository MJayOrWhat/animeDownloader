package de.jando.manga.download

import de.jando.manga.MangaFairyTail
import de.jando.manga.MangaNaruto
import de.jando.manga.MangaOnePiece
import org.apache.http.client.methods.HttpGet

class MangaDownloader {


    static void download() {
        String urlToDownload
        //String theString = IOUtils.toString(response.entity.content, 'UTF-8');
        //   theString.eachLine {
        //     if (it.contains("file:") && it.contains("http")) {
        //        urlToDownload = it.substring(it.indexOf("http"), it.length()).replace(",", "").replace("'", "")
        //   }
        // }
        downloadFile(urlToDownload)
    }

    static void downloadFile(String downloadUrl) {
        HttpGet httpGet = new HttpGet(downloadUrl)
    }

    public static void main(String[] args) {
        //download()
        //new MangaNaruto().beginWithDownload()
//        new MangaFairyTail().beginWithDownload()
        new MangaOnePiece().beginWithDownload()
    }


}
