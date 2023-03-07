package combignerdranch.android.beatbox

import android.content.res.AssetManager
import android.media.SoundPool
import android.util.Log
import java.lang.Exception

private const val TAG = "BeatBox"
private const val SOUNDS_FOLDER = "sample_sounds"  /*папка для хранения активов*/
private const val MAX_SOUNDS = 5 //для SoundPool

class BeatBox (private val assets: AssetManager) {

    val sounds: List<Sound>
    private val soundPool = SoundPool.Builder() //объект для воспроизведения музыки
        .setMaxStreams(MAX_SOUNDS)  // указывает сколько звуков может воспроизводится одновременно
        .build()

    init {
        sounds = loadSounds()
    }


    /*Функция для получения списка доступных активов*/
   private fun loadSounds(): List<Sound> {

        val soundNames: Array<String>

         try {
             soundNames = assets.list(SOUNDS_FOLDER)!!

            /*Функция AssetManager.list(String) возвращает список имен файлов,
            * содержащихся в заданной папке, мы передаем путь к папке со звуком*/
            //soundNames.asList()  //возвращает список ввиде List

        } catch (e: Exception) {
            Log.e(TAG, "Could not list assets", e)
            return emptyList()  // если ошибка возвращает пустой список
        }

        val sounds = mutableListOf<Sound>()

        soundNames.forEach { filename ->
            val assetPath = "$SOUNDS_FOLDER/$filename"
            val sound = Sound(assetPath)
            sounds.add(sound)
        }
        return sounds
    }
}