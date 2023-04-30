package combignerdranch.android.beatbox

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

class SoundViewModel : BaseObservable() {
    /*Выполняет роль интерфейса для адаптера (это модель представления)*/

    var sound: Sound? = null
        set(sound) {
            field = sound
            notifyChange()

        /*notifyChange() эта фугкция оповещает класс привязки о том что все Bindable- свойства
            * наших обьектов были обгавлены. Класс привязки выполнит код внутри скобок {} для повторного
            * заполнения представления.*/
    }

    @get:Bindable
    val title: String?
        get() = sound?.name

    /*Функция для получения названия, которое должно отображаться на кнопке */

}