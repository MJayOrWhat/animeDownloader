package de.jando.anime.download

import de.jando.anime.AnimeOnePiece

class MangaDownloader {

    static String path = "resources/"

    public static downloadMangas() {
        //new AnimeNaruto(path: path).beginWithDownload()
        //new AnimeFairyTail(path: path).beginWithDownload()
        new AnimeOnePiece(path: path).beginWithDownload()
    }

    public static void main(String[] args) {
        downloadMangas()
    }

}
