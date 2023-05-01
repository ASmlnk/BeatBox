package combignerdranch.android.beatbox

import android.content.res.AssetFileDescriptor
import android.content.res.AssetManager
import android.media.SoundPool
import android.util.Log
import java.io.IOException
import java.lang.Exception

private const val TAG = "BeatBox"
private const val SOUNDS_FOLDER = "sample_sounds"  /*папка для хранения активов*/
private const val MAX_SOUNDS = 5 //для SoundPool

/*класс для управления файлами(активами), входной параметр AssetManager(извлечение активов)*/

class BeatBox (private val assets: AssetManager) {

    val sounds: List<Sound> // свойство для хранения списка имен файлов(из loadSounds())

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
             soundNames = assets.list(SOUNDS_FOLDER)!!  //assets получаем из конструктора

            /*Функция AssetManager.list(String) возвращает список имен файлов,
            * содержащихся в заданной папке(SOUNDS_FOLDER), мы передаем путь к папке со звуком*/
            //soundNames.asList()  //возвращает список ввиде List

        } catch (e: Exception) {
            Log.e(TAG, "Could not list assets", e)
            return emptyList()  // если ошибка возвращает пустой список
        }

        val sounds = mutableListOf<Sound>()

        soundNames.forEach { filename ->
            /*форматируем полученый список файлов (откидываем путь и удаляем расшерение)
            дастаем только имя файла*/
            val assetPath = "$SOUNDS_FOLDER/$filename"
            val sound = Sound(assetPath)
            try {
                load(sound)
                sounds.add(sound)
            } catch (ioe: IOException) {
                Log.e(TAG, "Cound not load sound $filename", ioe)
            }
        }
        return sounds  //возвращаем список имен, для передачи в адаптер ресайзвиев
    }

    /*Функция для загрузки файлов в SoundPool*/
    private fun load (sound: Sound) {
        val afd: AssetFileDescriptor = assets.openFd(sound.assetPath)  //openFd(String) инициализирует исключение IOException, то
                                                                        //и load(sound) тоже будет инициализировать это исключение
        val soundId = soundPool.load(afd, 1)  //загружает файл в SoundPool для последующего воспроизведения
        sound.soundId = soundId
    }

    /*Функция для воспроизведения*/
    fun play(sound: Sound) {
        sound.soundId?.let {
            soundPool.play(it, 1.0f, 1.0f, 1,0,1.0f)
        }
    }

    fun release() {
        soundPool.release()
        /*освободить ресурсы SoundPool вызовом SoundPool.release() после
            завершения работы*/
    }
}