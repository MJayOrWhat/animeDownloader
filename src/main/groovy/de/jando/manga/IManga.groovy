package de.jando.manga

/**
 * Created by mjando on 02.06.16.
 */
interface IManga {

    public void getFileListToDownload()

    public void extractAllEpisodeNumbers(String alleEpisodeHtmlContent)

    public void loadAllVideoPages()

}