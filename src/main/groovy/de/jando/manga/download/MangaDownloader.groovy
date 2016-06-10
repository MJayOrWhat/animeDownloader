package de.jando.manga.download

import de.jando.manga.MangaOnePiece

class MangaDownloader {

    static String path = "resources/"

    public static downloadMangas() {
        //new MangaNaruto(path: path).beginWithDownload()
        //new MangaFairyTail(path: path).beginWithDownload()
        new MangaOnePiece(path: path).beginWithDownload()
    }

    public static void main(String[] args) {
        downloadMangas()
    }

}
