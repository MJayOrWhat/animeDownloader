package de.jando.anime

import de.jando.anime.AnimeOnePiece

class AnimeDownloader {

    static String path = "resources/"

    public static downloadAnimes() {
        //new AnimeNaruto(path: path).beginWithDownload()
        new AnimeFairyTail(path: path).beginWithDownload()
        //new AnimeOnePiece(path: path).beginWithDownload()
    }

    public static void main(String[] args) {
        downloadAnimes()
    }

}
