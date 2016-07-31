package de.jando.anime

/**
 * Created by mjando on 02.06.16.
 */
interface IAnime {

    public void beginWithDownload()

    public void getFileListToDownload()

    public void extractAllEpisodeNumbers(String alleEpisodeHtmlContent)

    public void loadAllVideoPages()

}