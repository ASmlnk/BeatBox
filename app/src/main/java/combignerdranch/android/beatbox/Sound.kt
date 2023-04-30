package combignerdranch.android.beatbox

private const val WAV = ".wav"

/*Класс в котором будем хранить имена файлов*/

class Sound (val assetPath: String, var soundId: Int? = null) {

    val name = assetPath.split("/").last().removeSuffix(WAV)
    /*Отделяем имя файла по / и потом удаляем расширеие
    * String.removeSuffix(WAV) - удаление расширение WAV*/
}